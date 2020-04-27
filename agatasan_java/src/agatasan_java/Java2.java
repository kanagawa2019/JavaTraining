package agatasan_java;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java2�N���X Java�ۑ�Q(�W�����́E�֐����E���̓`�F�b�N�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/08 �V�K�쐬
 * @version 1.1 2020/04/19 2020/03/24��RV�w�E��荞�� �������[�vwhile(true)��ύX
 * @version 1.2 2020/04/26 2020/04/20��RV�w�E��荞�� ���͏���`�F�b�N�̒ǉ��Ewhile�C���f���g�C��
 *          �E���͊����i���\���j��̃��[�U�[���͑��������̒ǉ�
 * @version 1.3 2020/04/27 2020/04/27��RV�w�E��荞�� �����萔���E���\�b�h�����K�����E�s�����̌����𑵂���
 */
public class Java2 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s ";

    /** �I������ */
    private static final String EXIT_CONDITIONS = "end";

    /** ���[�U�[���͑������� */
    private static final String USER_INPUT_SENTENCES = "���l����͂��Ă��������B";

    /** ���l���͑������� */
    private static final String NUMBER_INPUT_SENTENCES = "1�`9�̐��l����͂��Ă��������B";

    /** ���l�ȊO�̓��͎��G���[���� */
    private static final String NON_NUMERIC_ERROR = "�G���[�I�I1�`9�ȊO�̐��l�����͂���܂����B";

    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(USER_INPUT_SENTENCES);
        System.out.println("end�œ��͂��I�����܂��B");

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

                // ���͔͈͂�1~9�ȊO�̓G���[
                if (!isWithinRange(line, 1, 9)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                // ���͓��e�����l�̏ꍇ
                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                    System.out.printf(DISPLAY_FORMAT, i * line, " ");
                });
                System.out.printf("%n���������A���\������ꍇ�́A���l����͂��Ă��������B%n");
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
    public static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

}
