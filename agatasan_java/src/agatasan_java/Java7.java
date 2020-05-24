package agatasan_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Java7クラス Java課題７(リスト）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/17 新規作成
 * @version 1.1 2020/05/24 文字列結合、main処理関数化
 */
public class Java7 {

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

    /** 入力許容範囲文言 */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "～" + USER_INPUT_MAX_VALUE;

    /**
     * 入力された数値を昇順でコンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        displaySentence();

        List<Integer> inputList = new ArrayList<>();

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

            inputList.add(userInputNumber);

            // 最大入力回数入力時の場合、入力値を表示
            if (inputList.size() == USER_INPUT_MAX_NUMBER_OF_TIMES) {

                // 入力値を表示
                displayInputNumbers(inputList);

                // 最大入力回数分入力したので初期化
                InitializationForReset(inputList);

                continue;
            }

            System.out.printf(String.format("%s個めが入力されました。%n引き続き、%s%n", inputList.size(), USER_INPUT_SENTENCES));

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
     * @param list 入力された数値のリスト
     */
    private static void displayInputNumbers(List<Integer> list) {
        // 昇順にソート
        Collections.sort(list);
        System.out.println("並び替えて表示します。");
        list.forEach(System.out::println);
    }

    /**
     * リセットのため初期化
     * 
     * @param list 入力された数値のリスト
     */
    static void InitializationForReset(List<Integer> list) {

        System.out.println(String.format("%s回入力されたので、リセットしました。", USER_INPUT_MAX_NUMBER_OF_TIMES));
        displaySentence();
        list = new ArrayList<>();
    }
}
