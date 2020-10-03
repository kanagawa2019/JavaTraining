package agatasan_java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Java9クラス Java9課題(ファイル入出力）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/09/27 新規作成
 */
public class Java9 {

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    private static Scanner scanner = new Scanner(System.in);
    /** 入力カーソル */
    private static final String CURSOL = ">";
    /** 入力許容最小値 */
    private static final int USER_INPUT_MIN_VALUE = -1000000;
    /** 入力許容最大値 */
    private static final int USER_INPUT_MAX_VALUE = 1000000;

    /**
     * 入力された数値を昇順でコンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        Map<String, Integer> userInfoMap = new HashMap<>();

        do {

            test();

            // ユーザー名取得
            String inputName = getUserinputName("ユーザ名を入力してください。", userInfoMap);

            // 金額取得
            int inputMoney = getUserinputMoney("金額を入力してください。");

            // 保存
            userInfoMap.put(inputName, inputMoney);

            System.out.println(String.format("登録しました。"));

        } while (isContinue());

        // 終了処理
        scanner.close();
        System.out.println("処理を終了しました。");

    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return true:最小値〜最大値の範囲内にある
     */
    private static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 処理継続確認
     * 
     * @return 続ける場合に、True
     */
    public static boolean isContinue() {
        String input = inputStr("処理を続けますか？(y/n)");
        return "Y".equals(input.toUpperCase());
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
                input = scanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * ユーザー名を取得
     * 
     * @param inputMsg    入力コンソール
     * @param userInfoMap ユーザー入力値保存マップ
     * @return 入力値
     */
    public static String getUserinputName(final String inputMsg, Map<String, Integer> userInfoMap) {
        String input = null;
        boolean isCheck = false;

        do {
            // 入力文字取得
            input = inputStr(inputMsg);
            // ユーザー名重複チェック
            if (userInfoMap.containsKey(input)) {
                System.out.println(String.format("\\%d円が登録済みです。", userInfoMap.get(input)));
                continue;
            }
            isCheck = true;
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
                input = scanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }
        } while (!isCheck);
        return num;

    }

    /**
     * 金額を取得
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
     */
    public static int getUserinputMoney(final String inputMsg) {
        int input = 0;
        boolean isCheck = false;

        do {
            // 入力数値取得
            input = inputInt(inputMsg);
            // 金額限度額チェック
            if (!isWithinRange(input, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                System.out.println(
                        String.format("数値範囲外です。%d〜%dの数値を入力してください。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                continue;
            }
            isCheck = true;
        } while (!isCheck);
        return input;
    }

    public static void test() {

        try {
            FileWriter fw = new FileWriter("test.txt");
            fw.write("テスト");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
