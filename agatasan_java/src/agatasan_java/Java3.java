package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java�ۑ�R(���򐧌�j ���\���N���X(3�̔{�� or 3�����ꍇ��NA�\��)
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/02 �V�K�쐬
 * @version 1.1 2020/05/09 3�����ꍇ���ʕ\������悤�ɏC��
 * @version 1.2 2020/05/14 �w�蕶��������ꍇ�̔���`�F�b�N���C��
 * @version 1.3 2020/05/16 �萔�̖��̂��C��
 */
public class Java3 {

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

    /**
     * ���̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {

                int calculationResult = i * j;

                // �w�肵�����l�Ŋ���؂�� or �w��̐��l���܂ޏꍇ�͕ʕ\��
                if ((calculationResult) % DIVIDE_BY_NUMBER == 0
                        || hasContainedCharacter((calculationResult), CONTAINS_NUMBER)) {
                    System.out.printf(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS);
                } else {
                    System.out.printf(DISPLAY_FORMAT, calculationResult);
                }
            });
            System.out.printf("%n");
        });

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

}
