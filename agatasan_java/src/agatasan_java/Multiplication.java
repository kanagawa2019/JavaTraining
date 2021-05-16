package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * ���\�������N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 */
public class Multiplication {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �X�L���i�[�i�R���\�[�����́j */
    public static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** ���̓J�[�\�� */
    public static final String CURSOL = ">";
    /** ���̎n�܂�̒l */
    public static final int MULTIPLICATION_TABLE_RANGE_START = 1;
    /** ���̏I���̒l */
    public static final int MULTIPLICATION_TABLE_RANGE_END = 9;
    /** �\���`�� */
    public static final String DISPLAY_FORMAT = "%2s ";
    /** ���̌v�Z���ʂ����Z���鐔�l */
    public static final int DIVIDE_BY_NUMBER = 3;
    /** ���̌v�Z���ʂɊ܂܂�鐔�l */
    public static final int CONTAINS_NUMBER = 3;
    /** �w�萔�l�Ŋ���؂��A�܂��͎w�萔�l���܂ޏꍇ�̕\�� */
    public static final String DISPLAY_WHEN_MATCHING_CONDITIONS = "NA";
    /** ���͋��e�ŏ��l */
    public static final int USER_INPUT_MIN_VALUE = 1;
    /** ���͋��e�ő�l */
    public static final int USER_INPUT_MAX_VALUE = 9;

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * �ؑփ��[�hOFF�̋��\���̏���
     * 
     */
    public static void calcMultiplicationTable() {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    /**
     * �ؑփ��[�hON�̋��\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     */
//    public static void calcMultiplicationTable(final boolean displaySwitching) {
//
//        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
//            displaySwitchingMode(displaySwitching, i);
//        });
//
//    }

//    /**
//     * �ؑփ��[�hON�̏ꍇ�̕\��
//     * 
//     * @param displaySwitching �ؑփ��[�h�̏��
//     * @param inputNumber      ���͕���
//     */
//    public static void displaySwitchingMode(final boolean displaySwitching, final int inputNumber) {
//
//        StringBuilder sb = new StringBuilder();
//
//        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
//
//            int calculationResult = i * inputNumber;
//
//            // 3�̔{���͕ʕ\��
//            if (isMultipleOfTheeOrValueOfThree(calculationResult)) {
//                sb.append(String.format(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS));
//            } else {
//                sb.append(String.format(DISPLAY_FORMAT, calculationResult));
//            }
//            sb.append(String.format(DISPLAY_FORMAT, calculationResult));
//
//        });
//        System.out.println(sb.toString().replaceAll(" *$", ""));
//    }

    /**
     * �ؑփ��[�hOFF�̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    public static void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            sb.append(String.format(DISPLAY_FORMAT, i * inputNumber));
        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    /**
     * ���P�s�\���̓��͔��菈��
     * 
     * @return ���͒l
     */
    public static int calcMultiplicationLine() {

        do {
            // ���l������
            int inputLine = inputInt("�\������i����͂��Ă�������");

            // ���͋��e�͈͊O�̓G���[
            if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                System.out.println(String.format("�G���[�I�I%d�`%d�ȊO�̐��l�����͂���܂����B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                System.out.println(String.format("%d�`%d�̐��l����͂��Ă��������B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                continue;
            }

            return inputLine;

        } while (true);
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

    public static void scannerClose() {
        System.out.println("�I�����܂��B");
        mScanner.close();
    }

    public static void test() {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    public static void test(int ssss) {
        displaySwitchingMode(ssss);
    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * �����Ώۂɔ��蕶�����܂܂�邩�`�F�b�N
     * 
     * @param target            �����Ώ�
     * @param judgmentCharacter ���蕶��
     * @return ���蕶�����܂܂��ꍇ�Atrue�B�܂܂�Ȃ��ꍇ�Afalse
     */
    public static boolean hasContainedCharacter(final int target, final int judgmentCharacter) {
        String targetStr = String.valueOf(target);
        return targetStr.contains(String.valueOf(judgmentCharacter));
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return �ŏ��l�`�ő�l�͈͓̔��ɂ���ꍇ�Atrue�B�͈͊O�̏ꍇ�Afalse
     */
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 3�̔{����3�̒l�����邩�𔻒�
     * 
     * @param calculationResult ����Ώ�
     * @return 3�̔{���܂���3�̒l������ꍇ�ATrue�B3�̔{���܂���3�̒l�ł͂Ȃ��ꍇ�Afalse�B
     */
    private static boolean isMultipleOfTheeOrValueOfThree(final int calculationResult) {
        if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
            return true;
        }
        return false;
    }

}
