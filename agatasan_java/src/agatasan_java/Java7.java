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
 * @version 1.0 2020/05/17
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

    /** 数値入力促し文言 */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "の" + USER_INPUT_SENTENCES;

    /** 数値以外の入力時エラー文言 */
    private static final String NON_NUMERIC_ERROR = "エラー！！" + USER_INPUT_RANGE + "以外の数値が入力されました。";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        displaySentence();

        List<Integer> list = new ArrayList<>();

        while (scanner.hasNext()) {

            // 値取得
            String input = scanner.next();

            // endか判定
            if (Objects.equals(input, EXIT_CONDITIONS)) {
                // 終了
                break;
            }

            int line = 0;
            // 数値か判定
            try {
                line = Integer.valueOf(input).intValue();
            } catch (NumberFormatException e) {
                // 入力内容が数値以外の場合
                displayErrSentence();
                continue;
            }

            // 入力許容範囲外はエラー
            if (!isWithinRange(line, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                displayErrSentence();
                continue;
            }

            // 最大入力回数入力時の場合、入力値を表示
            if (list.size() == (USER_INPUT_MAX_NUMBER_OF_TIMES - 1)) {

                list.add(line);

                Collections.sort(list);
                System.out.println("入力値を昇順で表示します。");
                list.forEach(System.out::println);

                // 最大入力回数分入力したので初期化
                System.out.println(USER_INPUT_MAX_NUMBER_OF_TIMES + "回入力されたので、リセットしました。");
                displaySentence();
                list = new ArrayList<>();
                continue;
            }

            list.add(line);
            System.out.printf(list.size() + "個めが入力されました。%n引き続き、数値を入力してください。%n");

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
        System.out.println(NON_NUMERIC_ERROR);
        System.out.println(NUMBER_INPUT_SENTENCES);
    }
}
