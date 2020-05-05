package agatasan_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Java6クラス Java課題６(配列）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/05
 */
public class Java6 {

    /** 終了条件 */
    private static final String EXIT_CONDITIONS = "end";

    /** ユーザー最大入力回数 */
    private static final int USER_INPUT_MAX_NUMBER_OF_TIMES = 10;

    /** ユーザー入力促し文言 */
    private static final String USER_INPUT_SENTENCES = "数値を" + USER_INPUT_MAX_NUMBER_OF_TIMES + "回入力してください。";

    /** 入力許容最小値 */
    private static final int USER_INPUT_MIN_VALUE = 1;

    /** 入力許容最大値 */
    private static final int USER_INPUT_MAX_VALUE = 100;

    /** 入力許容範囲 */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "～" + USER_INPUT_MAX_VALUE;

    /** 数値入力促し文言 */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "の" + USER_INPUT_SENTENCES;

    /** 数値以外の入力時エラー文言 */
    private static final String NON_NUMERIC_ERROR = "エラー！！" + USER_INPUT_RANGE + "以外の数値が入力されました。";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(USER_INPUT_SENTENCES);
        System.out.println("endで入力を終了します。");

        // エラーがでる
//        int userInput[] = new int[10];
        List<Integer> list = new ArrayList<>();

        while (scanner.hasNext()) {

            // 値取得
            String input = scanner.next();

            // endか判定
            if (Objects.equals(input, EXIT_CONDITIONS)) {
                // 終了
                break;
            }

            // 数値か判定
            try {
                int line = Integer.valueOf(input).intValue();

                // 入力許容範囲外はエラー
                if (!isWithinRange(line, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                // 10個目の場合、表示
                if (list.size() == (USER_INPUT_MAX_NUMBER_OF_TIMES - 1)) {

                    list.add(line);

                    Integer[] array = list.toArray(new Integer[list.size()]);

                    Arrays.sort(array);
                    for (int number : array) {
                        System.out.println(number);
                    }
                    // 10個入力したので初期化
                    System.out.println(USER_INPUT_MAX_NUMBER_OF_TIMES + "回入力されたので、初期化しました。");
                    System.out.println(USER_INPUT_SENTENCES);
                    list = new ArrayList<>();
                    continue;
                }

                list.add(line);
                System.out.printf(list.size() + "個めが入力されました。%n引き続き、数値を入力してください。%n");

            } catch (NumberFormatException e) {
                // 入力内容が数値以外の場合
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }

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

}
