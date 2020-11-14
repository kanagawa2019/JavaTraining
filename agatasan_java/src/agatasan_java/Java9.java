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
 * Java9クラス Java9課題(ファイル入出力）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/03 新規作成
 * @version 1.1 2020/10/10 指摘No.48,54を対応
 */
public class Java9 {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    private static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 入力カーソル */
    private static final String CURSOL = ">";
    /** 入力許容最小値 */
    private static final int USER_INPUT_MIN_VALUE = -1000000;
    /** 入力許容最大値 */
    private static final int USER_INPUT_MAX_VALUE = 1000000;
    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";
    /** ファイル出力パス */
    private static final String FILE_OUTPUT_PATH = "FILE_OUTPUT_PATH";
    /** プロパティ設定パス */
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";
    /** ファイル保存識別子 */
    private static final String SAVE_IDENTIFIER = "'";
    /** ファイル区切り識別子 */
    private static final String SAVA_SEPARATION = ",";
    /** エスケープ識別子 */
    private static final char SAVA_ESCAPE = '\\';

    /**
     * 入力されたユーザー名と金額を表示します。
     * 
     */
    public static void main(String[] args) {

        try {
            // 前回のユーザー情報を取得
            Map<String, Integer> userInfoMap = getUserInfo();

            do {

                String inputName = null;
                do {
                    // ユーザー名取得
                    inputName = inputStr("ユーザ名を入力してください。");

                } while (isChecked(userInfoMap, inputName));

                int inputMoney = 0;
                do {
                    // 金額取得
                    inputMoney = inputInt("金額を入力してください。");

                } while (isOutOfRange(inputMoney));

                // 保存
                userInfoMap.put(inputName, inputMoney);

                System.out.println("登録しました。");

            } while (isContinue());

            // 次回再開時用にユーザー情報をファイルに保存
            createFile(userInfoMap);

            // closeさせるためにcatchする
        } catch (FileWriteException | FileReadException | IOException e) {
            System.out.println("処理を中断しました。システム管理者へ問い合わせしてください。");
        }

        // 終了処理
        mScanner.close();
        System.out.println("処理を終了しました。");

    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return true:最小値～最大値の範囲内にある
     */
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 処理継続確認
     * 
     * @return isProcessingContinue 処理継続はTrue。処理終了はfalse。
     */
    public static boolean isContinue() {
        boolean isCheck = false;
        boolean isProcessingContinue = false;

        do {
            String input = inputStr(String.format("処理を続けますか？(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END));

            // 入力値の判定
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    isCheck = true;
                    isProcessingContinue = true;
                    break;
                case PROCESSING_END:
                    isCheck = true;
                    break;
                default:
                    System.out.println(String.format("(%s/%s)以外が入力されました。\n再入力をお願いします。", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return isProcessingContinue;
    }

    /**
     * 文字入力
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
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
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * 数値入力(int型)
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
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
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }
        } while (!isCheck);
        return num;

    }

    /**
     * 登録可否チェック
     * 
     * @param userInfoMap ユーザー名、金額保持マップ
     * @param inputName   入力されたユーザー名
     * @return ユーザー名が登録不可の場合True。登録可能の場合はfalse。
     */
    public static boolean isChecked(final Map<String, Integer> userInfoMap, final String inputName) {

        // ユーザー名が既に登録されている場合
        if (userInfoMap.containsKey(inputName)) {
            System.out.println(String.format("\\%,d円が登録済みです。", userInfoMap.get(inputName)));
            return true;
        }

        return false;
    }

    /**
     * 金額限度額チェック
     * 
     * @param inputMoney 入力された金額
     * @return 入力された金額が限度範囲外の場合はTrue。範囲内の場合はfalse。
     */
    public static boolean isOutOfRange(final int inputMoney) {

        // 金額が範囲外の場合
        if (!isWithinRange(inputMoney, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
            System.out.println(String.format("数値範囲外です。%,d～%,dの数値を入力してください。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
            return true;
        }
        return false;
    }

    /**
     * ユーザー情報をファイルに保存
     * 
     * @param map ユーザー情報
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

            // ユーザー名と金額をカンマ区切りで連結
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String str = String.format("%s%s%s%s%s%d%s", SAVE_IDENTIFIER, conversionEscape(entry.getKey()), SAVE_IDENTIFIER, SAVA_SEPARATION,
                        SAVE_IDENTIFIER, entry.getValue(), SAVE_IDENTIFIER);
                // 書き込み
                bw.write(str);
                // 改行
                bw.newLine();
            }
        } catch (IOException e) {
            throw new FileWriteException(e, String.format("ファイルの書き込みに失敗しました。ファイル名:%s", strPass));

        } finally {

            if (bw != null) {
                // 閉じる処理
                bw.close();
            }
        }

    }

    /**
     * 前回のユーザー情報を取得
     * 
     * @return 前回入力したユーザー情報
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

            // ファイルが存在しない場合(=処理1回目の場合)
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
            throw new FileReadException(e, "前回のユーザー情報を保存したファイルを読み込めませんでした。");

        } finally {

            if (br != null) {
                // 閉じる処理
                br.close();
            }
        }
        return map;
    }

    /**
     * プロパティファイルの値を取得
     * 
     * @return ユーザー情報が記載されてファイルの格納パス
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

            // 設定ファイル読み込み失敗時
            if (strPass == null) {
                throw new FileReadException(new Exception(), "プロパティファイルに記載されている定義が見つかりませんでした。");
            }

        } catch (FileNotFoundException e) {
            throw new FileReadException(e, "プロパティファイルが見つかりませんでした。");
        } catch (IOException e) {
            throw new FileReadException(e, "プロパティファイルの読み込みに失敗しました。");

        }
        return strPass;
    }

    /**
     * ファイルの1行分をユーザー名と金額に分割する
     * 
     * @param line 読み込み行
     * @return 分割後のリスト。1つ目がユーザー名、2つ目が金額。
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

            // 金額を設定
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

    /**
     * 入力値をエスケープ処理をして返す
     * 
     * @param input 入力値
     * @return エスケープ処理
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
