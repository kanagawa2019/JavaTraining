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

    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("���l����͂��Ă��������B");
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
                if (!isRange(line, 1, 9)) {
                    System.out.println("�G���[�I�I1�`9�ȊO�̐��l�����͂���܂����B");
                    System.out.println("1�`9�̐��l����͂��Ă��������B");
                    continue;
                }

                // ���͓��e�����l�̏ꍇ
                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                    System.out.printf(DISPLAY_FORMAT, i * line, " ");
                });
                System.out.printf("%n���������A���\������ꍇ�́A���l����͂��Ă��������B%n");
            } catch (NumberFormatException e) {
                // ���͓��e�����l�ȊO�̏ꍇ
                System.out.println("�G���[�I�I���l�ȊO�����͂���܂����B");
                System.out.println("1�`9�̐��l����͂��Ă��������B");
            }

        }

        // ctr + z or "end"���͂ŏI��
        scanner.close();
        System.out.println("���͂��I�����܂����B");
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param x    �Ώې��l
     * @param from �͈�FROM
     * @param to   �͈�TO
     * @return true:�͈�FROM�`�͈�TO���ł���
     */
    public static boolean isRange(int x, int from, int to) {
        return (x >= from && x <= to);
    }

}
