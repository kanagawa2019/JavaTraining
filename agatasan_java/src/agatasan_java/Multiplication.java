package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 九九表示処理クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 */
public class Multiplication {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    public static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 入力カーソル */
    public static final String CURSOL = ">";
    /** 九九の始まりの値 */
    public static final int MULTIPLICATION_TABLE_RANGE_START = 1;
    /** 九九の終わりの値 */
    public static final int MULTIPLICATION_TABLE_RANGE_END = 9;
    /** 表示形式 */
    public static final String DISPLAY_FORMAT = "%2s ";
    /** 九九の計算結果を除算する数値 */
    public static final int DIVIDE_BY_NUMBER = 3;
    /** 九九の計算結果に含まれる数値 */
    public static final int CONTAINS_NUMBER = 3;
    /** 指定数値で割り切れる、または指定数値を含む場合の表示 */
    public static final String DISPLAY_WHEN_MATCHING_CONDITIONS = "NA";
    /** 入力許容最小値 */
    public static final int USER_INPUT_MIN_VALUE = 1;
    /** 入力許容最大値 */
    public static final int USER_INPUT_MAX_VALUE = 9;

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------
    /**
     * 切替モードOFFの九九表示の処理
     * 
     */
    public static void calcMultiplicationTable() {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    /**
     * 切替モードONの九九表示の処理
     * 
     * @param displaySwitching 切替モードの状態
     */
//    public static void calcMultiplicationTable(final boolean displaySwitching) {
//
//        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
//            displaySwitchingMode(displaySwitching, i);
//        });
//
//    }

//    /**
//     * 切替モードONの場合の表示
//     * 
//     * @param displaySwitching 切替モードの状態
//     * @param inputNumber      入力文字
//     */
//    public static void displaySwitchingMode(final boolean displaySwitching, final int inputNumber) {
//
//        StringBuilder sb = new StringBuilder();
//
//        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
//
//            int calculationResult = i * inputNumber;
//
//            // 3の倍数は別表示
//            if (isMultipleOfTheeOrValueOfThree(calculationResult)) {
//                sb.append(String.format(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS));
//            } else {
//                sb.append(String.format(DISPLAY_FORMAT, calculationResult));
//            }
//            sb.append(String.format(DISPLAY_FORMAT, calculationResult));
//
//        });
//        System.out.println(sb.toString().replaceAll(" *$", ""));
//    }

    /**
     * 切替モードOFFの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    public static void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            sb.append(String.format(DISPLAY_FORMAT, i * inputNumber));
        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    /**
     * 九九１行表示の入力判定処理
     * 
     * @return 入力値
     */
    public static int calcMultiplicationLine() {

        do {
            // 数値か判定
            int inputLine = inputInt("表示する段を入力してください");

            // 入力許容範囲外はエラー
            if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                System.out.println(String.format("エラー！！%d～%d以外の数値が入力されました。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                System.out.println(String.format("%d～%dの数値を入力してください。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                continue;
            }

            return inputLine;

        } while (true);
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
     * @param inputMsg 入力コンソールに表示する文言
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

    public static void scannerClose() {
        System.out.println("終了します。");
        mScanner.close();
    }

    public static void test() {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    public static void test(int ssss) {
        displaySwitchingMode(ssss);
    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 検索対象に判定文字が含まれるかチェック
     * 
     * @param target            検索対象
     * @param judgmentCharacter 判定文字
     * @return 判定文字が含まれる場合、true。含まれない場合、false
     */
    public static boolean hasContainedCharacter(final int target, final int judgmentCharacter) {
        String targetStr = String.valueOf(target);
        return targetStr.contains(String.valueOf(judgmentCharacter));
    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return 最小値～最大値の範囲内にある場合、true。範囲外の場合、false
     */
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 3の倍数か3の値があるかを判定
     * 
     * @param calculationResult 判定対象
     * @return 3の倍数または3の値がある場合、True。3の倍数または3の値ではない場合、false。
     */
    private static boolean isMultipleOfTheeOrValueOfThree(final int calculationResult) {
        if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
            return true;
        }
        return false;
    }

}
