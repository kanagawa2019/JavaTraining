package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java課題３(分岐制御） 九九表示クラス(3の倍数 or 3がつく場合はNA表示)
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/02 新規作成
 * @version 1.1 2020/05/09 3がつく場合も別表示するように修正
 * @version 1.2 2020/05/14 指定文字がある場合の判定チェックを修正
 * @version 1.3 2020/05/16 定数の名称を修正
 */
public class Java3 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s ";

    /** 九九の計算結果を除算する数値 */
    private static final int DIVIDE_BY_NUMBER = 3;

    /** 九九の計算結果に含まれる数値 */
    private static final int CONTAINS_NUMBER = 3;

    /** 指定数値で割り切れる、または指定数値を含む場合の表示 */
    private static final String DISPLAY_WHEN_MATCHING_CONDITIONS = "NA";

    /**
     * 九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {

                int calculationResult = i * j;

                // 指定した数値で割り切れる or 指定の数値を含む場合は別表示
                if ((calculationResult) % DIVIDE_BY_NUMBER == 0
                        || hasContainedCharacter((calculationResult), CONTAINS_NUMBER)) {
                    System.out.printf(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS);
                } else {
                    System.out.printf(DISPLAY_FORMAT, calculationResult);
                }
            });
            System.out.printf("%n");
        });

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

}
