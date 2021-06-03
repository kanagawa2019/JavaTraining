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

/**
 * �t�@�C������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 * @version 1.2 2021/05/31 No.123�`131�w�E�Ή�
 * @version 1.3 2021/06/01 No.126,128,130,131�w�E�Ή�
 * @version 1.4 2021/06/02 No.132�`136�w�E�Ή�
 *
 */
public class FileProcessing {
    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
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

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * �O��̃��[�U�[�����擾
     * 
     * @return �O����͂������[�U�[���
     * @throws FileReadException
     * @throws IOException
     */
    public static List<Personal> getUserInfo() throws FileReadException, IOException {

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

            br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while (str != null) {
                List<String> splitList = fileSplit(str);
                list.add(new Personal(
                        splitList.get(1),
                        Integer.parseInt(splitList.get(0)),
                        Long.parseLong(splitList.get(2)))
                        );

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
    public static void writeHistory(final int accountNumber, final int id, final long transactionAmount, final long balance)
            throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_HISTORY_FILE_OUTPUT_PATH);

            // �ǉ���������
            bw = new BufferedWriter(new FileWriter(strPass, true));

            // ���t�A�ԍ��A��舵���敪�A������z�A�c��
            String str = String.format(
                    "%s%s%s%s%s%d%s%s%s%d%s%s%s%d%s%s%s%d%s",
                    SAVE_IDENTIFIER, getToday(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, accountNumber, SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, id, SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, transactionAmount, SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, balance, SAVE_IDENTIFIER
                    );

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
     * �t�@�C���ɕۑ�
     * 
     * @param isUser        �ۑ��Ώۂ����[�U��񃊃X�g�Ȃ�true
     * @param list          ���[�U��񃊃X�g
     * @param nextAccountNo
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void createFile(final boolean isUser, final List<Personal> list, final int nextAccountNo)
            throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(isUser ? ACCOUNT_FILE_OUTPUT_PATH : NUMBERING_ACCOUNT_FILE_OUTPUT_PATH);

            bw = new BufferedWriter(new FileWriter(strPass));

            if (isUser) {
                // �����ԍ��A���[�U�[���A���z���J���}��؂�ŘA��
                for (Personal p : list) {
                    String str = String.format(
                            "%s%s%s%s%s%s%s%s%s%d%s",
                            SAVE_IDENTIFIER, p.getAccountNumber(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                            SAVE_IDENTIFIER, conversionEscape(p.getName()), SAVE_IDENTIFIER, SAVA_SEPARATION,
                            SAVE_IDENTIFIER, p.getBalance(), SAVE_IDENTIFIER
                            );
                    // ��������
                    bw.write(str);
                    // ���s
                    bw.newLine();
                }
            } else {
                String str = String.format("%s%d%s", SAVE_IDENTIFIER, nextAccountNo, SAVE_IDENTIFIER);
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
    public static String getAccountNo() throws FileReadException, IOException {

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
                retValue = fileSplit(str).get(0);
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
     * ����������擾
     * 
     * @return �O����͂������[�U�[���
     * @throws FileReadException
     * @throws IOException
     */
    public static List<AccountHistory> getAccountHistory() throws FileReadException, IOException {

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

            br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while (str != null) {

                List<String> splitList = fileSplit(str);
                list.add(new AccountHistory(
                        Integer.parseInt(splitList.get(1)),
                        Long.parseLong(splitList.get(4)),
                        StringToDate(splitList.get(0)),
                        AccountHandlingMenu.convertBank(splitList.get(2)),
                        Long.parseLong(splitList.get(3)))
                        );

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

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * �v���p�e�B�t�@�C���̒l���擾
     * 
     * @param path �擾�������t�@�C���̃p�X
     * @return �擾�����t�@�C���̊i�[�p�X
     * @throws FileNotFoundException
     * @throws FileReadException
     * @throws IOException
     */
    private static String getPropertiesInfo(final String path) throws FileNotFoundException, FileReadException, IOException {
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
    private static String getToday() {
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
    private static String conversionEscape(final String input) {
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
     * ������̓��t��Date�^�̓��t�ɕϊ�����
     * 
     * @param date ������̓��t
     * @return Date�^�̓��t
     */
    private static Date StringToDate(final String date) {
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

    /**
     * �t�@�C����1�s���������ԍ��ƃ��[�U�[���Ǝc���ɕ�������
     * 
     * @param line �ǂݍ��ݍs
     * @return ������̃��X�g
     */
    private static List<String> fileSplit(final String line) {
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

            // �Ō�̍��ڂ�ݒ�
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

}
