package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java4�N���X Java�ۑ�S(�X�e�[�^�X�Ǘ��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/22
 */
public class Java4 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s  ";

    /** �{�� */
    private static final int MULTIPLE = 3;

    /** s�{�����̕\�� */
    private static final String MULTIPLES_OF_DISPLAY = "NA";

    /** ���[�U�[���͑������� */
    private static final String USER_INPUT_SENTENCES = "���l����͂��Ă��������B";

    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = 1;

    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 9;

    /** ���͋��e�͈� */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "�`" + USER_INPUT_MAX_VALUE;

    /** ���l���͑������� */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "��" + USER_INPUT_SENTENCES;

    /** ���l�ȊO�̓��͎��G���[���� */
    private static final String NON_NUMERIC_ERROR = "�G���[�I�I" + USER_INPUT_RANGE + "�ȊO�̐��l�����͂���܂����B";

    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // �ؑփ��[�h�I���E�I�t
        boolean displaySwitching = false;

        System.out.println("***********************************");
        System.out.println("�������[�h��I�����Ă�������");
        System.out.println("1.���\��");
        System.out.println("2.�w��̒i�̂ݕ\��");
        if (displaySwitching) {
            System.out.println("3.���[�h�ؑ�(���E�̃i�x�A�c�˒ʏ�)");
        } else {
            System.out.println("3.���[�h�ؑ�(�ʏ�ː��E�̃i�x�A�c)");
        }

        System.out.println("9.�I��");
        System.out.println("***********************************");

        while (scanner.hasNext()) {

            // �l�擾
            String input = scanner.next();

            // ���l������
            try {
                int line = Integer.valueOf(input).intValue();

                // 1�����͂��ꂽ�ꍇ
                if (line == 1) {
                    // ���\������
                    displayMultiplicationTable(displaySwitching);
                    continue;
                }

                // 2�����͂��ꂽ�ꍇ
                if (line == 2) {
                    System.out.println("�\������i����͂��Ă�������");

                    while (scanner.hasNext()) {

                        // ���l������
                        try {
                            int inputLine = Integer.valueOf(scanner.next()).intValue();

                            // ���͋��e�͈͊O�̓G���[
                            if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                                System.out.println(NON_NUMERIC_ERROR);
                                System.out.println(NUMBER_INPUT_SENTENCES);
                                continue;
                            }

                            test(displaySwitching, inputLine);
                            break;
                        } catch (NumberFormatException e) {
                            // ���͓��e�����l�ȊO�̏ꍇ
                            System.out.println(NON_NUMERIC_ERROR);
                            System.out.println(NUMBER_INPUT_SENTENCES);
                        }

                    }

                    continue;
                }

                // 3�����͂��ꂽ�ꍇ
                if (line == 3) {
                    // �ؑ�
                    if (displaySwitching) {
                        displaySwitching = false;
                    } else {
                        displaySwitching = true;
                    }

                    System.out.println("���[�h��؂�ւ��܂����B");
                    continue;
                }

                // 9�����͂��ꂽ�ꍇ
                if (line == 9) {
                    System.out.println("�I�����܂��B");
                    break;
                }

                System.out.println("1,2,3,9�̂����ꂩ����͂��Ă��������B");
                continue;

            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }

        }

        scanner.close();

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
     * ���P�s�\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     * @param inputNumber      ���͕���
     */
    private static void test(boolean displaySwitching, int inputNumber) {

        // �ؑփ��[�h�I���̏ꍇ
        if (displaySwitching) {
            displaySwitchingModeOn(inputNumber);
            return;
        }

        // �ؑփ��[�h�I�t�̏ꍇ
        displaySwitchingModeOff(inputNumber);

    }

    /**
     * �ؑփ��[�h�I�t�̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    private static void displaySwitchingModeOff(int inputNumber) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            System.out.printf(DISPLAY_FORMAT, i * inputNumber, " ");
        });
        System.out.printf("%n");
    }

    /**
     * �ؑփ��[�h�I���̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    private static void displaySwitchingModeOn(int inputNumber) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            // 3�̔{���͕ʕ\��
            if ((i * inputNumber) % MULTIPLE == 0) {
                System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_DISPLAY, " ");
            } else {
                System.out.printf(DISPLAY_FORMAT, i * inputNumber, " ");
            }

        });
        System.out.printf("%n");
    }

    /**
     * ���\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    private static void displayMultiplicationTable(boolean displaySwitching) {

        // �ؑփ��[�h�I���̏ꍇ
        if (displaySwitching) {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                displaySwitchingModeOn(i);
            });
            return;
        }

        // �ؑփ��[�h�I�t�̏ꍇ
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingModeOff(i);

        });

    }
}
