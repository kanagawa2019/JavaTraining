package agatasan_java;

import java.util.Scanner;

/**
 * Java１３クラス Java課題１３(オブジェクト指向：継承・多重化）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 * @version 1.1 2021/05/16 No.106,107指摘対応
 */
public class Java13 {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** 切替モード */
    private static boolean displaySwitching = false;
    /** スキャナー（コンソール入力） */
    private static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";
    /** 入力カーソル */
    private static final String CURSOL = ">";

    /**
     * 入力された処理モードの処理を表示します。
     * 
     */
    public static void main(String[] args) {

        do {
            // --------------------------------------------------
            // 入力
            // --------------------------------------------------
            // 処理モード入力
            final DisplayMode displayMode = inputDisplayMode();

            Display display = displaySwitching ? new Nabeatsu() : new Multiplication();

            // --------------------------------------------------
            // 処理・出力
            // --------------------------------------------------

            switch (displayMode) {
                case MULTIPLICATION_TABLE:
                    display.calcMultiplicationTable();
                    break;
                case MULTIPLICATION_LINE:
                    display.calcMultiplicationTable(display.calcMultiplicationLine());
                    break;
                case SWITCHING:
                    displaySwitching = !displaySwitching;
                    System.out.println("モードを切り替えました。");
                    break;
                case END:
                    // 終了処理
                    System.out.println("終了します。");
                    mScanner.close();
                    return;
                default:
                    break;
            }

        } while (isContinue());

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------

    /**
     * 処理継続確認
     * 
     * @return 処理継続はTrue。処理終了はfalse。
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
     * 文字入力
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
     */
    private static String inputStr(final String inputMsg) {
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

    /**
     * モード選択表示文言取得
     */
    private static String getDisplayModeString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("処理モードを選択してください").append("\n");
        sb.append(DisplayMode.getSelectDisplayModeString(displaySwitching));
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * モード入力
     */
    private static DisplayMode inputDisplayMode() {
        DisplayMode displayMode = null;
        String displayMsg = getDisplayModeString();
        do {
            displayMode = DisplayMode.convertDisplayMode(inputStr(displayMsg));
            if (displayMode == null) {
                System.out.println("該当する処理モードが見つかりませんでした。");
            }

        } while (displayMode == null);
        return displayMode;
    }
}
