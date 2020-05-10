package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java�ۑ�R(���򐧌�j ���\���N���X(3�̔{�� or 3�����ꍇ��NA�\��)
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/02 �V�K�쐬
 * @version 1.1 2020/05/09 3�����ꍇ���ʕ\������悤�ɏC��
 */
public class Java3 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s  ";

    /** �{�� */
    private static final int MULTIPLE = 3;

    /** ���l */
    private static final int NUMERICAL_VALUE = 3;

    /** �{�����̕\�� */
    private static final String MULTIPLE_DISPLAY = "NA";

    /**
     * ���̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {

                // �w��̔{�� or �w��̐��������ꍇ�͕ʕ\��
                if ((i * j) % MULTIPLE == 0 || checkNumericalValue(i * j)) {
                    System.out.printf(DISPLAY_FORMAT, MULTIPLE_DISPLAY, " ");
                } else {
                    System.out.printf(DISPLAY_FORMAT, i * j, " ");
                }
            });
            System.out.printf("%n");
        });

    }

    /**
     * �w��̐��l���܂܂�邩�`�F�b�N
     * 
     * @param number
     * @return true:���l���܂܂��
     */
    private static boolean checkNumericalValue(int number) {
        String numberStr = String.valueOf(number);
        return numberStr.contains(String.valueOf(NUMERICAL_VALUE));
    }

}
