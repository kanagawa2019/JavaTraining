package agatasan_java;

/**
 * 九九処理モード表示のインターフェース
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/30 新規作成
 * @version 1.1 2021/05/16 No.93指摘対応
 * @version 1.2 2021/05/21 No.105指摘対応
 */
public interface Display {

    // 九九全行表示
    void calcMultiplicationTable();

    // 九九1行表示
    void calcMultiplicationTable(final int number);

    // 九九１行表示の入力判定処理
    int calcMultiplicationLine();

    // モード入力受付処理
    DisplayMode inputDisplayMode(final boolean displaySwitching);

    // 終了処理
    void scannerClose();

    // 処理継続確認
    boolean isContinue();
}
