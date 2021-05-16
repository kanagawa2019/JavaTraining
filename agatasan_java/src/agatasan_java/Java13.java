package agatasan_java;

import java.util.stream.IntStream;

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
//    private static Scanner mScanner = new Scanner(System.in);
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
//                    displayMultiplicationTable(displaySwitching);
                    if (displaySwitching) {
                        test();
                    } else {
                        calcMultiplicationTable();
                    }
                    break;
                case MULTIPLICATION_LINE:
//                    displayMultiplicationLine(mScanner, displaySwitching);
//                    displayMultiplicationLine(displaySwitching);
                    if (displaySwitching) {
                        test(calcMultiplicationLine());
                    } else {

                    }
                    break;
                case SWITCHING:
                    displaySwitching = displaySwitching ? false : true;
                    System.out.println("���[�h��؂�ւ��܂����B");
                    break;
                case END:
//                    System.out.println("�I�����܂��B");
                    // TODO �ʓr�I�[�o�[���C�h��ō쐬����H
                    // �I������
//                    mScanner.close();
                    scannerClose();
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
//    private static void displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {
    private static void displayMultiplicationLine(boolean displaySwitching) {

        int line = calcMultiplicationLine();

        calcSingleLine(displaySwitching, line);

    }

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

    /**
     * �ؑփ��[�hON�̋��\���̏���
     * 
     * @param displaySwitching �ؑփ��[�h�̏��
     */
    public static void calcMultiplicationTable() {

        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    /**
     * �ؑփ��[�hON�̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    public static void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            int calculationResult = i * inputNumber;

            // 3�̔{���͕ʕ\��
            if (isMultipleOfTheeOrValueOfThree(calculationResult)) {
                sb.append(String.format(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS));
            } else {
                sb.append(String.format(DISPLAY_FORMAT, calculationResult));
            }
            sb.append(String.format(DISPLAY_FORMAT, calculationResult));

        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    /**
     * 3�̔{����3�̒l�����邩�𔻒�
     * 
     * @param calculationResult ����Ώ�
     * @return 3�̔{���܂���3�̒l������ꍇ�ATrue�B3�̔{���܂���3�̒l�ł͂Ȃ��ꍇ�Afalse�B
     */
    private static boolean isMultipleOfTheeOrValueOfThree(final int calculationResult) {
        if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
            return true;
        }
        return false;
    }

}
