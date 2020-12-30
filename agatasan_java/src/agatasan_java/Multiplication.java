package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Multiplication extends Display {

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

    /** ���[�U�[���͑������� */
    public static final String USER_INPUT_SENTENCES = "���l����͂��Ă��������B";

    /** ���͋��e�ŏ��l */
    public static final int USER_INPUT_MIN_VALUE = 1;

    /** ���͋��e�ő�l */
    public static final int USER_INPUT_MAX_VALUE = 9;

    /** ���͋��e�͈� */
    public static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "�`" + USER_INPUT_MAX_VALUE;

    /** ���l���͑������� */
    public static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "��" + USER_INPUT_SENTENCES;

    /** ���l�ȊO�̓��͎��G���[���� */
    public static final String NON_NUMERIC_ERROR = "�G���[�I�I" + USER_INPUT_RANGE + "�ȊO�̐��l�����͂���܂����B";

    /**
     * ���\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    public static void displayMultiplicationTableTest() {

        // �ؑփ��[�h�I�t�̏ꍇ
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingModeOffTest(i);

        });

    }

    public static void displayMultiplicationTableTest(boolean displaySwitching) {

        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingModeOn(displaySwitching, i);
        });

    }

    /**
     * �ؑփ��[�h�I���̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    public static void displaySwitchingModeOn(boolean displaySwitching, int inputNumber) {

        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            int calculationResult = i * inputNumber;

            // 3�̔{���͕ʕ\��
            if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
                System.out.printf(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS);
            } else {
                System.out.printf(DISPLAY_FORMAT, calculationResult);
            }

        });
        System.out.printf("%n");
    }

    /**
     * �����Ώۂɔ��蕶�����܂܂�邩�`�F�b�N
     * 
     * @param target            �����Ώ�
     * @param judgmentCharacter ���蕶��
     * @return true:���蕶�����܂܂��
     */
    private static boolean hasContainedCharacter(int target, int judgmentCharacter) {
        String targetStr = String.valueOf(target);
        return targetStr.contains(String.valueOf(judgmentCharacter));
    }

    /**
     * �ؑփ��[�h�I�t�̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    public static void displaySwitchingModeOffTest(int inputNumber) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            System.out.printf(DISPLAY_FORMAT, i * inputNumber);
        });
        System.out.printf("%n");
    }

    /**
     * ���P�s�\���̓��͔��菈��
     * 
     * @param scanner          ���͏��
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    public static int displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {
        System.out.println("�\������i����͂��Ă�������");

        do {
            // ���l������
            try {
                int inputLine = Integer.valueOf(scanner.next()).intValue();

                // ���͋��e�͈͊O�̓G���[
                if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                return inputLine;
            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }
        } while (true);
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return true:�ŏ��l�`�ő�l�͈͓̔��ɂ���
     */
    private static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }
}
