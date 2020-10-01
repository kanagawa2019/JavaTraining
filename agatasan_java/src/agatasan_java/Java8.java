package agatasan_java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Java8�N���X Java8�ۑ�(�}�b�v�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/08/10 �V�K�쐬
 * @version 1.1 2020/10/02 �w�ENo.41�`45��Ή�
 */
public class Java8 {

    /** �X�L���i�[�i�R���\�[�����́j */
    private static Scanner scanner = new Scanner(System.in);

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";
    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = -1000000;
    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 1000000;
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";

    /**
     * ���͂��ꂽ���[�U�[���Ƌ��z��\�����܂��B
     * 
     */
    public static void main(String[] args) {

        // ���[�U�[���A���z�ێ��}�b�v
        Map<String, Integer> userInfoMap = new HashMap<>();

        do {

            boolean nameloopFlag = false;
            String inputName = null;
            do {
                // ���[�U�[���擾
                inputName = getUserName("���[�U������͂��Ă��������B", userInfoMap);

                // ���[�U�[���o�^�d���`�F�b�N
                if (userInfoMap.containsKey(inputName)) {
                    System.out.println(String.format("\\%d�~���o�^�ς݂ł��B", userInfoMap.get(inputName)));
                    nameloopFlag = true;
                } else {
                    nameloopFlag = false;
                }
            } while (nameloopFlag);

            boolean moneyloopFlag = false;
            int inputMoney = 0;
            do {
                // ���z�擾
                inputMoney = getUserMoney("���z����͂��Ă��������B");

                // ���z���x�z�`�F�b�N
                if (!isWithinRange(inputMoney, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                    System.out.println(
                            String.format("���l�͈͊O�ł��B%d�`%d�̐��l����͂��Ă��������B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                    moneyloopFlag = true;
                } else {
                    moneyloopFlag = false;
                }
            } while (moneyloopFlag);

            // �ۑ�
            userInfoMap.put(inputName, inputMoney);

            System.out.println(String.format("�o�^���܂����B"));

        } while (isContinue());

        // �I������
        scanner.close();
        System.out.println("�������I�����܂����B");

    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return true:�ŏ��l�`�ő�l�͈͓̔��ɂ���
     */
    private static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * �����p���m�F
     * 
     * @return isProcessingContinue �����p����True�B�����I����false�B
     */
    public static boolean isContinue() {
        boolean isCheck = false;
        boolean isProcessingContinue = false;

        do {
            String input = inputStr(String.format("�����𑱂��܂����H(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END));

            // ���͒l�̔���
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    isCheck = true;
                    isProcessingContinue = true;
                    break;
                case PROCESSING_END:
                    isCheck = true;
                    break;
                default:
                    System.out.println(
                            String.format("(%s/%s)�ȊO�����͂���܂����B\n�ē��͂����肢���܂��B", PROCESSING_CONTINUE, PROCESSING_END));
            }

        } while (!isCheck);

        return isProcessingContinue;
    }

    /**
     * ��������
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    public static String inputStr(final String inputMsg) {
        String input = null;
        boolean isCheck = false;

        System.out.println(inputMsg);
        do {
            try {
                System.out.print(CURSOL);
                input = scanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * ���[�U�[�����擾
     * 
     * @param inputMsg    ���̓R���\�[��
     * @param userInfoMap ���[�U�[���͒l�ۑ��}�b�v
     * @return ���͒l
     */
    public static String getUserName(final String inputMsg, Map<String, Integer> userInfoMap) {
        String input = null;
        boolean isCheck = false;

        do {
            // ���͕����擾
            input = inputStr(inputMsg);
            isCheck = true;
        } while (!isCheck);
        return input;
    }

    /**
     * ���l����(int�^)
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    public static int inputInt(final String inputMsg) {
        int num = 0;
        String input = null;
        boolean isCheck = false;

        if (inputMsg != null) {
            System.out.println(inputMsg);
        }

        do {
            try {
                System.out.print(CURSOL);
                input = scanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }
        } while (!isCheck);
        return num;

    }

    /**
     * ���z���擾
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    public static int getUserMoney(final String inputMsg) {
        int input = 0;
        boolean isCheck = false;

        do {
            // ���͐��l�擾
            input = inputInt(inputMsg);
            isCheck = true;
        } while (!isCheck);
        return input;
    }

}
