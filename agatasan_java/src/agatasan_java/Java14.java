package agatasan_java;

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
import java.util.Scanner;

/**
 * Java14�N���X Java�ۑ�P�S(�������K�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 */
public class Java14 {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �X�L���i�[�i�R���\�[�����́j */
    private static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";
    /** ���t�`�� �FyyyyMMdd */
    private static final String DATE_OF_BIRTH = "yyyyMMdd";
    /** ���t�`�� �Fyyyy�NMM��dd�� */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy�NMM��dd��";
    /** �͈͓��̃��b�Z�[�W */
    private static final String WITHIN_RANGE = "%d�`%d�͈̔͂œ��͂��Ă��������B";
    /** �z��O�̃��b�Z�[�W */
    private static final String UNEXPECTED_ERR = "�z�肳�ꂽ�����͂���܂���B�V�X�e���Ǘ��҂ɘA�����Ă��������B";
    /** �O�ɖ߂�̃��b�Z�[�W */
    private static final String BACK = "0.�O�ɖ߂�";
    /** �I�����̍ŏ��l */
    private static final int RANGE_MIN = 0;
    /** �I�����̍ő�l */
    private static final int RANGE_MAX = 4;
    /** �t�@�C���o�̓p�X */
    private static final String ACCOUNT_FILE_OUTPUT_PATH = "ACCOUNT_FILE_OUTPUT_PATH";
    /** �̔ԗp�����t�@�C���o�̓p�X */
    private static final String NUMBERING_ACCOUNT_FILE_OUTPUT_PATH = "NUMBERING_ACCOUNT_FILE_OUTPUT_PATH";
    /** ��������t�@�C���o�̓p�X */
    private static final String ACCOUNT_HISTORY_FILE_OUTPUT_PATH = "ACCOUNT_HISTORY_FILE_OUTPUT_PATH";
    /** �v���p�e�B�ݒ�p�X */
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";
    /** �t�@�C���ۑ����ʎq */
    private static final String SAVE_IDENTIFIER = "'";
    /** �t�@�C����؂莯�ʎq */
    private static final String SAVA_SEPARATION = ",";
    /** �G�X�P�[�v���ʎq */
    private static final char SAVA_ESCAPE = '\\';

    /**
     * ��������
     */
    private static enum Account {

        NEW(1, "������V�K�쐬"), CHANGE(2, "���łɂ����������舵��");

        /** id */
        private final int id;
        /** ���� */
        private final String name;

        /**
         * �R���X�g���N�^
         * 
         * @param id
         */
        private Account(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * ���������\��������擾
         * 
         * @return ���������\��������
         */
        public static String getSelectAccountString() {
            final StringBuffer sb = new StringBuffer();
            for (final Account t : Account.values()) {
                sb.append(t.id).append(".").append(t.name).append("\n");
            }
            return sb.toString();
        }

        /**
         * ���������擾
         * 
         * @param inputAccount���͒l
         * @return �������������݂��Ȃ��ꍇ��null�l
         */
        public static Account convertAccount(final String inputAccount) {
            if (inputAccount == null) {
                return null;
            }

            int account = 0;
            try {
                account = Integer.parseInt(inputAccount);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Account t : Account.values()) {
                if (t.id == account) {
                    return t;
                }
            }
            return null;
        }

    }

    /**
     * ���͂��ꂽ�������[�h�̏�����\�����܂��B
     * 
     */
    public static void main(String[] args) {

        try {

            List<Personal> personalList = getUserInfo();
            do {
                // --------------------------------------------------
                // ����
                // --------------------------------------------------

                // �V�K�E�������̏������[�h����
                final Account account = inputAccount();

                // �V�K�쐬�̏ꍇ
                if (account == Account.NEW) {
                    createAccount(personalList);
                }

                // ��������̏���
                changeAccountInfo(personalList);

            } while (isContinue());

            // ���[�U�[�����t�@�C���ɕۑ�
            createFile(personalList);

        } catch (FileWriteException | FileReadException | IOException e) {
            System.out.println("�����𒆒f���܂����B�V�X�e���Ǘ��҂֖₢���킹���Ă��������B");
        }

        // �I������
        mScanner.close();
        System.out.println("�������I�����܂����B");

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------

    /**
     * ��������̏���
     * 
     * @param personalList ���[�U�[��񃊃X�g
     */
    private static void changeAccountInfo(List<Personal> personalList) {

        do {

            // �C������l���\�������쐬
            String toModifyPersonMsg = displayToCorrectPerson(personalList, "�ǂ̃��[�U�̏��������܂����H");

            // �C������l���̔ԍ����擾
            int personOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (personOfNumber == 0) {
                return;
            }

            int idx = personOfNumber - 1;

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            String modifyName = personalList.get(idx).getName();
            Personal personal = personalList.get(idx);

            try {

                do {

                    // �C�����鑮���̔ԍ����擾
                    String toCorrectPropertyMsg = displayToCorrectProperty(modifyName);

                    // �C�����郆�[�U�[���ԍ����擾
                    int propertyOfNumber = getModifyUserInfo(toCorrectPropertyMsg);

                    // 0 �̏ꍇ�A�C���l����I�����鏈���܂Ŗ߂�
                    if (propertyOfNumber == 0) {
                        break;
                    }

                    // --------------------------------------------------
                    // �����E�o��
                    // --------------------------------------------------

                    switch (Bank.convertBank(String.valueOf(propertyOfNumber))) {
                        case DEPOSIT:
                            // ��������
                            depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // �U������
                            transfer(personal, personalList);
                            break;
                        case BALANCE:
                            // �c���\������
                            displayBalance(personal.getBalance());
                            break;
                        case HISTORY:
                            // ���������\��
                            displayHistory(personal.getAccountNumber());
                            break;
                        default:
                            System.out.println(UNEXPECTED_ERR);
                            break;
                    }

                } while (true);

            } catch (FileReadException | FileWriteException | IOException e) {
                System.out.println("�����𒆒f���܂����B�V�X�e���Ǘ��҂֖₢���킹���Ă��������B");
            }

        } while (true);

    }

    /**
     * �U������
     * 
     * @param transfer     �U����
     * @param personalList ���[�U�[��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void transfer(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            String toModifyPersonMsg = displayToCorrectPerson(personalList, "�ǂ̃��[�U�ɐU�����܂����H");

            // �U����̐l���̔ԍ����擾
            int payeeOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (payeeOfNumber == 0) {
                return;
            }

            int idx = payeeOfNumber - 1;

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            String payeeName = personalList.get(idx).getName();
            Personal payee = personalList.get(idx);

            // �����ɐU���͕s��
            if (transfer.getName().equals(payee.getName())) {
                System.out.println("�������ɂ͐U���߂܂���B");
                continue;
            }
            // �U�����擾
            int inputDeposit = getTransferInfo(transfer, payee);

            // �U�����̎c����ݒ�
            transfer.setBalance(transfer.getBalance() - inputDeposit);
            // �U����̎c����ݒ�
            payee.setBalance(payee.getBalance() + inputDeposit);

            // �U���������̍X�V
            writeHistory(transfer.getAccountNumber(), Bank.TRANSFER.getId(), inputDeposit);
            // �U���旚���̍X�V
            writeHistory(payee.getAccountNumber(), Bank.TRANSFER.getId(), inputDeposit);

            System.out.println(String.format("%S����ɐU���������܂����B", payeeName));

            break;
        } while (true);

    }

    /**
     * �c���\��
     * 
     * @param balance �c��
     */
    private static void displayBalance(final int balance) {
        System.out.println(String.format("�c���́A%,d�~�ł�", balance));
    }

    /**
     * ���������\��
     * 
     * @param accountNumber �����ԍ�
     * @throws FileReadException
     * @throws IOException
     */
    private static void displayHistory(final int accountNumber) throws FileReadException, IOException {

        // �����f�[�^�擾
        List<AccountHistory> historyList = getAccountHistory();

        // �����̒��Ō����ԍ���v�̂��̂�\��
        matchAccountNo(accountNumber, historyList);

    }

    /**
     * �\���Ώی����ԍ��ƈ�v���闚����\��
     * 
     * @param accountNumber �\���Ώی����ԍ�
     * @param historyList   ����������X�g
     */
    private static void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // ��������
        Boolean isExistHistory = false;

        sb.append("***********************************").append("\n");
        sb.append("��������A�敪�A���z").append("\n");

        for (AccountHistory history : historyList) {
            if (accountNumber == history.getAccountNumber()) {

                if (isExistHistory == false) {
                    isExistHistory = true;
                }

                sb.append(dateToString(history.getDate())).append("�A").append(history.getClassification().getName()).append("�A")
                        .append(String.format("%,d�~", history.getTransactionAmount())).append("\n");

            }

        }

        sb.append("***********************************");

        System.out.println(isExistHistory == true ? sb.toString() : "����������͂���܂���B");
    }

    /**
     * ���t�𕶎���ɕϊ�
     * 
     * @param date ���t
     * @return ������^���t
     */
    private static String dateToString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return dateFormat.format(date);
    }

    /**
     * ����������擾
     * 
     * @return �O����͂������[�U�[���
     * @throws FileReadException
     * @throws IOException
     */
    private static List<AccountHistory> getAccountHistory() throws FileReadException, IOException {

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
     * �t�@�C����1�s���������ԍ��ƃ��[�U�[���Ǝc���ɕ�������
     * 
     * @param line �ǂݍ��ݍs
     * @return ������̃��X�g�B1�ڂ������ԍ��A2�ڂ����[�U�[���A3�ڂ��c���B
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

            // ���z��ݒ�
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

    /**
     * ���s�ς݂̍ŐV�����ԍ��̎擾
     * 
     * @param line �ǂݍ��ݍs
     * @return ���s�ς݂̍ŐV�����ԍ��B
     */
    private static String accountFileSplit(final String line) {
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
     * ���[�U�[�����t�@�C���ɕۑ�
     * 
     * @param list ���[�U�[���
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void createFile(final List<Personal> list) throws FileWriteException, FileReadException, IOException {

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
     * �����I��\�������擾
     */
    private static String getDisplayString(String msg) {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("������I�����Ă�������").append("\n");
        sb.append(msg);
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * �����̏�������
     */
    private static Account inputAccount() {
        Account account = null;
        String displayMsg = getDisplayString(Account.getSelectAccountString());
        do {
            account = Account.convertAccount(inputStr(displayMsg));
            if (account == null) {
                System.out.println("�Y�����鏈����������܂���ł����B");
            }

        } while (account == null);
        return account;
    }

    /**
     * �����p���m�F
     * 
     * @return isProcessingContinue �����p����True�B�����I����false�B
     */
    private static boolean isContinue() {
        boolean isCheck = false;

        String displayMsg = String.format("�����𑱂��܂����H(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END);

        do {
            String input = inputStr(displayMsg);

            // ���͒l�̔���
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    return true;
                case PROCESSING_END:
                    return false;
                default:
                    System.out.println(String.format("(%s/%s)�ȊO�����͂���܂����B\n�ē��͂����肢���܂��B", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return false;
    }

    /**
     * ��������
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    private static String inputStr(final String inputMsg) {
        String input = null;
        boolean isCheck = false;

        System.out.println(inputMsg);
        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * �����V�K�쐬
     * 
     * @param personalList ���[�U�[��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void createAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �������擾
        String inputName = inputName();

        // �����ԍ��͐V�K�̔�
        int accountNumber = createNewAccountNo();

        // �l��ݒ�
        Personal personal = new Personal(inputName, accountNumber, 0);
        personalList.add(personal);
        // �������X�V
        depositMoney(personalList.size() - 1, personalList);

        System.out.println("������V�K�o�^���܂����B");

    }

    /**
     * �����ԍ��V�K�̔�
     * 
     * @return �V�K�̔Ԕԍ�
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static int createNewAccountNo() throws FileWriteException, FileReadException, IOException {
        // �̔ԗp�̃t�@�C����ǂݍ���
        // +1����
        int nextAccountNo = sumUpAccountNo(getAccountNo());

        // �̔Ԃ����ԍ����̔ԗp�̃t�@�C���ɏ�������
        createAccountNoFile(nextAccountNo);

        // �ԍ��𕶎���ɕϊ����ĕԂ�
        return nextAccountNo;

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
     * �����ԍ����Z����
     * 
     * @param accountNo �����ԍ�
     * @return ���Z���������ԍ�
     */
    private static int sumUpAccountNo(String accountNo) {
        // �������琔�l�ɕϊ�
        int retVlalue = Integer.parseInt(accountNo);
        // ���Z
        return ++retVlalue;

    }

    /**
     * �����ԍ��p�t�@�C���̏�������
     * 
     * @param nextAccountNo �̔Ԃ��������ԍ�
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void createAccountNoFile(int nextAccountNo) throws FileWriteException, FileReadException, IOException {

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
     * ��������
     * 
     * @param depositIdx   �I�����ꂽ�C���f�b�N�X
     * @param personalList ���[�U�[��񃊃X�g
     * @return ������̎c��
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static int depositMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �������擾
        int inputDeposit = getDeposit();

        // �c���̍��v
        int sum = inputDeposit + personalList.get(depositIdx).getBalance();
        displayBalance(sum);

        // �c���̐ݒ�
        personalList.get(depositIdx).setBalance(sum);

        // �����̍X�V
        createFile(personalList);

        // ��������
        writeHistory(personalList.get(depositIdx).getAccountNumber(), Bank.DEPOSIT.getId(), inputDeposit);

        return sum;
    }

    /**
     * �������̎擾
     * 
     * @return �������z
     */
    private static int getDeposit() {
        int inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000));

        return inputDeposit;
    }

    /**
     * �U�����擾
     * 
     * @param transfer �U�������[�U�[���
     * @param payee    �U���惆�[�U�[���
     * @return �U�����z
     */
    private static int getTransferInfo(final Personal transfer, final Personal payee) {
        int inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000) || canPay(transfer, inputDeposit));

        return inputDeposit;
    }

    /**
     * �c�����ŕ����邩�̃`�F�b�N
     * 
     * @param transfer     �U����̃��[�U�[���
     * @param inputDeposit �U�����z
     * @return true:�c�����ŕ�����
     */
    private static boolean canPay(final Personal transfer, final int inputDeposit) {

        // �����̌������略���Ȃ��ꍇ
        if (transfer.getBalance() - inputDeposit < 0) {
            System.out.println(String.format("�������̎c��%,d�~���ŐU�荞��ł��������B", transfer.getBalance()));
            return true;
        }
        return false;
    }

    /**
     * ����������̏�������
     * 
     * @param accountNumber �����ԍ�
     * @param id            ��舵���敪
     * @param balance       �c��
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void writeHistory(final int accountNumber, final int id, final int balance)
            throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_HISTORY_FILE_OUTPUT_PATH);

            // �ǉ���������
            FileWriter fw = new FileWriter(strPass, true);
            bw = new BufferedWriter(fw);

            // ���t�A�ԍ��A��舵���敪�A���z
            String str = String.format("%s%s%s%s%s%d%s%s%s%d%s%s%s%d%s", SAVE_IDENTIFIER, getToday(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, accountNumber, SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, id, SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, balance, SAVE_IDENTIFIER);

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
     * ���͐��l�͈͊O�`�F�b�N
     * 
     * @param number ���͂��ꂽ���l
     * @param min    �ŏ��l
     * @param max    �ő�l
     * @return ���͂��ꂽ���l���͈͊O�̏ꍇ��True�B�͈͓��̏ꍇ��false�B
     */
    private static boolean isOutOfRange(final int number, final int min, final int max) {

        // �͈͊O�̏ꍇ
        if (!isWithinRange(number, min, max)) {
            System.out.println(String.format(WITHIN_RANGE, min, max));
            return true;
        }
        return false;
    }

    /**
     * �C������l���\�������쐬
     * 
     * @param userList ���[�U�[��񃊃X�g
     * @param msg
     * @return �\������
     */
    private static String displayToCorrectPerson(final List<Personal> userList, String msg) {

        System.out.println(msg);

        // ���앶���\��
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        sb.append(BACK).append("\n");

        int cnt = 1;
        for (Personal u : userList) {
            sb.append(cnt++).append(".").append(u.getName()).append("\n");
        }
        sb.append("---------------------------").append("\n");

        return sb.toString();
    }

    /**
     * �C�����鑮���\�������쐬
     * 
     * @param modifyName �C������l������
     * @return �\������
     */
    private static String displayToCorrectProperty(final String correctName) {

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("%S������C�����܂��B", correctName)).append("\n");
        sb.append("�ǂ̏����C�����܂����H").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(BACK).append("\n");
        sb.append(Bank.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }

    /**
     * ���l����(int�^)
     * 
     * @param inputMsg ���̓R���\�[���ɕ\�����镶��
     * @return ���͒l
     */
    private static int inputInt(final String inputMsg) {
        int num = 0;
        String input = null;
        boolean isCheck = false;

        if (inputMsg != null) {
            System.out.println(inputMsg);
        }

        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }

        } while (!isCheck);
        return num;
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return true:�ŏ��l�`�ő�l�͈͓̔��ɂ���
     */
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * ���͂��ꂽ�������擾
     * 
     * @return ����
     */
    private static String inputName() {
        return inputStr("��������͂��Ă�������");
    }

    /**
     * ���͂��ꂽ���z���擾
     * 
     * @return ���z
     */
    private static int inputDeposit() {
        return inputInt("�������z����͂��Ă�������");
    }

    /**
     * ��������Ώۃ��[�U�[���擾
     * 
     * @param toCorrectPersonMsg �R���\�[���ɕ\�����镶��
     * @param userList           ���[�U�[��񃊃X�g
     * @return �������郆�[�U�[�ԍ�
     */
    private static int getCorrectPerson(final String toCorrectPersonMsg, final List<Personal> userList) {
        int personOfNumber = 0;
        do {
            // ��������l���̔ԍ����擾
            personOfNumber = inputInt(toCorrectPersonMsg);

        } while (isOutOfRange(personOfNumber, RANGE_MIN, userList.size()));
        return personOfNumber;
    }

    /**
     * �C����������擾
     * 
     * @param toModifyPropertyMsg �R���\�[���ɕ\�����镶��
     * @return �C���ԍ�
     */
    private static int getModifyUserInfo(final String toModifyPropertyMsg) {
        int propertyOfNumber = 0;
        do {
            // ���͒l���擾
            propertyOfNumber = inputInt(toModifyPropertyMsg);

        } while (isOutOfRange(propertyOfNumber, RANGE_MIN, RANGE_MAX));
        return propertyOfNumber;
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

}
