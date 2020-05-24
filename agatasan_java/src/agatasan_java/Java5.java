package agatasan_java;

/**
 * Java5�N���X Java�ۑ�T(���̑̑��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/05 �V�K�쐬
 */
public class Java5 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s ";

    /**
     * ���̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {
        diplayMultiplicationTable(MULTIPLICATION_TABLE_RANGE_START);
    }

    /**
     * ����\��
     * 
     * @param startNumber ���̏c�̒i�̐��l
     */
    public static void diplayMultiplicationTable(int startNumber) {
        if (startNumber <= MULTIPLICATION_TABLE_RANGE_END) {
            displayHorizontal(startNumber, MULTIPLICATION_TABLE_RANGE_START);
            System.out.printf("%n");
            diplayMultiplicationTable(startNumber + 1);
        }
    }

    /**
     * ���̉��̒i��\��
     * 
     * @param verticalNumber   �c�̒i�̐��l
     * @param horizontalNumber ���̒i�̐��l
     */
    public static void displayHorizontal(int verticalNumber, int horizontalNumber) {
        if (horizontalNumber <= MULTIPLICATION_TABLE_RANGE_END) {
            System.out.printf(DISPLAY_FORMAT, verticalNumber * horizontalNumber);
            displayHorizontal(verticalNumber, horizontalNumber + 1);
        }
    }
}
