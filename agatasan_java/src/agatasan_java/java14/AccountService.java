package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * ������舵���e�N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/30 �V�K�쐬
 *
 */
public class AccountService {

    /** �����ۗL���x�z�i�����j */
    protected static final long MIN_BALANCE = 0l;
    /** �����ۗL���x�z�i����j */
    protected static final long MAX_BALANCE = 9000000000000000000l;
    /** �����������z */
    private static final int MINIMUM_AMOUNT = 1;
    /** ����������z */
    private static final int MAXIMUM_AMOUNT = 10000000;

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * �c���\��
     * 
     * @param balance �c��
     */
    protected static void displayBalance(final long balance) {
        System.out.println(String.format("�c���́A%,d�~�ł�", balance));
    }

    /**
     * ���͋��z���擾
     * 
     * @param menu     ������舵�����j���[
     * @param msg      �\�����b�Z�[�W
     * @param transfer �U����
     * @param payee    �U����
     * @return �戵���z
     */
    protected static long getInputMoneyInfo(final AccountHandlingMenu menu, final String msg, final Personal transfer, final Personal payee) {
        long inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = Util.inputMoney(msg);

        } while (isMatchCondition(menu, inputDeposit, transfer, payee));

        return inputDeposit;
    }

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
    protected static void depositMoney(final int depositIdx, List<Personal> personalList) throws IOException, FileWriteException, FileReadException {

        // �����Ώ�
        Personal target = personalList.get(depositIdx);

        // �����\���̃`�F�b�N
        if (target.getBalance() >= MAX_BALANCE) {
            System.out.println("�����ۗL���x�z�ɒB���Ă��邽�߁A�����ł��܂���B");
            return;
        }

        // �������擾
        long inputDeposit = getInputMoneyInfo(AccountHandlingMenu.DEPOSIT, "����", target, null);

        // �c���̍��v
        long sum = inputDeposit + target.getBalance();
        displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // ��������
        fp.writeHistory(target.getAccountNumber(), AccountHandlingMenu.DEPOSIT.getId(), inputDeposit, sum);

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * ���͋��z���K�����`�F�b�N
     * 
     * @param menu         ������舵�����j���[
     * @param inputDeposit ���͋��z
     * @param transfer     �U����
     * @param payee        �U����
     * @return �s�K���̏ꍇ��true�A�K���̏ꍇ��false��Ԃ�
     */
    private static boolean isMatchCondition(final AccountHandlingMenu menu, final long inputDeposit, final Personal transfer, final Personal payee) {

        if (menu == AccountHandlingMenu.DEPOSIT) {
            if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (isMaxBalance(inputDeposit, transfer.getBalance())) {
                return true;
            }
        }

        if (menu == AccountHandlingMenu.TRANSFER) {
            if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (canPay(transfer, inputDeposit)) {
                return true;
            } else if (isMaxBalance(inputDeposit, payee.getBalance())) {
                return true;
            }

        }

        if (menu == AccountHandlingMenu.WITHDRAW) {
            if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (canPay(transfer, inputDeposit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * �c�����ŕ����邩�̃`�F�b�N
     * 
     * @param personal     ���[�U���
     * @param inputDeposit �U�����z
     * @return true:�c�����ŕ�����
     */
    private static boolean canPay(final Personal personal, final long inputDeposit) {

        // �����̌������略���Ȃ��ꍇ
        if (personal.getBalance() - inputDeposit < 0) {
            System.out.println(String.format("�������̎c��%,d�~���ł���舵���ł��܂��B", personal.getBalance()));
            return true;
        }
        return false;
    }

    /**
     * �����\���̊m�F
     * 
     * @param inputDeposit �������z
     * @param balance      �c��
     * @return
     */
    private static boolean isMaxBalance(final long inputDeposit, final long balance) {

        if (MAX_BALANCE < inputDeposit + balance) {
            System.out.println(String.format("������%,d�~�܂ł�����t���܂���B", MAX_BALANCE - balance));
            return true;
        }
        return false;
    }
}
