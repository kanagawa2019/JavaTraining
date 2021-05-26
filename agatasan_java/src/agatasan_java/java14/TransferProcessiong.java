package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * �U������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 *
 */
public class TransferProcessiong {

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * �U������
     * 
     * @param transfer     �U����
     * @param personalList ���[�U��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            // �U����̐l���̔ԍ����擾
            int payeeOfNumber = Util.getTargetNo(personalList, "�ǂ̃��[�U�ɐU�����܂����H");

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (payeeOfNumber == 0) {
                return;
            }

            int idx = payeeOfNumber - 1;

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            Personal payee = personalList.get(idx);

            // �����ɐU���͕s��
            if (transfer.getName().equals(payee.getName())) {
                System.out.println("�������ɂ͐U���߂܂���B");
                continue;
            }
            // �U�����擾
            long inputDeposit = getTransferInfo(transfer, payee);

            // �U�����̎c����ݒ�
            transfer.setBalance(transfer.getBalance() - inputDeposit);
            // �U����̎c����ݒ�
            payee.setBalance(payee.getBalance() + inputDeposit);

            // �U���������̍X�V
            FileProcessing fp = new FileProcessing();
            fp.writeHistory(transfer.getAccountNumber(), Bank.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
            // �U���旚���̍X�V
            fp.writeHistory(payee.getAccountNumber(), Bank.TRANSFER.getId(), inputDeposit, payee.getBalance());

            System.out.println(String.format("%S����ɐU���������܂����B", payee.getName()));

            break;
        } while (true);

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * �U�����擾
     * 
     * @param transfer �U�������[�U���
     * @param payee    �U���惆�[�U���
     * @return �U�����z
     */
    private long getTransferInfo(final Personal transfer, final Personal payee) {
        long inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = Util.inputMoney("����");

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000) || Util.canPay(transfer, inputDeposit));

        return inputDeposit;
    }

}
