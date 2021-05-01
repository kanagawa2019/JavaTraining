package agatasan_java;

/**
 * 顧客口座クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/01/02 新規作成
 */
public class Personal {

    /** 氏名 */
    private String name;
    /** 口座番号 */
    private String accountNumber;
    /** 残高 */
    private int balance;

    /**
     * コンストラクタ
     * 
     */
    Personal() {

    }

    /**
     * コンストラクタ
     * 
     * @param name
     * @param accountNumber
     * @param balance
     */
    Personal(final String name, final String accountNumber, final int balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;

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
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 口座番号を設定
     * 
     * @param accountNumber 口座番号をセットする
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 残高を取得
     * 
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * 残高を設定
     * 
     * @param balance 残高をセットする
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

}
