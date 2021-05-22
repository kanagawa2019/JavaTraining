package agatasan_java;

/**
 * Java１３クラス Java課題１３(オブジェクト指向：継承・多重化）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 * @version 1.1 2021/05/21 No.105,106,107指摘対応
 */
public class Java13 {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** 切替モード */
    private static boolean displaySwitching = false;

    /**
     * 入力された処理モードの処理を表示します。
     * 
     */
    public static void main(String[] args) {

        Display display = new Multiplication();

        do {
            // --------------------------------------------------
            // 入力
            // --------------------------------------------------
            // 処理モード入力
            final DisplayMode displayMode = display.inputDisplayMode(displaySwitching);

            display = displaySwitching ? new Nabeatsu() : new Multiplication();

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
                    display.scannerClose();
                    return;
                default:
                    break;
            }

        } while (display.isContinue());

    }

}
