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
 * @version 1.0 2020/05/17 �V�K�쐬
 * @version 1.1 2020/05/24 �����񌋍��Amain�����֐���
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

    /**
     * ���͂��ꂽ���l�������ŃR���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        displaySentence();

        List<Integer> inputList = new ArrayList<>();

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

            inputList.add(userInputNumber);

            // �ő���͉񐔓��͎��̏ꍇ�A���͒l��\��
            if (inputList.size() == USER_INPUT_MAX_NUMBER_OF_TIMES) {

                // ���͒l��\��
                displayInputNumbers(inputList);

                // �ő���͉񐔕����͂����̂ŏ�����
                InitializationForReset(inputList);

                continue;
            }

            System.out.printf(String.format("%s�߂����͂���܂����B%n���������A%s%n", inputList.size(), USER_INPUT_SENTENCES));

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
     * @param list ���͂��ꂽ���l�̃��X�g
     */
    private static void displayInputNumbers(List<Integer> list) {
        // �����Ƀ\�[�g
        Collections.sort(list);
        System.out.println("���ёւ��ĕ\�����܂��B");
        list.forEach(System.out::println);
    }

    /**
     * ���Z�b�g�̂��ߏ�����
     * 
     * @param list ���͂��ꂽ���l�̃��X�g
     */
    static void InitializationForReset(List<Integer> list) {

        System.out.println(String.format("%s����͂��ꂽ�̂ŁA���Z�b�g���܂����B", USER_INPUT_MAX_NUMBER_OF_TIMES));
        displaySentence();
        list = new ArrayList<>();
    }
}
