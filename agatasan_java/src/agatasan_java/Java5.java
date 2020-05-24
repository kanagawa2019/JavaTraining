package agatasan_java;

/**
 * Java5クラス Java課題５(頭の体操）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/05 新規作成
 */
public class Java5 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s ";

    /**
     * 九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {
        diplayMultiplicationTable(MULTIPLICATION_TABLE_RANGE_START);
    }

    /**
     * 九九を表示
     * 
     * @param startNumber 九九の縦の段の数値
     */
    public static void diplayMultiplicationTable(int startNumber) {
        if (startNumber <= MULTIPLICATION_TABLE_RANGE_END) {
            displayHorizontal(startNumber, MULTIPLICATION_TABLE_RANGE_START);
            System.out.printf("%n");
            diplayMultiplicationTable(startNumber + 1);
        }
    }

    /**
     * 九九の横の段を表示
     * 
     * @param verticalNumber   縦の段の数値
     * @param horizontalNumber 横の段の数値
     */
    public static void displayHorizontal(int verticalNumber, int horizontalNumber) {
        if (horizontalNumber <= MULTIPLICATION_TABLE_RANGE_END) {
            System.out.printf(DISPLAY_FORMAT, verticalNumber * horizontalNumber);
            displayHorizontal(verticalNumber, horizontalNumber + 1);
        }
    }
}
