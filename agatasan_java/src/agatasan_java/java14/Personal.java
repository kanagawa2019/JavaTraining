package agatasan_java.java14;

/**
 * �ڋq�����N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/01/02 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 */
public class Personal {

    /** ���� */
    private String name;
    /** �����ԍ� */
    private int accountNumber;
    /** �c�� */
    private long balance;

    /**
     * �R���X�g���N�^
     * 
     * @param name          ����
     * @param accountNumber �����ԍ�
     * @param balance       �c��
     */
    Personal(final String name, final int accountNumber, final long balance) {
        this.name          = name;
        this.accountNumber = accountNumber;
        this.balance       = balance;

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

}
