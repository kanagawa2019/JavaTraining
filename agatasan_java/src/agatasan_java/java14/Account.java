package agatasan_java.java14;

/**
 * 口座操作
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public enum Account {

    NEW(1, "口座を新規作成"), CHANGE(2, "すでにある口座を取り扱う");

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
    private Account(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 口座処理表示文字列取得
     * 
     * @return 口座処理表示文字列
     */
    public static String getSelectAccountString() {
        final StringBuffer sb = new StringBuffer();
        for (final Account t : Account.values()) {
            sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(t.name).append("\n");
        }
        return sb.toString();
    }

    /**
     * 口座処理取得
     * 
     * @param inputAccount入力値
     * @return 口座処理が存在しない場合はnull値
     */
    public static Account convertAccount(final String inputAccount) {
        if (inputAccount == null) {
            return null;
        }

        int account = 0;
        try {
            account = Integer.parseInt(inputAccount);
        } catch (NumberFormatException e) {
            return null;
        }

        for (final Account t : Account.values()) {
            if (t.id == account) {
                return t;
            }
        }
        return null;
    }

}
