package agatasan_java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Java10�N���X Java�ۑ�P�O(�I�u�W�F�N�g�w���P�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/11/03 �V�K�쐬
 * @version 1.1 2020/11/07 No.60�`63�w�E�Ή�
 * @version 1.2 2020/11/14 REV2��ڎw�E�Ή�
 * @version 1.3 2020/11/21 REV3��ڎw�E�Ή�
 * @version 1.4 2020/11/27 No.74�Ďw�E�Ή�
 */
public class Java10 {

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

    /**
     * �������[�h
     */
    private static enum Mode {

        ENTRY(1, "���[�U�o�^"), ALL(9, "�S���[�U�\��");

        /** id */
        private final int id;
        /** ���� */
        private final String name;

        /**
         * �R���X�g���N�^
         * 
         * @param id
         */
        private Mode(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * ���[�h�\��������擾
         * 
         * @return ���[�h�\��������
         */
        public static String getSelectModeString() {
            final StringBuffer sb = new StringBuffer();
            for (final Mode t : Mode.values()) {
                sb.append(t.id).append(".").append(t.name).append("\n");
            }
            return sb.toString();
        }

        /**
         * ���[�h�擾
         * 
         * @param inputMode���͒l
         * @return ���[�h�����݂��Ȃ��ꍇ��null�l
         */
        public static Mode convertMode(final String inputMode) {
            if (inputMode == null) {
                return null;
            }

            int mode = 0;
            try {
                mode = Integer.parseInt(inputMode);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Mode t : Mode.values()) {
                if (t.id == mode) {
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

        List<User> userList = new ArrayList<User>();
        do {
            // --------------------------------------------------
            // ����
            // --------------------------------------------------
            // �������[�h����
            final Mode mode = inputMode();
            // --------------------------------------------------
            // �����E�o��
            // --------------------------------------------------

            switch (mode) {
                case ENTRY:
                    // �o�^����
                    userList.add(inputUserInfo());
                    break;
                case ALL:
                    // �S�̕\��
                    getDisplayUserInfo(userList);
                    break;
                default:
                    System.out.println("�z�肳�ꂽ�����͂���܂���B�V�X�e���Ǘ��҂ɘA�����Ă��������B");
                    break;
            }

        } while (isContinue());

        // �I������
        mScanner.close();
        System.out.println("�������I�����܂����B");

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * ���[�h�I��\�������擾
     */
    private static String getDisplayModeString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("�������[�h��I�����Ă�������").append("\n");
        sb.append(Mode.getSelectModeString());
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * ���[�h����
     */
    private static Mode inputMode() {
        Mode mode = null;
        String displayMsg = getDisplayModeString();
        do {
            mode = Mode.convertMode(inputStr(displayMsg));
            if (mode == null) {
                System.out.println("�Y�����鏈�����[�h��������܂���ł����B");
            }

        } while (mode == null);
        return mode;
    }

    /**
     * ���ʓ���
     */
    private static User.Sex inputSex(String msg) {
        User.Sex sex = null;

        do {
            sex = User.Sex.convertSex(inputStr(msg));
            if (sex == null) {
                System.out.println("�Y�����鐫�ʂ�������܂���ł����B");
            }

        } while (sex == null);
        return sex;
    }

    /**
     * �����p���m�F
     * 
     * @return isProcessingContinue �����p����True�B�����I����false�B
     */
    public static boolean isContinue() {
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
    public static String inputStr(final String inputMsg) {
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
    public static User inputUserInfo() {

        // �������擾
        String inputName = inputStr("��������͂��Ă�������");
        // ���ʂ̎擾
        User.Sex sex = inputSex(String.format("���ʂ���͂��Ă�������(%S)", User.Sex.getSelectSexString()));
        // �a�����̎擾
        Date birthday = inputBirthday(String.format("���N��������͂��Ă�������(%s)", DATE_OF_BIRTH.toLowerCase()));
        // ���ӌ���̎擾
        String inputLanguage = inputStr("���ӌ������͂��Ă�������");

        // �l��ݒ�
        User user = new User(inputName, sex, birthday, inputLanguage);

        return user;
    }

    /**
     * ���͂��ꂽ���t�����݂��邩�̃`�F�b�N
     * 
     * @param birthDay ���͂��ꂽ�N����
     * @return �����ȓ��t��True�B�s���ȓ��t��false�B
     */
    public static boolean isConsistency(String birthDay) {

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
     * ���[�U�[���\��
     * 
     * @param userList ���[�U�[��񃊃X�g
     */
    private static void getDisplayUserInfo(List<User> userList) {

        // userList����̏ꍇ
        if (userList == null || userList.size() == 0) {
            System.out.println("�\������f�[�^������܂���B");
            return;
        }

        for (User ur : userList) {
            System.out.println("---------------------------");
            System.out.println(String.format("���@�@���F%S", ur.getName()));
            System.out.println(String.format("���@�@�ʁF%S", User.Sex.convertSexNameById(ur.getSex().getId())));
            System.out.println(String.format("���N�����F%S(%d��)", conversionBirthday(ur.getBirthday()), calcAge(ur.getBirthday())));
            System.out.println(String.format("���ӌ���F%S", ur.getFavoriteLanguage()));
            System.out.println("---------------------------");
        }
    }

    /**
     * ���N�����ɔN���������ĕԂ�
     * 
     * @param birthday ���N����
     * @return yyyy�NMM��dd��
     */
    private static String conversionBirthday(Date birthday) {

        // ���t����N�����ɕϊ�
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return sdf.format(birthday);

    }

    /**
     * �a��������N����v�Z���ĕԂ�
     * 
     * @param formatDate �a����
     * @return �N��
     */
    public static int calcAge(Date formatDate) {
        Date dateObj = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH);
        return (Integer.parseInt(sdf.format(dateObj)) - Integer.parseInt(sdf.format(formatDate))) / 10000;
    }

    /**
     * ������̓��t��Date�^�̓��t�ɕϊ�����
     * 
     * @param date ������̓��t
     * @return Date�^�̓��t
     */
    public static Date StringToDate(String date) {
        if (date == null) {
            return null;
        }
        // �����񂩂���t�^�ϊ�
        SimpleDateFormat tempSdf = new SimpleDateFormat(DATE_OF_BIRTH);
        Date formatDate = new Date();
        try {
            formatDate = tempSdf.parse(date);
        } catch (ParseException e) {
            // ���͎��Ƀ`�F�b�N���Ă���̂ł��肦�Ȃ�
        }
        return formatDate;
    }

    /**
     * �a��������
     */
    private static Date inputBirthday(String msg) {
        String birthdayStr = null;
        do {
            birthdayStr = inputStr(msg);
        } while (!isConsistency(birthdayStr));

        Date date = StringToDate(birthdayStr);

        return date;
    }
}
