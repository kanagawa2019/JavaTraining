package agatasan_java;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Java6�N���X Java�ۑ�U(�z��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/05
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

    /** ���l���͑������� */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "��" + USER_INPUT_SENTENCES;

    /** ���l�ȊO�̓��͎��G���[���� */
    private static final String NON_NUMERIC_ERROR = "�G���[�I�I" + USER_INPUT_RANGE + "�ȊO�̐��l�����͂���܂����B";

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

            int line = 0;
            // ���l������
            try {
                line = Integer.valueOf(input).intValue();
            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                displayErrSentence();
                continue;
            }

            // ���͋��e�͈͊O�̓G���[
            if (!isWithinRange(line, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                displayErrSentence();
                continue;
            }

            // �z��֒ǉ�
            if (count < 10) {
                inputNumbers[count] = line;
            }

            // �ő���͉񐔓��͎��̏ꍇ�A���͒l��\��
            if (count == USER_INPUT_MAX_NUMBER_OF_TIMES - 1) {

                System.out.println("���ёւ��ĕ\�����܂��B");
                Arrays.sort(inputNumbers);
                for (int number : inputNumbers) {
                    System.out.println(number);
                }
                // �ő���͉񐔕����͂����̂ŏ�����
                System.out.println(USER_INPUT_MAX_NUMBER_OF_TIMES + "����͂��ꂽ�̂ŁA���Z�b�g���܂����B");
                displaySentence();
                Arrays.fill(inputNumbers, 0);
                count = 0;
                continue;
            }

            count++;
            System.out.printf(count + "�߂����͂���܂����B%n���������A" + USER_INPUT_SENTENCES + "%n");

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
        System.out.println(NON_NUMERIC_ERROR);
        System.out.println(NUMBER_INPUT_SENTENCES);
    }
}
