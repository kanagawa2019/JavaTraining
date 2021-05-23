package agatasan_java.java14;

import java.util.List;
import java.util.Scanner;

/**
 * ���ʏ���
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 *
 */
public class Util {

    /** �X�L���i�[�i�R���\�[�����́j */
    public static Scanner mScanner = new Scanner(System.in);

    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";
    /** �͈͓��̃��b�Z�[�W */
    private static final String WITHIN_RANGE = "%d�`%d�͈̔͂œ��͂��Ă��������B";

    /** �l�����X�g�\���`�� */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_LIST = "%2d";
    /** �������X�g�\���`�� */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_ATTRIBUTE_LIST = "%2d";
    /** �������X�g�J�n�ԍ� */
    public static final int START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST = 0;
    /** �O�ɖ߂�̃��b�Z�[�W */
    public static final String BACK = "�O�ɖ߂�";
    /** �I�����̍ŏ��l */
    public static final int RANGE_MIN = 0;

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
     * @param inputMsg ���̓R���\�[���ɕ\�����镶��
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
     * �����p���m�F
     * 
     * @return �����p����True�B�����I����false�B
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
     * �I������
     */
    public static void scannerClose() {
        System.out.println("�I�����܂��B");
        mScanner.close();
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return true:�ŏ��l�`�ő�l�͈͓̔��ɂ���
     */
    public static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
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
        if (!Util.isWithinRange(number, min, max)) {
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
    public static String displayToCorrectPerson(final List<Personal> userList, String msg) {

        System.out.println(msg);

        // ���앶���\��
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        int cnt = 0;
        sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, cnt)).append(".").append(BACK).append("\n");

        for (Personal u : userList) {
            sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, ++cnt)).append(".").append(u.getName()).append("\n");
        }
        sb.append("---------------------------").append("\n");

        return sb.toString();
    }

    /**
     * ��������Ώۃ��[�U�[���擾
     * 
     * @param toCorrectPersonMsg �R���\�[���ɕ\�����镶��
     * @param userList           ���[�U�[��񃊃X�g
     * @return �������郆�[�U�[�ԍ�
     */
    public static int getCorrectPerson(final String toCorrectPersonMsg, final List<Personal> userList) {
        int personOfNumber = 0;
        do {
            // ��������l���̔ԍ����擾
            personOfNumber = Util.inputInt(toCorrectPersonMsg);

        } while (Util.isOutOfRange(personOfNumber, RANGE_MIN, userList.size()));
        return personOfNumber;
    }
//    /**
//     * ���͂��ꂽ���z���擾
//     * 
//     * @return ���z
//     */
//    public static int inputDeposit(String process) {
//        return Util.inputInt(String.format("%S���z����͂��Ă�������", process));
//    }
}
