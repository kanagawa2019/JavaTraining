package agatasan_java.java14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

public class FileProcessing {

    /** ���t�`�� �FyyyyMMdd */
    private static final String DATE_OF_BIRTH = "yyyyMMdd";

    /** �t�@�C���ۑ����ʎq */
    private static final String SAVE_IDENTIFIER = "'";
    /** �t�@�C����؂莯�ʎq */
    private static final String SAVA_SEPARATION = ",";
    /** �G�X�P�[�v���ʎq */
    private static final char SAVA_ESCAPE = '\\';

    /** �t�@�C���o�̓p�X */
    private static final String ACCOUNT_FILE_OUTPUT_PATH = "ACCOUNT_FILE_OUTPUT_PATH";
    /** �v���p�e�B�ݒ�p�X */
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";
    /** ��������t�@�C���o�̓p�X */
    private static final String ACCOUNT_HISTORY_FILE_OUTPUT_PATH = "ACCOUNT_HISTORY_FILE_OUTPUT_PATH";
    /** �̔ԗp�����t�@�C���o�̓p�X */
    private static final String NUMBERING_ACCOUNT_FILE_OUTPUT_PATH = "NUMBERING_ACCOUNT_FILE_OUTPUT_PATH";

    /**
     * �O��̃��[�U�[�����擾
     * 
     * @return �O����͂������[�U�[���
     * @throws FileReadException
     * @throws IOException
     */
    public List<Personal> getUserInfo() throws FileReadException, IOException {

        List<Personal> list = new ArrayList<>();

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_FILE_OUTPUT_PATH);

            File file = new File(strPass);

            // �t�@�C�������݂��Ȃ��ꍇ(=����1��ڂ̏ꍇ)
            if (!file.exists()) {
                return list;
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {

                List<String> splitList = fileSplit(str);

                Personal line = new Personal();

                line.setAccountNumber(Integer.parseInt(splitList.get(0)));
                line.setName(splitList.get(1));
                line.setBalance(Integer.parseInt(splitList.get(2)));

                list.add(line);
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(e, "�O��̃��[�U�[����ۑ������t�@�C����ǂݍ��߂܂���ł����B");

        } finally {

            if (br != null) {
                // ���鏈��
                br.close();
            }
        }
        return list;
    }

    /**
     * ����������̏�������
     * 
     * @param accountNumber     �����ԍ�
     * @param id                ��舵���敪
     * @param transactionAmount ������z
     * @param balance           �c��
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void writeHistory(final int accountNumber, final int id, final int transactionAmount, final int balance)
            throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_HISTORY_FILE_OUTPUT_PATH);

            // �ǉ���������
            FileWriter fw = new FileWriter(strPass, true);
            bw = new BufferedWriter(fw);

            // ���t�A�ԍ��A��舵���敪�A������z�A�c��
            String str = String.format("%s%s%s%s%s%d%s%s%s%d%s%s%s%d%s%s%s%d%s", SAVE_IDENTIFIER, getToday(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, accountNumber, SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, id, SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, transactionAmount, SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, balance, SAVE_IDENTIFIER);

            // ��������
            bw.write(str);
            // ���s
            bw.newLine();

        } catch (IOException e) {
            throw new FileWriteException(e, String.format("�t�@�C���̏������݂Ɏ��s���܂����B�t�@�C����:%s", strPass));

        } finally {

            if (bw != null) {
                // ���鏈��
                bw.close();
            }
        }

    }

    /**
     * �����ԍ��p�t�@�C���̏�������
     * 
     * @param nextAccountNo �̔Ԃ��������ԍ�
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createAccountNoFile(int nextAccountNo) throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(NUMBERING_ACCOUNT_FILE_OUTPUT_PATH);

            FileWriter fw = new FileWriter(strPass);
            bw = new BufferedWriter(fw);

            String str = String.format("%s%d%s", SAVE_IDENTIFIER, nextAccountNo, SAVE_IDENTIFIER);
            // ��������
            bw.write(str);
            // ���s
            bw.newLine();

        } catch (IOException e) {
            throw new FileWriteException(e, String.format("�t�@�C���̏������݂Ɏ��s���܂����B�t�@�C����:%s", strPass));

        } finally {

            if (bw != null) {
                // ���鏈��
                bw.close();
            }
        }

    }

    /**
     * �t�@�C����1�s���������ԍ��ƃ��[�U�[���Ǝc���ɕ�������
     * 
     * @param line �ǂݍ��ݍs
     * @return ������̃��X�g�B1�ڂ������ԍ��A2�ڂ����[�U�[���A3�ڂ��c���B
     */
    private List<String> fileSplit(final String line) {
        char c;
        StringBuilder sb = new StringBuilder();
        List<String> data = new ArrayList<String>();
        boolean singleQuoteFlag = false;
        boolean singleQuoteKeepingFlag = false;
        char[] separation = SAVA_SEPARATION.toCharArray();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();

        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (c == identifier[0] && singleQuoteFlag && singleQuoteKeepingFlag) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
                sb.append(c);
            } else if (c == separation[0] && !singleQuoteFlag) {
                data.add(sb.toString());
                sb.delete(0, sb.length());
            } else if (c == separation[0] && singleQuoteFlag) {
                sb.append(c);
            } else if (c == identifier[0]) {
                singleQuoteFlag = !singleQuoteFlag;
            } else if (c == SAVA_ESCAPE) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
            } else {
                sb.append(c);
            }

            // ���z��ݒ�
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

    /**
     * ���[�U�[�����t�@�C���ɕۑ�
     * 
     * @param list ���[�U�[���
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createFile(final List<Personal> list) throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_FILE_OUTPUT_PATH);

            FileWriter fw = new FileWriter(strPass);
            bw = new BufferedWriter(fw);

            // �����ԍ��A���[�U�[���A���z���J���}��؂�ŘA��
            for (Personal p : list) {
                String str = String.format("%s%s%s%s%s%s%s%s%s%d%s", SAVE_IDENTIFIER, p.getAccountNumber(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                        SAVE_IDENTIFIER, conversionEscape(p.getName()), SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, p.getBalance(),
                        SAVE_IDENTIFIER);
                // ��������
                bw.write(str);
                // ���s
                bw.newLine();
            }

        } catch (IOException e) {
            throw new FileWriteException(e, String.format("�t�@�C���̏������݂Ɏ��s���܂����B�t�@�C����:%s", strPass));

        } finally {

            if (bw != null) {
                // ���鏈��
                bw.close();
            }
        }

    }

    /**
     * �����ԍ��擾
     * 
     * @return �擾���������ԍ�
     * @throws FileReadException
     * @throws IOException
     */
    public String getAccountNo() throws FileReadException, IOException {

        String retValue = null;

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo(NUMBERING_ACCOUNT_FILE_OUTPUT_PATH);

            File file = new File(strPass);

            // �t�@�C�������݂��Ȃ��ꍇ(=����1��ڂ̏ꍇ)
            if (!file.exists()) {
                return "1";
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            if (str != null) {
                // �ǂݎ��
                retValue = accountFileSplit(str);
            }

        } catch (IOException e) {
            throw new FileReadException(e, "�����̔ԗp�t�@�C����ǂݍ��߂܂���ł����B");

        } finally {

            if (br != null) {
                // ���鏈��
                br.close();
            }
        }
        return retValue;
    }

    /**
     * �v���p�e�B�t�@�C���̒l���擾
     * 
     * @param path �擾�������t�@�C���̃p�X
     * @return �擾�����t�@�C���̊i�[�p�X
     * @throws FileNotFoundException
     * @throws FileReadException
     * @throws IOException
     */
    private String getPropertiesInfo(final String path) throws FileNotFoundException, FileReadException, IOException {
        Properties properties = new Properties();

        String strPass = null;
        try {
            InputStream istream = new FileInputStream(INIT_PROPERTIES_PATH);
            properties.load(istream);

            strPass = properties.getProperty(path);

            // �ݒ�t�@�C���ǂݍ��ݎ��s��
            if (strPass == null) {
                throw new FileReadException(new Exception(), "�v���p�e�B�t�@�C���ɋL�ڂ���Ă����`��������܂���ł����B");
            }

        } catch (FileNotFoundException e) {
            throw new FileReadException(e, "�v���p�e�B�t�@�C����������܂���ł����B");
        } catch (IOException e) {
            throw new FileReadException(e, "�v���p�e�B�t�@�C���̓ǂݍ��݂Ɏ��s���܂����B");

        }
        return strPass;
    }

    /**
     * �{�����t�̎擾
     * 
     * @return ������^���t
     */
    private String getToday() {
        Calendar cl = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH);
        return sdf.format(cl.getTime());
    }

    /**
     * ���͒l���G�X�P�[�v���������ĕԂ�
     * 
     * @param input ���͒l
     * @return �G�X�P�[�v����
     */
    private String conversionEscape(final String input) {
        char c;
        StringBuilder sb = new StringBuilder();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == identifier[0]) {
                sb.append(SAVA_ESCAPE);
            }
            sb.append(c);

        }
        return sb.toString();
    }

    /**
     * ���s�ς݂̍ŐV�����ԍ��̎擾
     * 
     * @param line �ǂݍ��ݍs
     * @return ���s�ς݂̍ŐV�����ԍ��B
     */
    private String accountFileSplit(final String line) {
        char c;
        StringBuilder sb = new StringBuilder();
        boolean singleQuoteFlag = false;
        boolean singleQuoteKeepingFlag = false;
        char[] separation = SAVA_SEPARATION.toCharArray();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();

        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (c == identifier[0] && singleQuoteFlag && singleQuoteKeepingFlag) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
                sb.append(c);
            } else if (c == separation[0] && !singleQuoteFlag) {
                sb.delete(0, sb.length());
            } else if (c == separation[0] && singleQuoteFlag) {
                sb.append(c);
            } else if (c == identifier[0]) {
                singleQuoteFlag = !singleQuoteFlag;
            } else if (c == SAVA_ESCAPE) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
            } else {
                sb.append(c);
            }

        }

        return sb.toString();

    }

    /**
     * ����������擾
     * 
     * @return �O����͂������[�U�[���
     * @throws FileReadException
     * @throws IOException
     */
    public List<AccountHistory> getAccountHistory() throws FileReadException, IOException {

        List<AccountHistory> list = new ArrayList<>();

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_HISTORY_FILE_OUTPUT_PATH);

            File file = new File(strPass);

            // �t�@�C�������݂��Ȃ��ꍇ(=����1��ڂ̏ꍇ)
            if (!file.exists()) {
                return list;
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {

                List<String> splitList = fileSplit(str);

                AccountHistory line = new AccountHistory();

                line.setDate(StringToDate(splitList.get(0)));
                line.setAccountNumber(Integer.parseInt(splitList.get(1)));
                line.setClassification(Bank.convertBank(splitList.get(2)));
                line.setTransactionAmount(Integer.parseInt(splitList.get(3)));
                line.setBalance(Integer.parseInt(splitList.get(4)));

                list.add(line);
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(e, "�O��̗�����ۑ������t�@�C����ǂݍ��߂܂���ł����B");

        } finally {

            if (br != null) {
                // ���鏈��
                br.close();
            }
        }
        return list;
    }

    /**
     * ������̓��t��Date�^�̓��t�ɕϊ�����
     * 
     * @param date ������̓��t
     * @return Date�^�̓��t
     */
    private Date StringToDate(final String date) {
        if (date == null) {
            return null;
        }
        // �����񂩂���t�^�ϊ�
        SimpleDateFormat tempSdf = new SimpleDateFormat(DATE_OF_BIRTH);
        Date formatDate = new Date();
        try {
            formatDate = tempSdf.parse(date);
        } catch (ParseException e) {
            // �T�[�o�[�Őݒ肷��̂ł��肦�Ȃ�
        }
        return formatDate;
    }
}