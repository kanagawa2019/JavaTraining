package agatasan_java.java14;

/**
 * 顧客口座クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/01/02 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 */
public class Personal {

    /** 氏名 */
    private String name;
    /** 口座番号 */
    private int accountNumber;
    /** 残高 */
    private long balance;

    /**
     * コンストラクタ
     * 
     * @param name               氏名
     * @param accountNumber 口座番号
     * @param balance            残高
     */
    Personal(final String name, final int accountNumber, final long balance) {
        this.name                 = name;
        this.accountNumber   = accountNumber;
        this.balance               = balance;

    }

    /**
     * 氏名を取得
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 氏名を設定
     * 
     * @param name 氏名をセットする
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 口座番号を取得
     * 
     * @return accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * 口座番号を設定
     * 
     * @param accountNumber 口座番号をセットする
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 残高を取得
     * 
     * @return balance
     */
    public long getBalance() {
        return balance;
    }

    /**
     * 残高を設定
     * 
     * @param balance 残高をセットする
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }

}
