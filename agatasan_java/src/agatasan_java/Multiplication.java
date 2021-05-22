package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 九九表示処理クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 * @version 1.1 2021/05/16 No.92,93,94指摘対応
 * @version 1.2 2021/05/21 No.105指摘対応
 */
public class Multiplication implements Display {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    public Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 九九の始まりの値 */
    protected static final int MULTIPLICATION_TABLE_RANGE_START = 1;
    /** 九九の終わりの値 */
    protected static final int MULTIPLICATION_TABLE_RANGE_END = 9;
    /** 表示形式 */
    protected static final String DISPLAY_FORMAT = "%2s ";
    /** 入力許容最小値 */
    private static final int USER_INPUT_MIN_VALUE = 1;
    /** 入力許容最大値 */
    private static final int USER_INPUT_MAX_VALUE = 9;
    /** 入力カーソル */
    private static final String CURSOL = ">";

    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------

    /**
     * 切替モードOFFの九九表示の処理
     * 
     */
    @Override
    public void calcMultiplicationTable() {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingMode(i);
        });

    }

    /**
     * 切替モードOFFの九九1行表示の処理
     * 
     * @param number 入力文字
     */
    @Override
    public void calcMultiplicationTable(final int number) {
        displaySwitchingMode(number);
    }

    /**
     * 九九１行表示の入力判定処理
     * 
     * @return 入力値
     */
    @Override
    public int calcMultiplicationLine() {

        do {
            // 数値か判定
            int inputLine = inputInt("表示する段を入力してください");

            // 入力許容範囲外はエラー
            if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                System.out.println(String.format("エラー！！%d～%d以外の数値が入力されました。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                System.out.println(String.format("%d～%dの数値を入力してください。", USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE));
                continue;
            }

            return inputLine;

        } while (true);
    }

    /**
     * 処理継続確認
     * 
     * @return 処理継続はTrue。処理終了はfalse。
     */
    @Override
    public boolean isContinue() {
        boolean isCheck = false;

        String displayMsg = String.format("処理を続けますか？(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END);

        do {
            String input = inputStr(displayMsg);

            // 入力値の判定
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    return true;
                case PROCESSING_END:
                    return false;
                default:
                    System.out.println(String.format("(%s/%s)以外が入力されました。\n再入力をお願いします。", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return false;
    }

    /**
     * 終了処理
     */
    @Override
    public void scannerClose() {
        System.out.println("終了します。");
        mScanner.close();
    }

    /**
     * モード入力
     */
    @Override
    public DisplayMode inputDisplayMode(boolean displaySwitching) {
        DisplayMode displayMode = null;
        String displayMsg = getDisplayModeString(displaySwitching);
        do {
            displayMode = DisplayMode.convertDisplayMode(inputStr(displayMsg));
            if (displayMode == null) {
                System.out.println("該当する処理モードが見つかりませんでした。");
            }

        } while (displayMode == null);
        return displayMode;
    }

    /**
     * 切替モードOFFの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    public void displaySwitchingMode(final int inputNumber) {

        StringBuilder sb = new StringBuilder();

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            sb.append(String.format(DISPLAY_FORMAT, i * inputNumber));
        });
        System.out.println(sb.toString().replaceAll(" *$", ""));
    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------

    /**
     * 数値入力(int型)
     * 
     * @param inputMsg 入力コンソールに表示する文言
     * @return 入力値
     */
    private int inputInt(final String inputMsg) {
        int num = 0;
        String input = null;
        boolean isCheck = false;

        if (inputMsg != null) {
            System.out.println(inputMsg);
        }

        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }

        } while (!isCheck);
        return num;
    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return 最小値～最大値の範囲内にある場合、true。範囲外の場合、false
     */
    private boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * モード選択表示文言取得
     */
    private String getDisplayModeString(final boolean displaySwitching) {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("処理モードを選択してください").append("\n");
        sb.append(DisplayMode.getSelectDisplayModeString(displaySwitching));
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * 文字入力
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
     */
    private String inputStr(final String inputMsg) {
        String input = null;
        boolean isCheck = false;

        System.out.println(inputMsg);
        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }
        } while (!isCheck);
        return input;
    }
}
