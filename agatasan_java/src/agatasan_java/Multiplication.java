package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * ���\�������N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 * @version 1.1 2021/05/16 No.92,93,94�w�E�Ή�
 * @version 1.2 2021/05/21 No.105�w�E�Ή�
 */
public class Multiplication implements Display {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �X�L���i�[�i�R���\�[�����́j */
    public Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** ���̎n�܂�̒l */
    protected static final int MULTIPLICATION_TABLE_RANGE_START = 1;
    /** ���̏I���̒l */
    protected static final int MULTIPLICATION_TABLE_RANGE_END = 9;
    /** �\���`�� */
    protected static final String DISPLAY_FORMAT = "%2s ";
    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = 1;
    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 9;
    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";

    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------

    /**
     * �ؑփ��[�hOFF�̋��\���̏���
     * 
     */
    @Override
    public void calcMultiplicationTable() {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    /**
     * �ؑփ��[�hOFF�̋��1�s�\���̏���
     * 
     * @param number ���͕���
     */
    @Override
    public void calcMultiplicationTable(final int number) {
        displaySwitchingMode(number);
    }

    /**
     * ���P�s�\���̓��͔��菈��
     * 
     * @return ���͒l
     */
    @Override
    public int calcMultiplicationLine() {

        do {
            // ���l������
            int inputLine = inputInt("�\������i����͂��Ă�������");

            // ���͋��e�͈͊O�̓G���[
            if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                System.out.println(String.format("�G���[�I�I%d�`%d�ȊO�̐��l�����͂���܂����B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                System.out.println(String.format("%d�`%d�̐��l����͂��Ă��������B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                continue;
            }

            return inputLine;

        } while (true);
    }

    /**
     * �����p���m�F
     * 
     * @return �����p����True�B�����I����false�B
     */
    @Override
    public boolean isContinue() {
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
     * �I������
     */
    @Override
    public void scannerClose() {
        System.out.println("�I�����܂��B");
        mScanner.close();
    }

    /**
     * ���[�h����
     */
    @Override
    public DisplayMode inputDisplayMode(boolean displaySwitching) {
        DisplayMode displayMode = null;
        String displayMsg = getDisplayModeString(displaySwitching);
        do {
            displayMode = DisplayMode.convertDisplayMode(inputStr(displayMsg));
            if (displayMode == null) {
                System.out.println("�Y�����鏈�����[�h��������܂���ł����B");
            }

        } while (displayMode == null);
        return displayMode;
    }

    /**
     * �ؑփ��[�hOFF�̏ꍇ�̕\��
     * 
     * @param inputNumber ���͕���
     */
    public void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            sb.append(String.format(DISPLAY_FORMAT, i * inputNumber));
        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------

    /**
     * ���l����(int�^)
     * 
     * @param inputMsg ���̓R���\�[���ɕ\�����镶��
     * @return ���͒l
     */
    private int inputInt(final String inputMsg) {
        int num = 0;
        String input = null;
        boolean isCheck = false;

        if (inputMsg != null) {
            System.out.println(inputMsg);
        }

        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }

        } while (!isCheck);
        return num;
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return �ŏ��l�`�ő�l�͈͓̔��ɂ���ꍇ�Atrue�B�͈͊O�̏ꍇ�Afalse
     */
    private boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * ���[�h�I��\�������擾
     */
    private String getDisplayModeString(final boolean displaySwitching) {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("�������[�h��I�����Ă�������").append("\n");
        sb.append(DisplayMode.getSelectDisplayModeString(displaySwitching));
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * ��������
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    private String inputStr(final String inputMsg) {
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
}
