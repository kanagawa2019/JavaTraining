package agatasan_java;

import java.util.stream.IntStream;

/**
 * �i�x�A�c���[�h���\�������N���X
 *
 * @author �H�c ���I
 * @version 1.0 2021/05/16 �V�K�쐬
 * @version 1.1 2021/05/21 No.105�w�E�Ή�
 */
public class Nabeatsu extends Multiplication {

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------

    /** ���̌v�Z���ʂ����Z���鐔�l */
    public static final int DIVIDE_BY_NUMBER = 3;
    /** ���̌v�Z���ʂɊ܂܂�鐔�l */
    public static final int CONTAINS_NUMBER = 3;
    /** �w�萔�l�Ŋ���؂��A�܂��͎w�萔�l���܂ޏꍇ�̕\�� */
    public static final String DISPLAY_WHEN_MATCHING_CONDITIONS = "NA";

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------

//    /**
//     * �ؑփ��[�hON�̋��\���̏���
//     * 
//     */
//    @Override
//    public void calcMultiplicationTable() {
//
//        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
//            displaySwitchingMode(i);
//        });
//
//    }
//
//    /**
//     * �ؑփ��[�hON�̋��1�s�\���̏���
//     * 
//     * @param number ���͕���
//     */
//    @Override
//    public void calcMultiplicationTable(final int number) {
//        displaySwitchingMode(number);
//    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------

    /**
     * �ؑփ��[�hON�̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    @Override
    public void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            int calculationResult = i * inputNumber;

            // 3�̔{���͕ʕ\��
            if (isMultipleOfTheeOrValueOfThree(calculationResult)) {
                sb.append(String.format(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS));
            } else {
                sb.append(String.format(DISPLAY_FORMAT, calculationResult));
            }

        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    /**
     * 3�̔{����3�̒l�����邩�𔻒�
     * 
     * @param calculationResult ����Ώ�
     * @return 3�̔{���܂���3�̒l������ꍇ�ATrue�B3�̔{���܂���3�̒l�ł͂Ȃ��ꍇ�Afalse�B
     */
    private boolean isMultipleOfTheeOrValueOfThree(final int calculationResult) {
        if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
            return true;
        }
        return false;
    }

    /**
     * �����Ώۂɔ��蕶�����܂܂�邩�`�F�b�N
     * 
     * @param target            �����Ώ�
     * @param judgmentCharacter ���蕶��
     * @return ���蕶�����܂܂��ꍇ�Atrue�B�܂܂�Ȃ��ꍇ�Afalse
     */
    private boolean hasContainedCharacter(final int target, final int judgmentCharacter) {
        String targetStr = String.valueOf(target);
        return targetStr.contains(String.valueOf(judgmentCharacter));
    }
}
