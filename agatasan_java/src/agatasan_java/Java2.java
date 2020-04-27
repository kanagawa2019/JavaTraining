package agatasan_java;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java2クラス Java課題２(標準入力・関数化・入力チェック）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/08 新規作成
 * @version 1.1 2020/04/19 2020/03/24のRV指摘取り込み 無限ループwhile(true)を変更
 * @version 1.2 2020/04/26 2020/04/20のRV指摘取り込み 入力上限チェックの追加・whileインデント修正
 *          ・入力完了（九九表示）後のユーザー入力促し文言の追加
 * @version 1.3 2020/04/27 2020/04/27のRV指摘取り込み 文言定数化・メソッド命名規則化・不等号の向きを揃える
 */
public class Java2 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s ";

    /** 終了条件 */
    private static final String EXIT_CONDITIONS = "end";

    /** ユーザー入力促し文言 */
    private static final String USER_INPUT_SENTENCES = "数値を入力してください。";

    /** 数値入力促し文言 */
    private static final String NUMBER_INPUT_SENTENCES = "1～9の数値を入力してください。";

    /** 数値以外の入力時エラー文言 */
    private static final String NON_NUMERIC_ERROR = "エラー！！1～9以外の数値が入力されました。";

    /**
     * 入力された数値で九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(USER_INPUT_SENTENCES);
        System.out.println("endで入力を終了します。");

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

                // 入力範囲が1~9以外はエラー
                if (!isWithinRange(line, 1, 9)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                // 入力内容が数値の場合
                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                    System.out.printf(DISPLAY_FORMAT, i * line, " ");
                });
                System.out.printf("%n引き続き、九九表示する場合は、数値を入力してください。%n");
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
    public static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

}
