package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java1_2�N���X ���\���N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/07 2020/03/06��RV�w�E��荞��
 */
public class Java1_2 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /**
     * ���̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                System.out.printf("%2d", i * j);
                System.out.print(" ");
            });
            System.out.printf("%n");
        });

    }

}
