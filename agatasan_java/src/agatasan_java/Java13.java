package agatasan_java;

import java.util.Scanner;

/**
 * Java�P�R�N���X Java�ۑ�P�R(�I�u�W�F�N�g�w���F�p���E���d���j
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 */
public class Java13 extends Multiplication implements Display {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �X�L���i�[�i�R���\�[�����́j */
    private static Scanner mScanner = new Scanner(System.in);
    /** �ؑփ��[�h */
    private static boolean displaySwitching = false;

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";

    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        do {
            // --------------------------------------------------
            // ����
            // --------------------------------------------------
            // �������[�h����
            final DisplayMode displayMode = inputDisplayMode();
            // --------------------------------------------------
            // �����E�o��
            // --------------------------------------------------

            switch (displayMode) {
                case MULTIPLICATION_TABLE:
                    displayMultiplicationTable(displaySwitching);
                    break;
                case MULTIPLICATION_LINE:
                    displayMultiplicationLine(mScanner, displaySwitching);
                    break;
                case SWITCHING:
                    displaySwitching = displaySwitching ? false : true;
                    System.out.println("���[�h��؂�ւ��܂����B");
                    break;
                case END:
                    System.out.println("�I�����܂��B");
                    // �I������
                    mScanner.close();
                    return;
                default:
                    break;
            }

        } while (isContinue());

    }

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * ���[�h�I��\�������擾
     */
    @Override
    public String getDisplayModeString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("�������[�h��I�����Ă�������").append("\n");
        sb.append(DisplayMode.getSelectDisplayModeString(displaySwitching));
        sb.append("***********************************");
        return sb.toString();
    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * ���P�s�\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     * @param inputNumber      ���͕���
     */
    private static void calcSingleLine(boolean displaySwitching, int inputNumber) {

        // �ؑփ��[�h�I���̏ꍇ
        if (displaySwitching) {
            displaySwitchingMode(displaySwitching, inputNumber);
            return;
        }

        // �ؑփ��[�h�I�t�̏ꍇ
        displaySwitchingMode(inputNumber);

    }

    /**
     * ���\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    private static void displayMultiplicationTable(boolean displaySwitching) {

        // �ؑփ��[�h�I���̏ꍇ
        if (displaySwitching) {
            calcMultiplicationTable(displaySwitching);
            return;
        }

        // �ؑփ��[�h�I�t�̏ꍇ
        calcMultiplicationTable();

    }

    /**
     * ���P�s�\���̓��͔��菈��
     * 
     * @param scanner          ���͏��
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    private static void displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {

        int line = calcMultiplicationLine();

        calcSingleLine(displaySwitching, line);

    }

    /**
     * �����p���m�F
     * 
     * @return isProcessingContinue �����p����True�B�����I����false�B
     */
    private static boolean isContinue() {
        boolean isCheck = false;

        String displayMsg = String.format("�����𑱂��܂����H(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END);

        do {
            String input = inputStr(displayMsg);

            // ���͒l�̔���
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    return true;
                case PROCESSING_END:
                    return false;
                default:
                    System.out.println(String.format("(%s/%s)�ȊO�����͂���܂����B\n�ē��͂����肢���܂��B", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return false;
    }

    /**
     * ���[�h����
     */
    private static DisplayMode inputDisplayMode() {
        DisplayMode displayMode = null;
        Display display = new Java13();
        String displayMsg = display.getDisplayModeString();
        do {
            displayMode = DisplayMode.convertDisplayMode(inputStr(displayMsg));
            if (displayMode == null) {
                System.out.println("�Y�����鏈�����[�h��������܂���ł����B");
            }

        } while (displayMode == null);
        return displayMode;
    }

}
