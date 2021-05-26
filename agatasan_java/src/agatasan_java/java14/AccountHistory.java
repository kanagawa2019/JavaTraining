package agatasan_java.java14;

import java.util.Date;

/**
 * ���������N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/01/03 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 */
public class AccountHistory {

    /** �����ԍ� */
    private int accountNumber;
    /** �c�� */
    private long balance;
    /** ���t */
    private Date date;
    /** �敪 */
    private Bank classification;
    /** ������z */
    private long transactionAmount;

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
    public long getBalance() {
        return balance;
    }

    /**
     * �c����ݒ�
     * 
     * @param balance �c�����Z�b�g����
     */
    public void setBalance(long balance) {
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
    public long getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * ������z��ݒ�
     * 
     * @param transactionAmount ������z���Z�b�g����
     */
    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
