package agatasan_java;

/**
 * Java�P�R�N���X Java�ۑ�P�R(�I�u�W�F�N�g�w���F�p���E���d���j
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 * @version 1.1 2021/05/21 No.105,106,107�w�E�Ή�
 */
public class Java13 {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �ؑփ��[�h */
    private static boolean displaySwitching = false;

    /**
     * ���͂��ꂽ�������[�h�̏�����\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Display display = new Multiplication();

        do {
            // --------------------------------------------------
            // ����
            // --------------------------------------------------
            // �������[�h����
            final DisplayMode displayMode = display.inputDisplayMode(displaySwitching);

            display = displaySwitching ? new Nabeatsu() : new Multiplication();

            // --------------------------------------------------
            // �����E�o��
            // --------------------------------------------------

            switch (displayMode) {
                case MULTIPLICATION_TABLE:
                    display.calcMultiplicationTable();
                    break;
                case MULTIPLICATION_LINE:
                    display.calcMultiplicationTable(display.calcMultiplicationLine());
                    break;
                case SWITCHING:
                    displaySwitching = !displaySwitching;
                    System.out.println("���[�h��؂�ւ��܂����B");
                    break;
                case END:
                    // �I������
                    display.scannerClose();
                    return;
                default:
                    break;
            }

        } while (display.isContinue());

    }

}
