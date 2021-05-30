package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * �o������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 * @version 1.2 2021/05/30 No.110�`122�w�E�Ή�
 *
 */
public class WithdrawProcessiong extends AccountService {
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
    public static void withdrawMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {
        // �o���Ώ�
        Personal target = personalList.get(depositIdx);

        // ���x�z����̏ꍇ�́A���~
        if (target.getBalance() == MIN_BALANCE) {
            System.out.println("�a���z��0�~�̂��߁A�o���𒆎~�v���܂��B");
            return;
        }

        // �o�����擾
        long inputWithdraw = getInputMoneyInfo(AccountHandlingMenu.WITHDRAW, "�o��", target, null);

        // �c���̍��v
        long sum = target.getBalance() - inputWithdraw;
        displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // �o������
        fp.writeHistory(target.getAccountNumber(), AccountHandlingMenu.WITHDRAW.getId(), inputWithdraw, sum);

    }

}
