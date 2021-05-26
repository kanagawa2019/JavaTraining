package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * ��������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 *
 */
public class DepositProcessiong {

    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** ���͏�����z */
    private final int limitinputMoney = 10000000;

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * ��������
     * 
     * @param depositIdx   �I�����ꂽ�C���f�b�N�X
     * @param personalList ���[�U��񃊃X�g
     * @return ������̎c��
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     * @throws FileReadException
     * @throws FileWriteException
     */
    public void depositMoney(final int depositIdx, List<Personal> personalList) throws IOException, FileWriteException, FileReadException {

        // �����Ώ�
        Personal target = personalList.get(depositIdx);

        // �����\���̃`�F�b�N
        if (target.getBalance() >= Util.MAX_BALANCE) {
            System.out.println("�����ۗL���x�z�ɒB���Ă��邽�߁A�����ł��܂���B");
            return;
        }

        // �������擾
        long inputDeposit = getDeposit(target);

        // �c���̍��v
        long sum = inputDeposit + target.getBalance();
        new BalanceProcessiong().displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // ��������
        fp.writeHistory(target.getAccountNumber(), Bank.DEPOSIT.getId(), inputDeposit, sum);

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * ���͋��z���̎擾
     * 
     * @param target ���[�U���
     * @return ���͋��z
     */
    private long getDeposit(Personal target) {
        long inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = Util.inputMoney("����");

        } while (Util.isOutOfRange(inputDeposit, 1, limitinputMoney) || isMaxBalance(inputDeposit, target.getBalance()));

        return inputDeposit;
    }

    /**
     * �����\���̊m�F
     * 
     * @param inputDeposit �������z
     * @param balance      �c��
     * @return
     */
    private boolean isMaxBalance(final long inputDeposit, final long balance) {

        if (Util.MAX_BALANCE < inputDeposit + balance) {
//            long aa = (inputDeposit + balance) - Util.MAX_BALANCE;
//            if (aa > limitinputMoney) {
//
//            }

            System.out.println(String.format("������%,d�~�܂ł�����t���܂���B", Util.MAX_BALANCE - balance));

            return true;
        }
        return false;
    }

}
