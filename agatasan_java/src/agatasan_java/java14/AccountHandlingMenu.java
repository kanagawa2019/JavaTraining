package agatasan_java.java14;

/**
 * 口座取り扱いメニュー
 * 
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 * @version 1.1 2021/05/23 No.95～102指摘対応
 * @version 1.2 2021/05/30 No.110～122指摘対応
 *
 */
public enum AccountHandlingMenu {

    DEPOSIT(1, "入金"), TRANSFER(2, "振込"), WITHDRAW(3, "出金"), BALANCE(4, "残高表示"), HISTORY(5, "お取引履歴表示");

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
    private AccountHandlingMenu(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * モード表示文字列取得
     * 
     * @return モード表示文字列
     */
    public static String getSelectBankString() {
        final StringBuffer sb = new StringBuffer();
        for (final AccountHandlingMenu t : AccountHandlingMenu.values()) {
            sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(t.name).append("\n");
        }
        return sb.toString();
    }

    /**
     * モード取得
     * 
     * @param inputBank入力値
     * @return モードが存在しない場合はnull値
     */
    public static AccountHandlingMenu convertBank(final String inputBank) {
        if (inputBank == null) {
            return null;
        }

        int bank = 0;
        try {
            bank = Integer.parseInt(inputBank);
        } catch (NumberFormatException e) {
            return null;
        }

        for (final AccountHandlingMenu t : AccountHandlingMenu.values()) {
            if (t.id == bank) {
                return t;
            }
        }
        return null;
    }

    /**
     * idを取得
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * nameを取得
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

}
