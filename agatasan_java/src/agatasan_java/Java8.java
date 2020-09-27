package agatasan_java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Java8�N���X Java8�ۑ�(�}�b�v�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/08/10 �V�K�쐬
 */
public class Java8 {

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** �X�L���i�[�i�R���\�[�����́j */
    private static Scanner scanner = new Scanner(System.in);
    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";
    /** ���͋��e�ŏ��l */
    private static final int USER_INPUT_MIN_VALUE = -1000000;
    /** ���͋��e�ő�l */
    private static final int USER_INPUT_MAX_VALUE = 1000000;

    /**
     * ���͂��ꂽ���l�������ŃR���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {

        Map<String, Integer> userInfoMap = new HashMap<>();

        do {
            // ���[�U�[���擾
            String inputName = getUserinputName("���[�U������͂��Ă��������B", userInfoMap);

            // ���z�擾
            int inputMoney = getUserinputMoney("���z����͂��Ă��������B");

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
     * @return ������ꍇ�ɁATrue
     */
    public static boolean isContinue() {
        String input = inputStr("�����𑱂��܂����H(y/n)");
        return "Y".equals(input.toUpperCase());
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
                System.out.println(CURSOL);
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
    public static String getUserinputName(final String inputMsg, Map<String, Integer> userInfoMap) {
        String input = null;
        boolean isCheck = false;

        do {
            // ���͕����擾
            input = inputStr(inputMsg);
            // ���[�U�[���d���`�F�b�N
            if (userInfoMap.containsKey(input)) {
                System.out.println(String.format("%d�~���o�^�ς݂ł��B", userInfoMap.get(input)));
                continue;
            }
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
                System.out.println(CURSOL);
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
    public static int getUserinputMoney(final String inputMsg) {
        int input = 0;
        boolean isCheck = false;

        do {
            // ���͐��l�擾
            input = inputInt(inputMsg);
            // ���z���x�z�`�F�b�N
            if (!isWithinRange(input, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                System.out.println(
                        String.format("���l�͈͊O�ł��B%d�`%d�̐��l����͂��Ă��������B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                continue;
            }
            isCheck = true;
        } while (!isCheck);
        return input;
    }

}
