package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java4�N���X Java�ۑ�S(�X�e�[�^�X�Ǘ��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/04 �V�K�쐬
 * @version 1.1 2020/05/17 �萔�̖��̂��C��
 */
public class Java4 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s ";

    /** ���̌v�Z���ʂ����Z���鐔�l */
    private static final int DIVIDE_BY_NUMBER = 3;

    /** ���̌v�Z���ʂɊ܂܂�鐔�l */
    private static final int CONTAINS_NUMBER = 3;

    /** �w�萔�l�Ŋ���؂��A�܂��͎w�萔�l���܂ޏꍇ�̕\�� */
    private static final String DISPLAY_WHEN_MATCHING_CONDITIONS = "NA";

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

        displaySentence(displaySwitching);

        while (scanner.hasNext()) {

            // �l�擾
            String input = scanner.next();

            int userInputMode = 0;

            // ���l������
            try {
                userInputMode = Integer.valueOf(input).intValue();
            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
                continue;
            }

            // ���͒l�ɂ���ď�����ύX
            try {
                switch (Mode.getTypeByValue(userInputMode)) {

                    case MULTIPLICATION_TABLE:
                        displayMultiplicationTable(displaySwitching);
                        break;
                    case MULTIPLICATION_LINE:
                        displayMultiplicationLine(scanner, displaySwitching);
                        break;
                    case SWITCHING:
                        displaySwitching = displaySwitching ? false : true;
                        System.out.println("���[�h��؂�ւ��܂����B");
                        break;
                    case END:
                        System.out.println("�I�����܂��B");
                        break;
                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                // �w�萔�l�ȊO�̏ꍇ
                String[] arrayMode = createArrayMode();
                System.out.println(String.join(",", arrayMode) + "�̂����ꂩ����͂��Ă��������B");
            }

            // �I�������͂��ꂽ�ꍇ
            if (userInputMode == Mode.END.getValue()) {
                break;
            }

            displaySentence(displaySwitching);

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
            System.out.printf(DISPLAY_FORMAT, i * inputNumber);
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

            int calculationResult = i * inputNumber;

            // 3�̔{���͕ʕ\��
            if (calculationResult % DIVIDE_BY_NUMBER == 0
                    || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
                System.out.printf(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS);
            } else {
                System.out.printf(DISPLAY_FORMAT, calculationResult);
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
        System.out.println(Mode.MULTIPLICATION_TABLE.getValue() + ".���\��");
        System.out.println(Mode.MULTIPLICATION_LINE.getValue() + ".�w��̒i�̂ݕ\��");
        if (displaySwitching) {
            System.out.println(Mode.SWITCHING.getValue() + ".���[�h�ؑ�(���E�̃i�x�A�c�˒ʏ�)");
        } else {
            System.out.println(Mode.SWITCHING.getValue() + ".���[�h�ؑ�(�ʏ�ː��E�̃i�x�A�c)");
        }

        System.out.println(Mode.END.getValue() + ".�I��");
        System.out.println("***********************************");
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
     * Mode�̑S��ނ��������z����擾
     * 
     * @return enum��Mode�̑S��ނ��͂������z��
     */
    private static String[] createArrayMode() {
        String[] arrayMode = new String[Mode.values().length];
        int count = 0;
        for (Mode t : Mode.values()) {
            arrayMode[count] = String.valueOf(t.getValue());
            count++;
        }
        return arrayMode;
    }
}
