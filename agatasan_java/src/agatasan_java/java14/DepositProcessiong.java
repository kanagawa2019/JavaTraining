package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * ��������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 *
 */
public class DepositProcessiong {
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
     */
    public void depositMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �����Ώ�
        Personal target = personalList.get(depositIdx);

        // �������擾
        int inputDeposit = getDeposit();

        // �c���̍��v
        int sum = inputDeposit + target.getBalance();
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
     * �������̎擾
     * 
     * @return �������z
     */
    private int getDeposit() {
        int inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = Util.inputMoney("����");

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000));

        return inputDeposit;
    }

}
