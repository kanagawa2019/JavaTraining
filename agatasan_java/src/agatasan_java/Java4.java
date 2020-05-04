package agatasan_java;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java4�N���X Java�ۑ�S(�X�e�[�^�X�Ǘ��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/04
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

    /** �������[�h���l�F���\�� */
    private static final int MODE_DISPLAY_MULTIPLICATION_TABLE = 1;

    /** �������[�h���l�F�w��̒i�̂ݕ\�� */
    private static final int MODE_DISPLAY_MULTIPLICATION_LINE = 2;

    /** �������[�h���l�F���[�h�ؑ� */
    private static final int MODE_SWITCHING = 3;

    /** �������[�h���l�F�I�� */
    private static final int MODE_END = 9;

    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // �ؑփ��[�h�I���E�I�t
        boolean displaySwitching = false;

        displaySentence(displaySwitching);

        while (scanner.hasNext()) {

            // �l�擾
            String input = scanner.next();

            // ���l������
            try {
                int line = Integer.valueOf(input).intValue();

                // 1�����͂��ꂽ�ꍇ
                if (line == MODE_DISPLAY_MULTIPLICATION_TABLE) {
                    // ���\������
                    displayMultiplicationTable(displaySwitching);
                    displaySentence(displaySwitching);
                    continue;
                }

                // 2�����͂��ꂽ�ꍇ
                if (line == MODE_DISPLAY_MULTIPLICATION_LINE) {
                    displayMultiplicationLine(scanner, displaySwitching);
                    displaySentence(displaySwitching);
                    continue;
                }

                // 3�����͂��ꂽ�ꍇ
                if (line == MODE_SWITCHING) {
                    // �ؑ�
                    displaySwitching = displaySwitching ? false : true;
                    System.out.println("���[�h��؂�ւ��܂����B");
                    displaySentence(displaySwitching);
                    continue;
                }

                // 9�����͂��ꂽ�ꍇ
                if (line == MODE_END) {
                    System.out.println("�I�����܂��B");
                    break;
                }

                // �w�萔�l�ȊO�̏ꍇ
                List<String> list = Arrays.asList(String.valueOf(MODE_DISPLAY_MULTIPLICATION_TABLE),
                        String.valueOf(MODE_DISPLAY_MULTIPLICATION_LINE), String.valueOf(MODE_SWITCHING),
                        String.valueOf(MODE_END));

                System.out.println(String.join(",", list) + "�̂����ꂩ����͂��Ă��������B");

                displaySentence(displaySwitching);
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
    private static void displaySingleLine(boolean displaySwitching, int inputNumber) {

        // �ؑփ��[�h�I���̏ꍇ
        if (displaySwitching) {
            // TODO NA�\���H
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

    /**
     * ���P�s�\���̓��͔��菈��
     * 
     * @param scanner          ���͏��
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    private static void displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {
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

                displaySingleLine(displaySwitching, inputLine);
                break;
            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }

        }
    }

    /**
     * �R���\�[���ɕ\�����镶��
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    private static void displaySentence(boolean displaySwitching) {

        System.out.println("***********************************");
        System.out.println("�������[�h��I�����Ă�������");
        System.out.println(MODE_DISPLAY_MULTIPLICATION_TABLE + ".���\��");
        System.out.println(MODE_DISPLAY_MULTIPLICATION_LINE + ".�w��̒i�̂ݕ\��");
        if (displaySwitching) {
            System.out.println(MODE_SWITCHING + ".���[�h�ؑ�(���E�̃i�x�A�c�˒ʏ�)");
        } else {
            System.out.println(MODE_SWITCHING + ".���[�h�ؑ�(�ʏ�ː��E�̃i�x�A�c)");
        }

        System.out.println(MODE_END + ".�I��");
        System.out.println("***********************************");
    }
}
