package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * �U������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 * @version 1.2 2021/05/30 No.110�`122�w�E�Ή�
 * 
 */
public class TransferProcessiong extends AccountService {

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
    public static void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        Personal payee;

        do {
            // �U����̐l���̔ԍ����擾
            int payeeOfNumber = Util.getTargetNo(personalList, "�ǂ̃��[�U�ɐU�����܂����H");

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (payeeOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
                return;
            }

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            payee = personalList.get(payeeOfNumber - 1);

            // �����ɐU�����Ȃ��ꍇ
            if (!transfer.getName().equals(payee.getName())) {
                break;
            }
            System.out.println("�������ɂ͐U���߂܂���B");
        } while (true);

        remitMoney(transfer, payee);

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------

    /**
     * �����ɐU���ޏ���
     * 
     * @param transfer �U����
     * @param payee    �U����
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void remitMoney(Personal transfer, Personal payee) throws FileWriteException, FileReadException, IOException {

        // �U����̌��x�z����̏ꍇ�́A�U�����~
        if (payee.getBalance() == MAX_BALANCE) {
            System.out.println("�U����̗a������z���B���Ă��邽�߁A���U���𒆎~�v���܂��B");
            return;
        }

        // �U�����擾
        long inputDeposit = getInputMoneyInfo(AccountHandlingMenu.TRANSFER, "����", transfer, payee);

        // �U�����̎c����ݒ�
        transfer.setBalance(transfer.getBalance() - inputDeposit);
        // �U����̎c����ݒ�
        payee.setBalance(payee.getBalance() + inputDeposit);

        // �U���������̍X�V
        FileProcessing fp = new FileProcessing();
        fp.writeHistory(transfer.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
        // �U���旚���̍X�V
        fp.writeHistory(payee.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), inputDeposit, payee.getBalance());

        System.out.println(String.format("%S����ɐU���������܂����B", payee.getName()));
    }
}
