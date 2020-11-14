package agatasan_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Java9�N���X Java9�ۑ�(�t�@�C�����o�́j
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/03 �V�K�쐬
 * @version 1.1 2020/10/10 �w�ENo.48,54��Ή�
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
    private static final String FILE_OUTPUT_PATH = "FILE_OUTPUT_PATH";
    /** �v���p�e�B�ݒ�p�X */
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";
    /** �t�@�C���ۑ����ʎq */
    private static final String SAVE_IDENTIFIER = "'";
    /** �t�@�C����؂莯�ʎq */
    private static final String SAVA_SEPARATION = ",";
    /** �G�X�P�[�v���ʎq */
    private static final char SAVA_ESCAPE = '\\';

    /**
     * ���͂��ꂽ���[�U�[���Ƌ��z��\�����܂��B
     * 
     */
    public static void main(String[] args) {

        try {
            // �O��̃��[�U�[�����擾
            Map<String, Integer> userInfoMap = getUserInfo();

            do {

                String inputName = null;
                do {
                    // ���[�U�[���擾
                    inputName = inputStr("���[�U������͂��Ă��������B");

                } while (isChecked(userInfoMap, inputName));

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

            // close�����邽�߂�catch����
        } catch (FileWriteException | FileReadException | IOException e) {
            System.out.println("�����𒆒f���܂����B�V�X�e���Ǘ��҂֖₢���킹���Ă��������B");
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
     * �o�^�ۃ`�F�b�N
     * 
     * @param userInfoMap ���[�U�[���A���z�ێ��}�b�v
     * @param inputName   ���͂��ꂽ���[�U�[��
     * @return ���[�U�[�����o�^�s�̏ꍇTrue�B�o�^�\�̏ꍇ��false�B
     */
    public static boolean isChecked(final Map<String, Integer> userInfoMap, final String inputName) {

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
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void createFile(final Map<String, Integer> map) throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo();

            FileWriter fw = new FileWriter(strPass);
            bw = new BufferedWriter(fw);

            // ���[�U�[���Ƌ��z���J���}��؂�ŘA��
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String str = String.format("%s%s%s%s%s%d%s", SAVE_IDENTIFIER, conversionEscape(entry.getKey()), SAVE_IDENTIFIER, SAVA_SEPARATION,
                        SAVE_IDENTIFIER, entry.getValue(), SAVE_IDENTIFIER);
                // ��������
                bw.write(str);
                // ���s
                bw.newLine();
            }
        } catch (IOException e) {
            throw new FileWriteException(e, String.format("�t�@�C���̏������݂Ɏ��s���܂����B�t�@�C����:%s", strPass));

        } finally {

            if (bw != null) {
                // ���鏈��
                bw.close();
            }
        }

    }

    /**
     * �O��̃��[�U�[�����擾
     * 
     * @return �O����͂������[�U�[���
     * @throws FileReadException
     * @throws IOException
     */
    public static Map<String, Integer> getUserInfo() throws FileReadException, IOException {

        Map<String, Integer> map = new HashMap<>();

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo();

            File file = new File(strPass);

            // �t�@�C�������݂��Ȃ��ꍇ(=����1��ڂ̏ꍇ)
            if (!file.exists()) {
                return map;
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {
                List<String> splitList = fileSplit(str);
                map.put(splitList.get(0), Integer.parseInt(splitList.get(1)));
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(e, "�O��̃��[�U�[����ۑ������t�@�C����ǂݍ��߂܂���ł����B");

        } finally {

            if (br != null) {
                // ���鏈��
                br.close();
            }
        }
        return map;
    }

    /**
     * �v���p�e�B�t�@�C���̒l���擾
     * 
     * @return ���[�U�[��񂪋L�ڂ���ăt�@�C���̊i�[�p�X
     * @throws FileNotFoundException
     * @throws FileReadException
     * @throws IOException
     */
    private static String getPropertiesInfo() throws FileNotFoundException, FileReadException, IOException {
        Properties properties = new Properties();

        String strPass = null;
        try {
            InputStream istream = new FileInputStream(INIT_PROPERTIES_PATH);
            properties.load(istream);

            strPass = properties.getProperty(FILE_OUTPUT_PATH);

            // �ݒ�t�@�C���ǂݍ��ݎ��s��
            if (strPass == null) {
                throw new FileReadException(new Exception(), "�v���p�e�B�t�@�C���ɋL�ڂ���Ă����`��������܂���ł����B");
            }

        } catch (FileNotFoundException e) {
            throw new FileReadException(e, "�v���p�e�B�t�@�C����������܂���ł����B");
        } catch (IOException e) {
            throw new FileReadException(e, "�v���p�e�B�t�@�C���̓ǂݍ��݂Ɏ��s���܂����B");

        }
        return strPass;
    }

    /**
     * �t�@�C����1�s�������[�U�[���Ƌ��z�ɕ�������
     * 
     * @param line �ǂݍ��ݍs
     * @return ������̃��X�g�B1�ڂ����[�U�[���A2�ڂ����z�B
     */
    private static List<String> fileSplit(final String line) {
        char c;
        StringBuilder sb = new StringBuilder();
        List<String> data = new ArrayList<String>();
        boolean singleQuoteFlag = false;
        boolean singleQuoteKeepingFlag = false;
        char[] separation = SAVA_SEPARATION.toCharArray();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();

        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (c == identifier[0] && singleQuoteFlag && singleQuoteKeepingFlag) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
                sb.append(c);
            } else if (c == separation[0] && !singleQuoteFlag) {
                data.add(sb.toString());
                sb.delete(0, sb.length());
            } else if (c == separation[0] && singleQuoteFlag) {
                sb.append(c);
            } else if (c == identifier[0]) {
                singleQuoteFlag = !singleQuoteFlag;
            } else if (c == SAVA_ESCAPE) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
            } else {
                sb.append(c);
            }

            // ���z��ݒ�
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

    /**
     * ���͒l���G�X�P�[�v���������ĕԂ�
     * 
     * @param input ���͒l
     * @return �G�X�P�[�v����
     */
    private static String conversionEscape(final String input) {
        char c;
        StringBuilder sb = new StringBuilder();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == identifier[0]) {
                sb.append(SAVA_ESCAPE);
            }
            sb.append(c);

        }
        return sb.toString();
    }

}
