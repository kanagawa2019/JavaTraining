package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java1�N���X ���\���N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/04 �V�K�쐬
 * @version 1.1 2020/03/05 2020/03/05��RV�w�E��荞�݁@�}�W�b�N�i���o�[��萔���A�\���𐮌`
 * @version 1.2 2020/03/07 2020/03/06��RV�w�E��荞��
 * @version 1.3 2020/03/11 2020/03/10��RV�w�E��荞��
 * @version 1.4 2020/04/04 2020/03/24��RV�w�E��荞�݁@Git�Ǘ����A�t�@�C����1�ɏW��
 */
public class Java1 {

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
