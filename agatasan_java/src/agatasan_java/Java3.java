package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java�ۑ�R(���򐧌�j�@���\���N���X(3�̔{����NA�\��)�@
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/15
 */
public class Java3 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;
    
    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s%s";
    
    /** 3�̔{�����̕\�� */
    private static final String MULTIPLES_OF_3_DISPLAY = "NA";

    /**
     * ���̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {
        
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                
                // 3�̔{���͕ʕ\��
                if ((i * j) % 3 == 0) {
                    System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_3_DISPLAY, " ");
                } else {
                    System.out.printf(DISPLAY_FORMAT, i * j, " ");
                }
            });
            System.out.printf("%n");
        });

    }

}
