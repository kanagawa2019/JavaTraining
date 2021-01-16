package agatasan_java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Java11�N���X Java�ۑ�P�Q(�I�u�W�F�N�g�w���R�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 */
public class Java12 {

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

    /**
     * �������[�h
     */
    private static enum Mode {

        ENTRY(1, "���[�U�o�^"), MODIFY(2, "���|�U�[�C��"), DELETE(3, "���|�U�[�폜"), ALL(9, "�S���[�U�\��");

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
                case MODIFY:
                    // �C������
                    userList = modifyUserInfo(userList);
                    break;
                case DELETE:
                    // �폜����
                    userList = deleteUserInfo(userList);
                    break;
                case ALL:
                    // �S�̕\��
                    getDisplayUserInfo(userList);
                    break;
                default:
                    System.out.println(UNEXPECTED_ERR);
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
    private static User inputUserInfo() {

        // �������擾
        String inputName = inputName();
        // ���ʂ̎擾
        User.Sex sex = inputSex();
        // �a�����̎擾
        Date birthday = inputBirthday();
        // ���ӌ���̎擾
        String inputLanguage = inputLanguage();

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
     * ���[�U�[���\��
     * 
     * @param userList ���[�U�[��񃊃X�g
     */
    private static void getDisplayUserInfo(List<User> userList) {

        // userList����̏ꍇ
        if (userList == null || userList.size() == 0) {
            System.out.println(NO_DATA);
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
    private static int calcAge(Date formatDate) {
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
    private static Date StringToDate(String date) {
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

    /**
     * ���[�U�[���̏C��
     * 
     * @param userList ���[�U�[��񃊃X�g
     * @return ���[�U�[��񃊃X�g
     */
    private static List<User> modifyUserInfo(final List<User> userList) {

        // userList����̏ꍇ
        if (isEmpty(userList)) {
            System.out.println(NO_DATA);
            return userList;
        }

        do {
            // �C������l���\�������쐬
            String toModifyPersonMsg = displayToCorrectPerson(userList, "�ǂ̃��[�U���C�����܂����H");

            // �C������l���̔ԍ����擾
            int personOfNumber = getCorrectPerson(toModifyPersonMsg, userList);

            // 0�̏ꍇ�́A�������[�h�ɖ߂�
            if (personOfNumber == 0) {
                return userList;
            }

            int idx = personOfNumber - 1;
            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            String modifyName = userList.get(idx).getName();
            do {

                // �C�����鑮���̔ԍ����擾
                String toModifyPropertyMsg = displayToModifyProperty(modifyName);

                // �C�����郆�[�U�[���ԍ����擾
                int propertyOfNumber = getModifyUserInfo(toModifyPropertyMsg);

                // 0 �̏ꍇ�A�C���l����I�����鏈���܂Ŗ߂�
                if (propertyOfNumber == 0) {
                    break;
                }

                // �C������
                switch (propertyOfNumber) {
                    case User.NUMBER_NAME:
                        String newName = inputName();
                        userList.get(idx).setName(newName);
                        modifyName = newName;
                        break;
                    case User.NUMBER_SEX:
                        userList.get(idx).setSex(inputSex());
                        break;
                    case User.NUMBER_BIRTHDAY:
                        userList.get(idx).setBirthday(inputBirthday());
                        break;
                    case User.NUMBER_FAVORITELANGUAGE:
                        userList.get(idx).setFavoriteLanguage(inputLanguage());
                        break;
                    default:
                        System.out.println(UNEXPECTED_ERR);
                        break;

                }
            } while (true);

        } while (true);
    }

    /**
     * �C������l���\�������쐬
     * 
     * @param userList ���[�U�[��񃊃X�g
     * @param msg
     * @return �\������
     */
    private static String displayToCorrectPerson(final List<User> userList, final String msg) {

        System.out.println(msg);

        // ���앶���\��
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        sb.append(BACK).append("\n");

        int cnt = 1;
        for (User u : userList) {
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
    private static String displayToModifyProperty(final String modifyName) {

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("%S������C�����܂��B", modifyName)).append("\n");
        sb.append("�ǂ̏����C�����܂����H").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(BACK).append("\n");
        sb.append(User.getSelectPropertyString());
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
     * ���͂��ꂽ���ʂ��擾
     * 
     * @return ����
     */
    private static User.Sex inputSex() {
        return inputSex(String.format("���ʂ���͂��Ă�������(%S)", User.Sex.getSelectSexString()));
    }

    /**
     * ���͂��ꂽ�a�������擾
     * 
     * @return �a����
     */
    private static Date inputBirthday() {
        return inputBirthday(String.format("���N��������͂��Ă�������(%s)", DATE_OF_BIRTH.toLowerCase()));
    }

    /**
     * ���͂��ꂽ���ӌ�����擾
     * 
     * @return ���ӌ���
     */
    private static String inputLanguage() {
        return inputStr("���ӌ������͂��Ă�������");
    }

    /**
     * ��������Ώۃ��[�U�[���擾
     * 
     * @param toCorrectPersonMsg �R���\�[���ɕ\�����镶��
     * @param userList           ���[�U�[���X�g
     * @return �������郆�[�U�[�ԍ�
     */
    private static int getCorrectPerson(final String toCorrectPersonMsg, final List<User> userList) {
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
     * ���[�U�[���̍폜
     * 
     * @param userList ���[�U�[���X�g
     * @return ���[�U�[���X�g
     */
    private static List<User> deleteUserInfo(final List<User> userList) {

        // userList����̏ꍇ
        if (isEmpty(userList)) {
            System.out.println(NO_DATA);
            return userList;
        }

        do {
            // �폜����l���\�������쐬
            String toCorrectPersonMsg = displayToCorrectPerson(userList, "�ǂ̃��[�U���폜���܂����H");

            // ��������l���̔ԍ����擾
            int personOfNumber = getCorrectPerson(toCorrectPersonMsg, userList);

            // 0�̏ꍇ�́A�������[�h�ɖ߂�
            if (personOfNumber == 0) {
                return userList;
            }

            int idx = personOfNumber - 1;
            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            String deleteName = userList.get(idx).getName();

            // �폜����
            deleteUser(userList, deleteName, idx);

        } while (true);

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
     * ���[�U�[���X�g����폜����
     * 
     * @param userList   ���[�U�[���X�g
     * @param deleteName �폜����l�̖��O
     * @param idx        �C���f�b�N�X
     */
    private static void deleteUser(List<User> userList, final String deleteName, final int idx) {

        System.out.println(String.format("%S��������̐�����폜���܂����B", deleteName));
        userList.remove(idx);
    }
}
