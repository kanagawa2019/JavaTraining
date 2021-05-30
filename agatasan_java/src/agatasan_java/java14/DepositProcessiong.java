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
    public static void depositMoney(final int depositIdx, List<Personal> personalList) throws IOException, FileWriteException, FileReadException {

        // �����Ώ�
        Personal target = personalList.get(depositIdx);

        // �����\���̃`�F�b�N
        if (target.getBalance() >= Util.MAX_BALANCE) {
            System.out.println("�����ۗL���x�z�ɒB���Ă��邽�߁A�����ł��܂���B");
            return;
        }

        // �������擾
        long inputDeposit = Util.getInputMoneyInfo(AccountHandlingMenu.DEPOSIT, "����", target, null);

        // �c���̍��v
        long sum = inputDeposit + target.getBalance();
        BalanceProcessiong.displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // ��������
        fp.writeHistory(target.getAccountNumber(), AccountHandlingMenu.DEPOSIT.getId(), inputDeposit, sum);

    }

}
