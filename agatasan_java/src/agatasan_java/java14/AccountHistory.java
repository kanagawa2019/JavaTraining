package agatasan_java.java14;

import java.util.Date;

/**
 * ���������N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/01/03 �V�K�쐬
 */
public class AccountHistory {

    /** �����ԍ� */
    private int accountNumber;
    /** �c�� */
    private int balance;
    /** ���t */
    private Date date;
    /** �敪 */
    private Bank classification;
    /** ������z */
    private int transactionAmount;

    /**
     * �R���X�g���N�^
     * 
     */
    AccountHistory() {

    }

    /**
     * �����ԍ����擾
     * 
     * @return accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * �����ԍ���ݒ�
     * 
     * @param accountNumber �����ԍ����Z�b�g����
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * �c�����擾
     * 
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * �c����ݒ�
     * 
     * @param balance �c�����Z�b�g����
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * ���t���擾
     * 
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * ���t��ݒ�
     * 
     * @param date ���t���Z�b�g����
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * �敪���擾
     * 
     * @return classification
     */
    public Bank getClassification() {
        return classification;
    }

    /**
     * �敪��ݒ�
     * 
     * @param classification �敪���Z�b�g����
     */
    public void setClassification(Bank classification) {
        this.classification = classification;
    }

    /**
     * ������z���擾
     * 
     * @return transactionAmount
     */
    public int getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * ������z��ݒ�
     * 
     * @param transactionAmount ������z���Z�b�g����
     */
    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
