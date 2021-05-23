package agatasan_java.java14;

/**
 * 口座操作
 * 
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 *
 */
public enum Bank {
    DEPOSIT(1, "入金"), TRANSFER(2, "振込"), BALANCE(3, "残高表示"), HISTORY(4, "お取引履歴表示");

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
    private Bank(final int id, final String name) {
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
        for (final Bank t : Bank.values()) {
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
    public static Bank convertBank(final String inputBank) {
        if (inputBank == null) {
            return null;
        }

        int bank = 0;
        try {
            bank = Integer.parseInt(inputBank);
        } catch (NumberFormatException e) {
            return null;
        }

        for (final Bank t : Bank.values()) {
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
