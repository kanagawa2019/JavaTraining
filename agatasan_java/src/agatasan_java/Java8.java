package agatasan_java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Java8クラス Java8課題(マップ）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/08/10 新規作成
 * @version 1.1 2020/10/02 指摘No.41～45を対応
 * @version 1.2 2020/10/03 指摘No.41,43,44,46を対応
 */
public class Java8 {

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

    /**
     * 入力されたユーザー名と金額を表示します。
     * 
     */
    public static void main(String[] args) {

        // ユーザー名、金額保持マップ
        Map<String, Integer> userInfoMap = new HashMap<>();

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
    private static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
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
}
