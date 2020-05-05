package agatasan_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private static final String USER_INPUT_SENTENCES = "���l��" + USER_INPUT_MAX_NUMBER_OF_TIMES + "����͂��Ă��������B";

    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = 1;

    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 100;

    /** ���͋��e�͈� */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "�`" + USER_INPUT_MAX_VALUE;

    /** ���l���͑������� */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "��" + USER_INPUT_SENTENCES;

    /** ���l�ȊO�̓��͎��G���[���� */
    private static final String NON_NUMERIC_ERROR = "�G���[�I�I" + USER_INPUT_RANGE + "�ȊO�̐��l�����͂���܂����B";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(USER_INPUT_SENTENCES);
        System.out.println("end�œ��͂��I�����܂��B");

        // �G���[���ł�
//        int userInput[] = new int[10];
        List<Integer> list = new ArrayList<>();

        while (scanner.hasNext()) {

            // �l�擾
            String input = scanner.next();

            // end������
            if (Objects.equals(input, EXIT_CONDITIONS)) {
                // �I��
                break;
            }

            // ���l������
            try {
                int line = Integer.valueOf(input).intValue();

                // ���͋��e�͈͊O�̓G���[
                if (!isWithinRange(line, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                // 10�ڂ̏ꍇ�A�\��
                if (list.size() == (USER_INPUT_MAX_NUMBER_OF_TIMES - 1)) {

                    list.add(line);

                    Integer[] array = list.toArray(new Integer[list.size()]);

                    Arrays.sort(array);
                    for (int number : array) {
                        System.out.println(number);
                    }
                    // 10���͂����̂ŏ�����
                    System.out.println(USER_INPUT_MAX_NUMBER_OF_TIMES + "����͂��ꂽ�̂ŁA���������܂����B");
                    System.out.println(USER_INPUT_SENTENCES);
                    list = new ArrayList<>();
                    continue;
                }

                list.add(line);
                System.out.printf(list.size() + "�߂����͂���܂����B%n���������A���l����͂��Ă��������B%n");

            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }

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

}
