package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java課題３(分岐制御） 九九表示クラス(3の倍数 or 3がつく場合はNA表示)
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/02 新規作成
 * @version 1.1 2020/05/09 3がつく場合も別表示するように修正
 */
public class Java3 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s  ";

    /** 倍数 */
    private static final int MULTIPLE = 3;

    /** 数値 */
    private static final int NUMERICAL_VALUE = 3;

    /** 倍数時の表示 */
    private static final String MULTIPLE_DISPLAY = "NA";

    /**
     * 九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {

                // 指定の倍数 or 指定の数字がつく場合は別表示
                if ((i * j) % MULTIPLE == 0 || checkNumericalValue(i * j)) {
                    System.out.printf(DISPLAY_FORMAT, MULTIPLE_DISPLAY, " ");
                } else {
                    System.out.printf(DISPLAY_FORMAT, i * j, " ");
                }
            });
            System.out.printf("%n");
        });

    }

    /**
     * 指定の数値が含まれるかチェック
     * 
     * @param number
     * @return true:数値が含まれる
     */
    private static boolean checkNumericalValue(int number) {
        String numberStr = String.valueOf(number);
        return numberStr.contains(String.valueOf(NUMERICAL_VALUE));
    }

}
