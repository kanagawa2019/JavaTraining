package agatasan_java;

import java.util.Scanner;

/**
 * Java�P�R�N���X Java�ۑ�P�R(�I�u�W�F�N�g�w���F�p���E���d���j
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 * @version 1.1 2021/05/16 No.106,107�w�E�Ή�
 */
public class Java13 {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �ؑփ��[�h */
    private static boolean displaySwitching = false;
    /** �X�L���i�[�i�R���\�[�����́j */
    private static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";
    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";

    /**
     * ���͂��ꂽ�������[�h�̏�����\�����܂��B
     * 
     */
    public static void main(String[] args) {

        do {
            // --------------------------------------------------
            // ����
            // --------------------------------------------------
            // �������[�h����
            final DisplayMode displayMode = inputDisplayMode();

            Display display = displaySwitching ? new Nabeatsu() : new Multiplication();

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
                    System.out.println("�I�����܂��B");
                    mScanner.close();
                    return;
                default:
                    break;
            }

        } while (isContinue());

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------

    /**
     * �����p���m�F
     * 
     * @return �����p����True�B�����I����false�B
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
     * ��������
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    private static String inputStr(final String inputMsg) {
        String input = null;
        boolean isCheck = false;

        System.out.println(inputMsg);
        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * ���[�h�I��\�������擾
     */
    private static String getDisplayModeString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("�������[�h��I�����Ă�������").append("\n");
        sb.append(DisplayMode.getSelectDisplayModeString(displaySwitching));
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * ���[�h����
     */
    private static DisplayMode inputDisplayMode() {
        DisplayMode displayMode = null;
        String displayMsg = getDisplayModeString();
        do {
            displayMode = DisplayMode.convertDisplayMode(inputStr(displayMsg));
            if (displayMode == null) {
                System.out.println("�Y�����鏈�����[�h��������܂���ł����B");
            }

        } while (displayMode == null);
        return displayMode;
    }
}
