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
    private String accountNumber;
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
     * �����o�ϐ�������擾
     * 
     * @return �����o�ϐ�������擾
     */
    public static String getSelectPersonalString() {
        final StringBuffer sb = new StringBuffer();
        int cnt = 0;
        sb.append(++cnt).append(".����").append("\n");
        sb.append(++cnt).append(".�����ԍ�").append("\n");
        sb.append(++cnt).append(".�c��").append("\n");
        return sb.toString();
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
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * �����ԍ���ݒ�
     * 
     * @param accountNumber �����ԍ����Z�b�g����
     */
    public void setAccountNumber(String accountNumber) {
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
