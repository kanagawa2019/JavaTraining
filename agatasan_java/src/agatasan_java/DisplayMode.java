package agatasan_java;

/**
 * 九九処理モードの列挙型クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/30 新規作成
 * @version 1.1 2021/05/16 表示形式揃え横展開
 * @version 1.2 2021/05/21 No.105指摘対応
 * @version 1.3 2021/05/29 No.108指摘対応
 */
public enum DisplayMode {

                         MULTIPLICATION_TABLE(1, "九九表示"), MULTIPLICATION_LINE(2, "指定の段のみ表示"),
                         SWITCHING(3, "モード切替(%S)"), END(9, "終了");

    /** id */
    private final int id;
    /** 名称 */
    private final String name;
    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2d";

    /**
     * コンストラクタ
     * 
     * @param id
     */
    private DisplayMode(final int id, final String name) {
        this.id   = id;
        this.name = name;
    }

    /**
     * モード表示文字列取得
     * 
     * @return モード表示文字列
     */
    public static String getSelectDisplayModeString(boolean displaySwitching) {
        final StringBuffer sb = new StringBuffer();
        for (final DisplayMode t : DisplayMode.values()) {
            if (displaySwitching) {
                sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(String.format(
                    t.name, "世界のナベアツ⇒通常")).append("\n");
            } else {
                sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(String.format(t.name,
                    "通常⇒世界のナベアツ")).append("\n");
            }

        }
        return sb.toString();
    }

    /**
     * モード取得
     * 
     * @param inputDiaplayMode 入力値
     * @return モードが存在しない場合はnull値
     */
    public static DisplayMode convertDisplayMode(final String inputDiaplayMode) {
        if (inputDiaplayMode == null) {
            return null;
        }

        int displayMode = 0;
        try {
            displayMode = Integer.parseInt(inputDiaplayMode);
        }
        catch (NumberFormatException e) {
            return null;
        }

        for (final DisplayMode t : DisplayMode.values()) {
            if (t.id == displayMode) {
                return t;
            }
        }
        return null;
    }

}
