package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * �o������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
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
        int inputWithdraw = Util.inputMoney("�o��");

        // �c���̍��v
        int sum = target.getBalance() - inputWithdraw;
        new BalanceProcessiong().displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // �o������
        fp.writeHistory(target.getAccountNumber(), Bank.WITHDRAW.getId(), inputWithdraw, sum);

    }
}
