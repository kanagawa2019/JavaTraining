package agatasan_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Java7�N���X Java�ۑ�V(���X�g�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/17
 */
public class Java7 {

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

    /** ���͋��e�͈͕��� */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "�`" + USER_INPUT_MAX_VALUE;

    /** ���l���͑������� */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "��" + USER_INPUT_SENTENCES;

    /** ���l�ȊO�̓��͎��G���[���� */
    private static final String NON_NUMERIC_ERROR = "�G���[�I�I" + USER_INPUT_RANGE + "�ȊO�̐��l�����͂���܂����B";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        displaySentence();

        List<Integer> list = new ArrayList<>();

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

            // �ő���͉񐔓��͎��̏ꍇ�A���͒l��\��
            if (list.size() == (USER_INPUT_MAX_NUMBER_OF_TIMES - 1)) {

                list.add(line);

                Collections.sort(list);
                System.out.println("���͒l�������ŕ\�����܂��B");
                list.forEach(System.out::println);

                // �ő���͉񐔕����͂����̂ŏ�����
                System.out.println(USER_INPUT_MAX_NUMBER_OF_TIMES + "����͂��ꂽ�̂ŁA���Z�b�g���܂����B");
                displaySentence();
                list = new ArrayList<>();
                continue;
            }

            list.add(line);
            System.out.printf(list.size() + "�߂����͂���܂����B%n���������A���l����͂��Ă��������B%n");

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
