package agatasan_java.java14;

import java.util.Date;

/**
 * ���������N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/01/03 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 * @version 1.2 2021/05/31 No.123�`131�w�E�Ή�
 */
public class AccountHistory {

    /** �����ԍ� */
    private int accountNumber;
    /** �c�� */
    private long balance;
    /** ���t */
    private Date date;
    /** �敪 */
    private AccountHandlingMenu classification;
    /** ������z */
    private long transactionAmount;

    /**
     * �R���X�g���N�^
     * 
     * @param accountNumber     �����ԍ�
     * @param balance           �c��
     * @param date              ���t
     * @param classification    �敪
     * @param transactionAmount ������z
     */
    AccountHistory(int accountNumber, final long balance, final Date date, final AccountHandlingMenu classification, final long transactionAmount) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.date = date;
        this.classification = classification;
        this.transactionAmount = transactionAmount;
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
    public AccountHandlingMenu getClassification() {
        return classification;
    }

    /**
     * �敪��ݒ�
     * 
     * @param classification �敪���Z�b�g����
     */
    public void setClassification(AccountHandlingMenu classification) {
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
