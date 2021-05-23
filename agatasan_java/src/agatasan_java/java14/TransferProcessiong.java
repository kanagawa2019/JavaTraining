package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * �U������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 *
 */
public class TransferProcessiong {
    /**
     * �U������
     * 
     * @param transfer     �U����
     * @param personalList ���[�U�[��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            String toModifyPersonMsg = Util.displayToCorrectPerson(personalList, "�ǂ̃��[�U�ɐU�����܂����H");

            // �U����̐l���̔ԍ����擾
            int payeeOfNumber = Util.getCorrectPerson(toModifyPersonMsg, personalList);

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
            int inputDeposit = getTransferInfo(transfer, payee);

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

    /**
     * �U�����擾
     * 
     * @param transfer �U�������[�U�[���
     * @param payee    �U���惆�[�U�[���
     * @return �U�����z
     */
    private int getTransferInfo(final Personal transfer, final Personal payee) {
        int inputDeposit = 0;
        DepositProcessiong deposit = new DepositProcessiong();
        do {
            // ���͒l���擾
            inputDeposit = deposit.inputDeposit();

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000) || canPay(transfer, inputDeposit));

        return inputDeposit;
    }

    /**
     * �c�����ŕ����邩�̃`�F�b�N
     * 
     * @param transfer     �U����̃��[�U�[���
     * @param inputDeposit �U�����z
     * @return true:�c�����ŕ�����
     */
    private boolean canPay(final Personal transfer, final int inputDeposit) {

        // �����̌������略���Ȃ��ꍇ
        if (transfer.getBalance() - inputDeposit < 0) {
            System.out.println(String.format("�������̎c��%,d�~���ŐU�荞��ł��������B", transfer.getBalance()));
            return true;
        }
        return false;
    }
}
