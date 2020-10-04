package agatasan_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Java9クラス Java9課題(ファイル入出力）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/03 新規作成
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
    private static final String FILE_OUTPUT_PATH = "C:\\Users\\green3\\Documents\\git_java_training\\gittest\\agatasan_java\\userInfo.txt";

    /**
     * 入力されたユーザー名と金額を表示します。
     * 
     */
    public static void main(String[] args) {

        // 前回のユーザー情報を取得
        Map<String, Integer> userInfoMap = getUserInfo();

        do {

            boolean nameloopFlag = false;
            String inputName = null;
            do {
                // ユーザー名取得
                inputName = inputStr("ユーザ名を入力してください。");

                // ユーザー名登録重複チェック
                nameloopFlag = isDuplicate(userInfoMap, inputName);

            } while (nameloopFlag);

            boolean moneyloopFlag = false;
            int inputMoney = 0;
            do {
                // 金額取得
                inputMoney = inputInt("金額を入力してください。");

                // 金額限度額チェック
                moneyloopFlag = isOutOfRange(inputMoney);

            } while (moneyloopFlag);

            // 保存
            userInfoMap.put(inputName, inputMoney);

            System.out.println(String.format("登録しました。"));

        } while (isContinue());

        // 次回再開時用にユーザー情報をファイルに保存
        createFile(userInfoMap);

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
                    System.out.println(
                            String.format("(%s/%s)以外が入力されました。\n再入力をお願いします。", PROCESSING_CONTINUE, PROCESSING_END));
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
     * ユーザー名登録重複チェック
     * 
     * @param userInfoMap ユーザー名、金額保持マップ
     * @param inputName   入力されたユーザー名
     * @return ユーザー名がすでに登録されている場合はTrue。登録されていない場合はfalse。
     */
    public static boolean isDuplicate(final Map<String, Integer> userInfoMap, final String inputName) {

        // ユーザー名が既に登録されている場合
        if (userInfoMap.containsKey(inputName)) {
            System.out.println(String.format("\\%d円が登録済みです。", userInfoMap.get(inputName)));
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
            System.out.println(String.format("数値範囲外です。%d～%dの数値を入力してください。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
            return true;
        }
        return false;
    }

    /**
     * ユーザー情報をファイルに保存
     * 
     * @param map ユーザー情報
     */
    public static void createFile(final Map<String, Integer> map) {

        try {
            FileWriter fw = new FileWriter(FILE_OUTPUT_PATH);
            BufferedWriter bw = new BufferedWriter(fw);

            // ユーザー名と金額をカンマ区切りで連結
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String str = String.format("%s,%d", entry.getKey(), entry.getValue());
                // 書き込み
                bw.write(str);
                // 改行
                bw.newLine();
            }
            // 閉じる処理
            bw.close();
        } catch (IOException ex) {
            System.out.println("ユーザー情報のファイル書き込みに失敗しました。");
            ex.printStackTrace();
        }

    }

    /**
     * 前回のユーザー情報を取得
     * 
     * @return 前回入力したユーザー情報
     */
    public static Map<String, Integer> getUserInfo() {

        Map<String, Integer> map = new HashMap<>();

        try {
            File file = new File(FILE_OUTPUT_PATH);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {
                String[] array = str.split(",");
                map.put(array[0], Integer.parseInt(array[1]));
                str = br.readLine();
            }
            // 閉じる処理
            br.close();
        } catch (FileNotFoundException e) {
            // ファイルが存在しない場合
            // 最初に初期化したmapを返す
            return map;
        } catch (IOException e) {
            System.out.println("ユーザー情報のファイル読み込みに失敗しました。");
            e.printStackTrace();
        }
        return map;
    }
}
