package agatasan_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Java9�N���X Java9�ۑ�(�t�@�C�����o�́j
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/03 �V�K�쐬
 * @version 1.1 2020/10/10 �w�ENo.48��Ή�
 */
public class Java9 {

    // --------------------------------------------------
    // �����o�ϐ�
    // --------------------------------------------------
    /** �X�L���i�[�i�R���\�[�����́j */
    private static Scanner mScanner = new Scanner(System.in);

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
    /** �t�@�C���o�̓p�X */
//    private static final String FILE_OUTPUT_PATH = "C:\\Users\\green3\\Documents\\git_java_training\\gittest\\agatasan_java\\userInfo.txt";
    private static final String FILE_OUTPUT_PATH = "FILE_OUTPUT_PATH";
    /** �v���p�e�B�ݒ�p�X */
//    private static final String INIT_PROPERTIES_PATH = "C:\\Users\\green3\\Documents\\git_java_training\\gittest\\agatasan_java\\Setting.properties";
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";

//    /** �v���p�e�B�t�@�C���� */
//    private static final String INIT_PROPERTIES = "Setting.properties";

    /**
     * ���͂��ꂽ���[�U�[���Ƌ��z��\�����܂��B
     * 
     * @throws IOException
     * 
     */
    public static void main(String[] args) throws IOException {

        try {
            // �O��̃��[�U�[�����擾
            Map<String, Integer> userInfoMap = getUserInfo();

            do {

                String inputName = null;
                do {
                    // ���[�U�[���擾
                    inputName = inputStr("���[�U������͂��Ă��������B");

                } while (isDuplicate(userInfoMap, inputName));

                int inputMoney = 0;
                do {
                    // ���z�擾
                    inputMoney = inputInt("���z����͂��Ă��������B");

                } while (isOutOfRange(inputMoney));

                // �ۑ�
                userInfoMap.put(inputName, inputMoney);

                System.out.println("�o�^���܂����B");

            } while (isContinue());

            // ����ĊJ���p�Ƀ��[�U�[�����t�@�C���ɕۑ�
            createFile(userInfoMap);
        } catch (FileReadException e) {
        }

        // �I������
        mScanner.close();
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
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
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
                    System.out.println(String.format("(%s/%s)�ȊO�����͂���܂����B\n�ē��͂����肢���܂��B", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
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
                input = mScanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }
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
     * ���[�U�[���o�^�d���`�F�b�N
     * 
     * @param userInfoMap ���[�U�[���A���z�ێ��}�b�v
     * @param inputName   ���͂��ꂽ���[�U�[��
     * @return ���[�U�[�������łɓo�^����Ă���ꍇ��True�B�o�^����Ă��Ȃ��ꍇ��false�B
     */
    public static boolean isDuplicate(final Map<String, Integer> userInfoMap, final String inputName) {

        // ���[�U�[�������ɓo�^����Ă���ꍇ
        if (userInfoMap.containsKey(inputName)) {
            System.out.println(String.format("\\%,d�~���o�^�ς݂ł��B", userInfoMap.get(inputName)));
            return true;
        }

        return false;
    }

    /**
     * ���z���x�z�`�F�b�N
     * 
     * @param inputMoney ���͂��ꂽ���z
     * @return ���͂��ꂽ���z�����x�͈͊O�̏ꍇ��True�B�͈͓��̏ꍇ��false�B
     */
    public static boolean isOutOfRange(final int inputMoney) {

        // ���z���͈͊O�̏ꍇ
        if (!isWithinRange(inputMoney, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
            System.out.println(String.format("���l�͈͊O�ł��B%,d�`%,d�̐��l����͂��Ă��������B", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
            return true;
        }
        return false;
    }

    /**
     * ���[�U�[�����t�@�C���ɕۑ�
     * 
     * @param map ���[�U�[���
     */
    public static void createFile(final Map<String, Integer> map) {

        try {
            FileWriter fw = new FileWriter(FILE_OUTPUT_PATH);
            BufferedWriter bw = new BufferedWriter(fw);

            // ���[�U�[���Ƌ��z���J���}��؂�ŘA��
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String str = String.format("%s,%d", entry.getKey(), entry.getValue());
                // ��������
                bw.write(str);
                // ���s
                bw.newLine();
            }
            // ���鏈��
            bw.close();
        } catch (IOException ex) {
            System.out.println("���[�U�[���̃t�@�C���������݂Ɏ��s���܂����B");
            ex.printStackTrace();
        }

    }

    /**
     * �O��̃��[�U�[�����擾
     * 
     * @return �O����͂������[�U�[���
     * @throws IOException
     */
    public static Map<String, Integer> getUserInfo() throws FileReadException, IOException {

        Map<String, Integer> map = new HashMap<>();

//        try {
//            File file = new File(FILE_OUTPUT_PATH);
//            FileReader fileReader = new FileReader(file);
//            BufferedReader br = new BufferedReader(fileReader);
//            String str = br.readLine();
//            while (str != null) {
//                String[] array = str.split(",");
//                map.put(array[0], Integer.parseInt(array[1]));
//                str = br.readLine();
//            }
//            // ���鏈��
//            br.close();
//        } catch (FileNotFoundException e) {
//            // �t�@�C�������݂��Ȃ��ꍇ
//            // �ŏ��ɏ���������map��Ԃ�
//            return map;
//        } catch (IOException e) {
//            System.out.println("���[�U�[���̃t�@�C���ǂݍ��݂Ɏ��s���܂����B");
//            e.printStackTrace();
//        }

//        String osname = System.getProperty("os.name");
//        if (osname.indexOf("Windows") >= 0) {
//            // Windows�ł������Ƃ��̏���
//
//        } else if (osname.indexOf("Linux") >= 0) {
//            // Linux�ł������Ƃ��̏���
//        } else if (osname.indexOf("Mac") >= 0) {
//            // MacOS�ł������Ƃ��̏���
//        } else {
//            // ���̑��̊��������Ƃ��̏���
//        }

//        String path = new File(".").getAbsoluteFile().getParent();
//        System.out.println(path);
//
//        File file2 = new File("Setting.properties");

        // �t�@�C���p�X���擾����
//        String str2 = file2.getAbsolutePath();

        Properties properties = new Properties();

        String strPass = null;
        BufferedReader br = null;
        try {
            InputStream istream = new FileInputStream(INIT_PROPERTIES_PATH);
            properties.load(istream);

            strPass = properties.getProperty(FILE_OUTPUT_PATH);

            File file = new File(strPass);
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {
                String[] array = str.split(",");
                map.put(array[0], Integer.parseInt(array[1]));
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(strPass, e);

        } finally {
            // ���鏈��
            br.close();
        }
        return map;
    }
}
