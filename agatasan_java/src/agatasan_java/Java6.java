package agatasan_java;

import java.util.Objects;
import java.util.Scanner;

/**
 * Java6�N���X Java�ۑ�U(�z��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/05 �V�K�쐬
 * @version 1.1 2020/05/24 �����񌋍��Amain�����֐���
 */
public class Java6 {

    /** �I������ */
    private static final String EXIT_CONDITIONS = "end";

    /** ���[�U�[�ő���͉� */
    private static final int USER_INPUT_MAX_NUMBER_OF_TIMES = 10;

    /** ���[�U�[���͑������� */
    private static final String USER_INPUT_SENTENCES = "���l����͂��Ă��������B";

    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = -1000;

    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 1000;

    /** ���͋��e�͈� */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "�`" + USER_INPUT_MAX_VALUE;

    /**
     * ���͂��ꂽ���l�������ŃR���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        displaySentence();

        int[] inputNumbers = new int[USER_INPUT_MAX_NUMBER_OF_TIMES];
        int count = 0;

        while (scanner.hasNext()) {

            // �l�擾
            String input = scanner.next();

            // end������
            if (Objects.equals(input, EXIT_CONDITIONS)) {
                // �I��
                break;
            }

            int userInputNumber = 0;
            // ���l������
            try {
                userInputNumber = Integer.valueOf(input).intValue();
            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                displayErrSentence();
                continue;
            }

            // ���͋��e�͈͊O�̓G���[
            if (!isWithinRange(userInputNumber, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                displayErrSentence();
                continue;
            }

            // �z��֒ǉ�
            if (count < USER_INPUT_MAX_NUMBER_OF_TIMES) {
                inputNumbers[count] = userInputNumber;
            }

            // �ő���͉񐔓��͎��̏ꍇ
            if (count == USER_INPUT_MAX_NUMBER_OF_TIMES - 1) {

                // ���͒l��\��
                displayInputNumbers(inputNumbers);

                // �ő���͉񐔕����͂����̂ŏ�����
                InitializationForReset(inputNumbers, count);

                continue;
            }

            count++;
            System.out.printf(String.format("%s�߂����͂���܂����B%n���������A%s%n", count, USER_INPUT_SENTENCES));

        }

        // ctr + z or "end"���͂ŏI��
        scanner.close();
        System.out.println("���͂��I�����܂����B");

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

    /**
     * �R���\�[���ɕ\�����镶��
     * 
     */
    private static void displaySentence() {
        System.out.println(USER_INPUT_SENTENCES);
        System.out.println("end�œ��͂��I�����܂��B");
    }

    /**
     * ���͓��e�����l�ȊO�̏ꍇ�A�R���\�[���ɕ\�����镶��
     * 
     */
    private static void displayErrSentence() {
        System.out.println(String.format("�G���[�I�I%s�ȊO�̐��l�����͂���܂����B", USER_INPUT_RANGE));
        System.out.println(String.format("%s��%s", USER_INPUT_RANGE, USER_INPUT_SENTENCES));
    }

    /**
     * ���͂��ꂽ���l��\��
     * 
     * @param inputNumbers ���͂��ꂽ���l
     */
    private static void displayInputNumbers(int[] inputNumbers) {
        System.out.println("���ёւ��ĕ\�����܂��B");
        // �����Ƀ\�[�g
        int[] afterSortArray = sortArrayInAsc(inputNumbers);

        for (int number : afterSortArray) {
            System.out.println(number);
        }
    }

    /**
     * ���l�z��������ɕ��ёւ�
     * 
     * @param numericalArray ���ёւ��Ώۂ̔z��
     * @return ���ёւ���̔z��
     */
    static int[] sortArrayInAsc(int[] numericalArray) {

        for (int i = 0; i < numericalArray.length - 1; i++) {
            for (int j = numericalArray.length - 1; j > i; j--) {
                if (numericalArray[j - 1] > numericalArray[j]) {
                    int tmp = numericalArray[j - 1];
                    numericalArray[j - 1] = numericalArray[j];
                    numericalArray[j] = tmp;
                }
            }
        }
        return numericalArray;
    }

    /**
     * ���Z�b�g�̂��ߏ�����
     * 
     * @param inputNumbers ���͂��ꂽ���l
     * @param count        ���͉�
     */
    static void InitializationForReset(int[] inputNumbers, int count) {

        System.out.println(String.format("%s����͂��ꂽ�̂ŁA���Z�b�g���܂����B", USER_INPUT_MAX_NUMBER_OF_TIMES));
        displaySentence();
        inputNumbers = new int[USER_INPUT_MAX_NUMBER_OF_TIMES];
        count = 0;

    }

}
