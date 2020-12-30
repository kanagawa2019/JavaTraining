package agatasan_java;

/**
 * Displayクラス コンソール表示抽象クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 */
public abstract class Display {

    // コンソール表示
    public static void displaySentence(boolean displaySwitching) {

        System.out.println("***********************************");
        System.out.println("処理モードを選択してください");
        System.out.println(String.format("%s.九九表示", Mode.MULTIPLICATION_TABLE.getValue()));
        System.out.println(String.format("%s.指定の段のみ表示", Mode.MULTIPLICATION_LINE.getValue()));

        if (displaySwitching) {
            System.out.println(String.format("%s.モード切替(世界のナベアツ⇒通常)", Mode.SWITCHING.getValue()));
        } else {
            System.out.println(String.format("%s.モード切替(通常⇒世界のナベアツ)", Mode.SWITCHING.getValue()));
        }

        System.out.println(String.format("%s.終了", Mode.END.getValue()));
        System.out.println("***********************************");
    }

}
