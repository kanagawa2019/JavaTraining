package agatasan_java;

/**
 * �ڋq�����N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/01/02 �V�K�쐬
 */
public class Personal {

    /** ���� */
    private String name;
    /** �����ԍ� */
    private int accountNumber;
    /** �c�� */
    private int balance;

    /**
     * �R���X�g���N�^
     * 
     */
    Personal() {

    }

    /**
     * �R���X�g���N�^
     * 
     * @param name          ����
     * @param accountNumber �����ԍ�
     * @param balance       �c��
     */
    Personal(final String name, final int accountNumber, final int balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;

    }

    /**
     * �������擾
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * ������ݒ�
     * 
     * @param name �������Z�b�g����
     */
    public void setName(String name) {
        this.name = name;
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

}
