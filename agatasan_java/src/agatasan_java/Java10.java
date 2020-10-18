package agatasan_java;

import java.util.Map;
import java.util.Scanner;

/**
 * Java10�N���X Java�ۑ�P�O(�I�u�W�F�N�g�w���P�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/04 �V�K�쐬
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
    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = -1000000;
    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 1000000;
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";

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
        public static Mode getMode(final String inputMode) {
            if (inputMode == null) {
                return null;
            }
            for (final Mode t : Mode.values()) {
                if (t.id == Integer.parseInt(inputMode)) {
                    // TODO ���[�h�Ԃ��ɂ͂Ȃ��H
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

        do {
            // --------------------------------------------------
            // ����
            // --------------------------------------------------
            // �������[�h����
            final Mode mode = inputMode();
            // --------------------------------------------------
            // �����E�o��
            // --------------------------------------------------

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
        do {
            mode = Mode.getMode(inputStr(getDisplayModeString()));
            if (mode == null) {
                System.out.println("�Y�����鏈�����[�h��������܂���ł����B");
            }

        } while (mode == null);
        return mode;
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
     * �����p���m�F
     * 
     * @return isProcessingContinue �����p����True�B�����I����false�B
     */
    public static boolean isContinue() {
        boolean isCheck = false;
        boolean isProcessingContinue = false;

        do {
            String input = inputStr(String.format("�����𑱂��܂����H(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END));

            // ���͒l�̔���
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    isCheck = true;
                    isProcessingContinue = true;
                    break;
                case PROCESSING_END:
                    isCheck = true;
                    break;
                default:
                    System.out.println(
                            String.format("(%s/%s)�ȊO�����͂���܂����B\n�ē��͂����肢���܂��B", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return isProcessingContinue;
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
     * ���l����(int�^)
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    public static int inputInt(final String inputMsg) {
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
     * ���[�U�[���o�^�d���`�F�b�N
     * 
     * @param userInfoMap ���[�U�[���A���z�ێ��}�b�v
     * @param inputName   ���͂��ꂽ���[�U�[��
     * @return ���[�U�[�������łɓo�^����Ă���ꍇ��True�B�o�^����Ă��Ȃ��ꍇ��false�B
     */
    public static boolean isDuplicate(final Map<String, Integer> userInfoMap, final String inputName) {

        // ���[�U�[�������ɓo�^����Ă���ꍇ
        if (userInfoMap.containsKey(inputName)) {
            System.out.println(String.format("\\%d�~���o�^�ς݂ł��B", userInfoMap.get(inputName)));
            return true;
        }

        return false;
    }

    /**
     * ���z���x�z�`�F�b�N
     * 
     * @param inputMoney ���͂��ꂽ���z
     * @return ���͂��ꂽ���z�����x�͈͊O�̏ꍇ��True�B�͈͓��̏ꍇ��false�B
     */
    public static boolean isOutOfRange(final int inputMoney) {

        // ���z���͈͊O�̏ꍇ
        if (!isWithinRange(inputMoney, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
            System.out.println(String.format("���l�͈͊O�ł��B%d�`%d�̐��l����͂��Ă��������B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
            return true;
        }
        return false;
    }

}
