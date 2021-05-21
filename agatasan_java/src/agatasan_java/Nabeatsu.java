package agatasan_java;

import java.util.stream.IntStream;

/**
 * ナベアツモード九九表示処理クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2021/05/16 新規作成
 * @version 1.1 2021/05/21 No.105指摘対応
 */
public class Nabeatsu extends Multiplication {

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------

    /** 九九の計算結果を除算する数値 */
    public static final int DIVIDE_BY_NUMBER = 3;
    /** 九九の計算結果に含まれる数値 */
    public static final int CONTAINS_NUMBER = 3;
    /** 指定数値で割り切れる、または指定数値を含む場合の表示 */
    public static final String DISPLAY_WHEN_MATCHING_CONDITIONS = "NA";

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------

//    /**
//     * 切替モードONの九九表示の処理
//     * 
//     */
//    @Override
//    public void calcMultiplicationTable() {
//
//        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
//            displaySwitchingMode(i);
//        });
//
//    }
//
//    /**
//     * 切替モードONの九九1行表示の処理
//     * 
//     * @param number 入力文字
//     */
//    @Override
//    public void calcMultiplicationTable(final int number) {
//        displaySwitchingMode(number);
//    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------

    /**
     * 切替モードONの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    @Override
    public void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            int calculationResult = i * inputNumber;

            // 3の倍数は別表示
            if (isMultipleOfTheeOrValueOfThree(calculationResult)) {
                sb.append(String.format(DISPLAY_FORMAT, DISPLAY_WHEN_MATCHING_CONDITIONS));
            } else {
                sb.append(String.format(DISPLAY_FORMAT, calculationResult));
            }

        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    /**
     * 3の倍数か3の値があるかを判定
     * 
     * @param calculationResult 判定対象
     * @return 3の倍数または3の値がある場合、True。3の倍数または3の値ではない場合、false。
     */
    private boolean isMultipleOfTheeOrValueOfThree(final int calculationResult) {
        if (calculationResult % DIVIDE_BY_NUMBER == 0 || hasContainedCharacter(calculationResult, CONTAINS_NUMBER)) {
            return true;
        }
        return false;
    }

    /**
     * 検索対象に判定文字が含まれるかチェック
     * 
     * @param target            検索対象
     * @param judgmentCharacter 判定文字
     * @return 判定文字が含まれる場合、true。含まれない場合、false
     */
    private boolean hasContainedCharacter(final int target, final int judgmentCharacter) {
        String targetStr = String.valueOf(target);
        return targetStr.contains(String.valueOf(judgmentCharacter));
    }
}
