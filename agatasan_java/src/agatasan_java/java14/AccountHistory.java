package agatasan_java.java14;

import java.util.Date;

/**
 * 口座履歴クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/01/03 新規作成
 */
public class AccountHistory {

    /** 口座番号 */
    private int accountNumber;
    /** 残高 */
    private int balance;
    /** 日付 */
    private Date date;
    /** 区分 */
    private Bank classification;
    /** 取引金額 */
    private int transactionAmount;

    /**
     * コンストラクタ
     * 
     */
    AccountHistory() {

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

    /**
     * 日付を取得
     * 
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 日付を設定
     * 
     * @param date 日付をセットする
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 区分を取得
     * 
     * @return classification
     */
    public Bank getClassification() {
        return classification;
    }

    /**
     * 区分を設定
     * 
     * @param classification 区分をセットする
     */
    public void setClassification(Bank classification) {
        this.classification = classification;
    }

    /**
     * 取引金額を取得
     * 
     * @return transactionAmount
     */
    public int getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * 取引金額を設定
     * 
     * @param transactionAmount 取引金額をセットする
     */
    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
