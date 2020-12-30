package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Multiplication extends Display {

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

    /** ユーザー入力促し文言 */
    public static final String USER_INPUT_SENTENCES = "数値を入力してください。";

    /** 入力許容最小値 */
    public static final int USER_INPUT_MIN_VALUE = 1;

    /** 入力許容最大値 */
    public static final int USER_INPUT_MAX_VALUE = 9;

    /** 入力許容範囲 */
    public static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "～" + USER_INPUT_MAX_VALUE;

    /** 数値入力促し文言 */
    public static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "の" + USER_INPUT_SENTENCES;

    /** 数値以外の入力時エラー文言 */
    public static final String NON_NUMERIC_ERROR = "エラー！！" + USER_INPUT_RANGE + "以外の数値が入力されました。";

    /**
     * 九九表示の処理
     * 
     * @param displaySwitching 切替モードの状態
     */
    public static void displayMultiplicationTableTest() {

        // 切替モードオフの場合
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingModeOffTest(i);

        });

    }

    public static void displayMultiplicationTableTest(boolean displaySwitching) {

        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingModeOn(displaySwitching, i);
        });

    }

    /**
     * 切替モードオンの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    public static void displaySwitchingModeOn(boolean displaySwitching, int inputNumber) {

        IntStream.rangeClosed(Multiplication.MULTIPLICATION_TABLE_RANGE_START, Multiplication.MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            int calculationResult = i * inputNumber;

            // 3の倍数は別表示
            if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
                System.out.printf(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS);
            } else {
                System.out.printf(DISPLAY_FORMAT, calculationResult);
            }

        });
        System.out.printf("%n");
    }

    /**
     * 検索対象に判定文字が含まれるかチェック
     * 
     * @param target            検索対象
     * @param judgmentCharacter 判定文字
     * @return true:判定文字が含まれる
     */
    private static boolean hasContainedCharacter(int target, int judgmentCharacter) {
        String targetStr = String.valueOf(target);
        return targetStr.contains(String.valueOf(judgmentCharacter));
    }

    /**
     * 切替モードオフの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    public static void displaySwitchingModeOffTest(int inputNumber) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            System.out.printf(DISPLAY_FORMAT, i * inputNumber);
        });
        System.out.printf("%n");
    }

    /**
     * 九九１行表示の入力判定処理
     * 
     * @param scanner          入力情報
     * @param displaySwitching 切替モードの状態
     */
    public static int displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {
        System.out.println("表示する段を入力してください");

        do {
            // 数値か判定
            try {
                int inputLine = Integer.valueOf(scanner.next()).intValue();

                // 入力許容範囲外はエラー
                if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                return inputLine;
            } catch (NumberFormatException e) {
                // 入力内容が数値以外の場合
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }
        } while (true);
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
}
