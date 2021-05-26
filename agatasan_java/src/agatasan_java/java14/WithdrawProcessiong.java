package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * �o������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 *
 */
public class WithdrawProcessiong {

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------

    /**
     * �o������
     * 
     * @param depositIdx   �o���ΏۃC���f�b�N�X
     * @param personalList ���[�U��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void withdrawMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �o���Ώ�
        Personal target = personalList.get(depositIdx);

        // �o�����擾
        long inputWithdraw = getWithdraw(target);

        // �c���̍��v
        long sum = target.getBalance() - inputWithdraw;
        new BalanceProcessiong().displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // �o������
        fp.writeHistory(target.getAccountNumber(), Bank.WITHDRAW.getId(), inputWithdraw, sum);

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
    private long getWithdraw(Personal personal) {
        long inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = Util.inputMoney("�o��");

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000) || Util.canPay(personal, inputDeposit));

        return inputDeposit;
    }
}
