package agatasan_java;

import java.util.Objects;
import java.util.Scanner;

/**
 * Java6クラス Java課題６(配列）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/05 新規作成
 * @version 1.1 2020/05/24 文字列結合、main処理関数化
 */
public class Java6 {

    /** 終了条件 */
    private static final String EXIT_CONDITIONS = "end";

    /** ユーザー最大入力回数 */
    private static final int USER_INPUT_MAX_NUMBER_OF_TIMES = 10;

    /** ユーザー入力促し文言 */
    private static final String USER_INPUT_SENTENCES = "数値を入力してください。";

    /** 入力許容最小値 */
    private static final int USER_INPUT_MIN_VALUE = -1000;

    /** 入力許容最大値 */
    private static final int USER_INPUT_MAX_VALUE = 1000;

    /** 入力許容範囲 */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "～" + USER_INPUT_MAX_VALUE;

    /**
     * 入力された数値を昇順でコンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        displaySentence();

        int[] inputNumbers = new int[USER_INPUT_MAX_NUMBER_OF_TIMES];
        int count = 0;

        while (scanner.hasNext()) {

            // 値取得
            String input = scanner.next();

            // endか判定
            if (Objects.equals(input, EXIT_CONDITIONS)) {
                // 終了
                break;
            }

            int userInputNumber = 0;
            // 数値か判定
            try {
                userInputNumber = Integer.valueOf(input).intValue();
            } catch (NumberFormatException e) {
                // 入力内容が数値以外の場合
                displayErrSentence();
                continue;
            }

            // 入力許容範囲外はエラー
            if (!isWithinRange(userInputNumber, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                displayErrSentence();
                continue;
            }

            // 配列へ追加
            if (count < USER_INPUT_MAX_NUMBER_OF_TIMES) {
                inputNumbers[count] = userInputNumber;
            }

            // 最大入力回数入力時の場合
            if (count == USER_INPUT_MAX_NUMBER_OF_TIMES - 1) {

                // 入力値を表示
                displayInputNumbers(inputNumbers);

                // 最大入力回数分入力したので初期化
                InitializationForReset(inputNumbers, count);

                continue;
            }

            count++;
            System.out.printf(String.format("%s個めが入力されました。%n引き続き、%s%n", count, USER_INPUT_SENTENCES));

        }

        // ctr + z or "end"入力で終了
        scanner.close();
        System.out.println("入力を終了しました。");

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
     * コンソールに表示する文言
     * 
     */
    private static void displaySentence() {
        System.out.println(USER_INPUT_SENTENCES);
        System.out.println("endで入力を終了します。");
    }

    /**
     * 入力内容が数値以外の場合、コンソールに表示する文言
     * 
     */
    private static void displayErrSentence() {
        System.out.println(String.format("エラー！！%s以外の数値が入力されました。", USER_INPUT_RANGE));
        System.out.println(String.format("%sの%s", USER_INPUT_RANGE, USER_INPUT_SENTENCES));
    }

    /**
     * 入力された数値を表示
     * 
     * @param inputNumbers 入力された数値
     */
    private static void displayInputNumbers(int[] inputNumbers) {
        System.out.println("並び替えて表示します。");
        // 昇順にソート
        int[] afterSortArray = sortArrayInAsc(inputNumbers);

        for (int number : afterSortArray) {
            System.out.println(number);
        }
    }

    /**
     * 数値配列を昇順に並び替え
     * 
     * @param numericalArray 並び替え対象の配列
     * @return 並び替え後の配列
     */
    static int[] sortArrayInAsc(int[] numericalArray) {

        for (int i = 0; i < numericalArray.length - 1; i++) {
            for (int j = numericalArray.length - 1; j > i; j--) {
                if (numericalArray[j - 1] > numericalArray[j]) {
                    int tmp = numericalArray[j - 1];
                    numericalArray[j - 1] = numericalArray[j];
                    numericalArray[j] = tmp;
                }
            }
        }
        return numericalArray;
    }

    /**
     * リセットのため初期化
     * 
     * @param inputNumbers 入力された数値
     * @param count        入力回数
     */
    static void InitializationForReset(int[] inputNumbers, int count) {

        System.out.println(String.format("%s回入力されたので、リセットしました。", USER_INPUT_MAX_NUMBER_OF_TIMES));
        displaySentence();
        inputNumbers = new int[USER_INPUT_MAX_NUMBER_OF_TIMES];
        count = 0;

    }

}
