package agatasan_java;

import java.util.Scanner;

/**
 * Java１３クラス Java課題１３(オブジェクト指向：継承・多重化）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 */
public class Java13 extends Multiplication implements Display {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    private static Scanner mScanner = new Scanner(System.in);
    /** 切替モード */
    private static boolean displaySwitching = false;

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";

    /**
     * 入力された数値で九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        do {
            // --------------------------------------------------
            // 入力
            // --------------------------------------------------
            // 処理モード入力
            final DisplayMode displayMode = inputDisplayMode();
            // --------------------------------------------------
            // 処理・出力
            // --------------------------------------------------

            switch (displayMode) {
                case MULTIPLICATION_TABLE:
                    displayMultiplicationTable(displaySwitching);
                    break;
                case MULTIPLICATION_LINE:
                    displayMultiplicationLine(mScanner, displaySwitching);
                    break;
                case SWITCHING:
                    displaySwitching = displaySwitching ? false : true;
                    System.out.println("モードを切り替えました。");
                    break;
                case END:
                    System.out.println("終了します。");
                    // 終了処理
                    mScanner.close();
                    return;
                default:
                    break;
            }

        } while (isContinue());

    }

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------
    /**
     * モード選択表示文言取得
     */
    @Override
    public String getDisplayModeString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("処理モードを選択してください").append("\n");
        sb.append(DisplayMode.getSelectDisplayModeString(displaySwitching));
        sb.append("***********************************");
        return sb.toString();
    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 九九１行表示の処理
     * 
     * @param displaySwitching 切替モードの状態
     * @param inputNumber      入力文字
     */
    private static void calcSingleLine(boolean displaySwitching, int inputNumber) {

        // 切替モードオンの場合
        if (displaySwitching) {
            displaySwitchingMode(displaySwitching, inputNumber);
            return;
        }

        // 切替モードオフの場合
        displaySwitchingMode(inputNumber);

    }

    /**
     * 九九表示の処理
     * 
     * @param displaySwitching 切替モードの状態
     */
    private static void displayMultiplicationTable(boolean displaySwitching) {

        // 切替モードオンの場合
        if (displaySwitching) {
            calcMultiplicationTable(displaySwitching);
            return;
        }

        // 切替モードオフの場合
        calcMultiplicationTable();

    }

    /**
     * 九九１行表示の入力判定処理
     * 
     * @param scanner          入力情報
     * @param displaySwitching 切替モードの状態
     */
    private static void displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {

        int line = calcMultiplicationLine();

        calcSingleLine(displaySwitching, line);

    }

    /**
     * 処理継続確認
     * 
     * @return isProcessingContinue 処理継続はTrue。処理終了はfalse。
     */
    private static boolean isContinue() {
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
     * モード入力
     */
    private static DisplayMode inputDisplayMode() {
        DisplayMode displayMode = null;
        Display display = new Java13();
        String displayMsg = display.getDisplayModeString();
        do {
            displayMode = DisplayMode.convertDisplayMode(inputStr(displayMsg));
            if (displayMode == null) {
                System.out.println("該当する処理モードが見つかりませんでした。");
            }

        } while (displayMode == null);
        return displayMode;
    }

}
