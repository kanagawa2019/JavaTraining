package agatasan_java;

/**
 * 処理モードの列挙型クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/10 新規作成
 */
public enum Mode {

    /**
     * 九九表示
     */
    MULTIPLICATION_TABLE(1),

    /**
     * 指定の段のみ表示
     */
    MULTIPLICATION_LINE(2),

    /**
     * モード切替
     */
    SWITCHING(3),

    /**
     * 終了
     */
    END(9);

    private final int num;

    /**
     * 列挙型のインスタンス生成を抑止します。
     * 
     * @param num
     */
    private Mode(int num) {
        this.num = num;
    }

    /**
     * 番号を取得する。
     * 
     * @return 番号
     */
    public int getValue() {
        return this.num;
    }

    public static Mode getTypeByValue(int num) {
        for (Mode v : values()) {
            if (v.getValue() == (num)) {
                return v;
            }
        }
        throw new IllegalArgumentException("undefined : " + num);
//        return 99;
    }
}
