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
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Java11�N���X Java�ۑ�P�Q(�I�u�W�F�N�g�w���R�j
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
    /** �f�[�^�Ȃ��̃��b�Z�[�W */
    private static final String NO_DATA = "�\������f�[�^������܂���B";
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
     * �������[�h
     */
    private static enum Bank {

        DEPOSIT(1, "����"), TRANSFER(2, "�U��"), BALANCE(3, "�c���\��"), HISTORY(9, "���������\��");

        /** id */
        private final int id;
        /** ���� */
        private final String name;

        /**
         * �R���X�g���N�^
         * 
         * @param id
         */
        private Bank(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * ���[�h�\��������擾
         * 
         * @return ���[�h�\��������
         */
        public static String getSelectBankString() {
            final StringBuffer sb = new StringBuffer();
            for (final Bank t : Bank.values()) {
                sb.append(t.id).append(".").append(t.name).append("\n");
            }
            return sb.toString();
        }

        /**
         * ���[�h�擾
         * 
         * @param inputBank���͒l
         * @return ���[�h�����݂��Ȃ��ꍇ��null�l
         */
        public static Bank convertBank(final String inputBank) {
            if (inputBank == null) {
                return null;
            }

            int bank = 0;
            try {
                bank = Integer.parseInt(inputBank);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Bank t : Bank.values()) {
                if (t.id == bank) {
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
                    personalList.add(inputUserInfo());
                }

                // ���[�U�[�I��

                // �C������l���\�������쐬
                String toModifyPersonMsg = displayToCorrectPerson(personalList, "�ǂ̃��[�U�̏��������܂����H");

                // �C������l���̔ԍ����擾
                int personOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

                // 0�̏ꍇ�́A�ŏ��ɖ߂�
                if (personOfNumber == 0) {
                    continue;
                }

                int idx = personOfNumber - 1;

                // �ʂ̏���
                changePersonal(personalList, idx);

            } while (isContinue());

            // ����ĊJ���p�Ƀ��[�U�[�����t�@�C���ɕۑ�
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

    private static void changePersonal(List<Personal> personalList, int idx) {

        // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
        String modifyName = personalList.get(idx).getName();
        Personal personal = personalList.get(idx);

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
                    personal.setBalance(depositMoney(personal.getBalance()));
                    break;
                case TRANSFER:
                    // �U������
//                    personalList = modifyUserInfo(personalList);
                    break;
                case BALANCE:
                    // �c���\������
                    displayBalance(personal.getBalance());
                    break;
                case HISTORY:
                    // ���������\��
                    // TODO
                    displayHistory(personal.getAccountNumber());
                    break;
                default:
                    System.out.println(UNEXPECTED_ERR);
                    break;
            }

        } while (true);

    }

    private static void displayBalance(int balance) {
        System.out.println(String.format("�c���́A%,d�~�ł�", balance));
    }

    private static void displayHistory(String accountNumber) {

        // �����f�[�^�擾

        // �����̒��Ō����ԍ���v�̂��̂��擾

        // �\��

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
            strPass = getPropertiesInfo();

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

                line.setAccountNumber(splitList.get(0));
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
     * �t�@�C����1�s�������[�U�[���Ƌ��z�ɕ�������
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
     * �v���p�e�B�t�@�C���̒l���擾
     * 
     * @return ���[�U�[��񂪋L�ڂ���ăt�@�C���̊i�[�p�X
     * @throws FileNotFoundException
     * @throws FileReadException
     * @throws IOException
     */
    private static String getPropertiesInfo() throws FileNotFoundException, FileReadException, IOException {
        Properties properties = new Properties();

        String strPass = null;
        try {
            InputStream istream = new FileInputStream(INIT_PROPERTIES_PATH);
            properties.load(istream);

            strPass = properties.getProperty(ACCOUNT_FILE_OUTPUT_PATH);

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
            strPass = getPropertiesInfo();

            FileWriter fw = new FileWriter(strPass);
            bw = new BufferedWriter(fw);

            // �����ԍ��A���[�U�[���A���z���J���}��؂�ŘA��
            for (Personal p : list) {
                String str = String.format("%s%s%s%s%s%s%s%s%d%s", SAVE_IDENTIFIER, p.getAccountNumber(), SAVE_IDENTIFIER, SAVE_IDENTIFIER,
                        conversionEscape(p.getName()), SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, p.getBalance(), SAVE_IDENTIFIER);
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
     * ��������
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
     * ��������
     */
    private static Bank inputBank() {
        Bank bank = null;
        String displayMsg = displayToCorrectProperty(Bank.getSelectBankString());
        do {
            bank = Bank.convertBank(inputStr(displayMsg));
            if (bank == null) {
                System.out.println("�Y�����鏈����������܂���ł����B");
            }

        } while (bank == null);
        return bank;
    }

//    /**
//     * ���[�h�I��\�������擾
//     */
//    private static String getDisplayModeString() {
//        final StringBuffer sb = new StringBuffer();
//        sb.append("***********************************").append("\n");
//        sb.append("�������[�h��I�����Ă�������").append("\n");
//        sb.append(Mode.getSelectModeString());
//        sb.append("***********************************");
//        return sb.toString();
//    }
//
//    /**
//     * ���[�h����
//     */
//    private static Mode inputMode() {
//        Mode mode = null;
//        String displayMsg = getDisplayModeString();
//        do {
//            mode = Mode.convertMode(inputStr(displayMsg));
//            if (mode == null) {
//                System.out.println("�Y�����鏈�����[�h��������܂���ł����B");
//            }
//
//        } while (mode == null);
//        return mode;
//    }

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
     * ���[�U�[���̎擾
     * 
     * @return ���[�U�[���
     */
    private static Personal inputUserInfo() {

        // �������擾
        String inputName = inputName();
        // ���z�̎擾
        int inputDeposit = depositMoney(0);
        // �����ԍ��͐V�K�̔�
        // TODO
        String balance = "001";

        // �l��ݒ�
        Personal personal = new Personal(inputName, balance, inputDeposit);

        System.out.println("������V�K�o�^���܂����B");

        return personal;
    }

    private static int depositMoney(int balance) {
        int inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000));

        // �c���̍��v
        displayBalance(inputDeposit + balance);

        // TODO ��������

        return inputDeposit;
    }

    /**
     * ���͐��l�͈͊O�`�F�b�N
     * 
     * @param number ���͂��ꂽ���l
     * @param min    �ŏ��l
     * @param max    �ő�l
     * @return ���͂��ꂽ���l���͈͊O�̏ꍇ��True�B�͈͓��̏ꍇ��false�B
     */
    public static boolean isOutOfRange(final int number, final int min, final int max) {

        // �͈͊O�̏ꍇ
        if (!isWithinRange(number, min, max)) {
            System.out.println(String.format(WITHIN_RANGE, min, max));
            return true;
        }
        return false;
    }

    /**
     * ���͂��ꂽ���t�����݂��邩�̃`�F�b�N
     * 
     * @param birthDay ���͂��ꂽ�N����
     * @return �����ȓ��t��True�B�s���ȓ��t��false�B
     */
    private static boolean isConsistency(String birthDay) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH);
        sdf.setLenient(false);
        try {
            sdf.parse(birthDay);
        } catch (ParseException e) {
            System.out.println("���������t�ł͂���܂���");
            return false;
        }
        return true;
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
        sb.append(Personal.getSelectPersonalString());
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
     * @param userList           ���[�U�[���X�g
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
     * userList���󂩂̔�����s��
     * 
     * @param userList ���[�U�[���X�g
     * @return �n���ꂽ���X�g����̏ꍇ��True�B��łȂ��ꍇ��false�B
     */
    private static boolean isEmpty(final List<User> userList) {

        if (userList == null || userList.size() == 0) {
            return true;
        }
        return false;
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
