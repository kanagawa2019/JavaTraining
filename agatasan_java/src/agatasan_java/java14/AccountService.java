package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * ������舵���e�N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/30 �V�K�쐬
 * @version 1.1 2021/05/31 No.123�`131�w�E�Ή�
 * @version 1.2 2021/06/01 No.126,128,130,131�w�E�Ή�
 * @version 1.3 2021/06/02 No.132�`136�w�E�Ή�
 * @version 1.4 2021/06/02 No.132�`136�w�E�Ή�
 *
 */
public class AccountService {

    /** �����ۗL���x�z�i�����j */
    protected static final long MIN_BALANCE = 0L;
    /** �����ۗL���x�z�i����j */
    protected static final long MAX_BALANCE = 9000000000000000000L;
    /** �����������z */
    private static final long MINIMUM_AMOUNT = 1L;
    /** ����������z */
    private static final long MAXIMUM_AMOUNT = 10000000L;

    // --------------------------------------------------
    // protected�֐�
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
     * @return �戵���z
     */
    protected static long getInputMoneyInfo(final AccountHandlingMenu menu, final String msg, final Personal transfer) {
        return getInputMoneyInfo(menu, msg, transfer, null);
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

        } while (!isMatchCondition(menu, inputDeposit, transfer, payee));

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
        long inputDeposit = getInputMoneyInfo(AccountHandlingMenu.DEPOSIT, "����", target);

        // �c���̍��v
        long sum = inputDeposit + target.getBalance();
        displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing.createFile(true, personalList, 0);

        // ��������
        FileProcessing.writeHistory(target.getAccountNumber(), AccountHandlingMenu.DEPOSIT.getId(), inputDeposit, sum);

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
     * @return �K���̏ꍇ��true�A�s�K���̏ꍇ��false��Ԃ�
     */
    private static boolean isMatchCondition(final AccountHandlingMenu menu, final long inputDeposit, final Personal transfer, final Personal payee) {

        // �͈̓`�F�b�N�͋��ʂōs��
        if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
            return false;
        }

        switch (menu) {
            case DEPOSIT:
                if (isMaxBalance(inputDeposit, transfer.getBalance())) {
                    return false;
                }
                break;
            case TRANSFER:
                if (canPay(transfer, inputDeposit)) {
                    return false;
                }

                if (isMaxBalance(inputDeposit, payee.getBalance())) {
                    return false;
                }
                break;
            case WITHDRAW:
                if (canPay(transfer, inputDeposit)) {
                    return false;
                }
                break;
            case BALANCE:
                break;
            case HISTORY:
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * �c�����ŕ����邩�̃`�F�b�N
     * 
     * @param personal     ���[�U���
     * @param inputDeposit �U�����z
     * @return �c�����ŕ�����Ȃ�True�A�����Ȃ��Ȃ�false��Ԃ�
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
     * @return �����s�\�Ȃ�True�A�\�Ȃ�false��Ԃ�
     */
    private static boolean isMaxBalance(final long inputDeposit, final long balance) {

        if (MAX_BALANCE < inputDeposit + balance) {
            System.out.println(String.format("������%,d�~�܂ł�����t���܂���B", MAX_BALANCE - balance));
            return true;
        }
        return false;
    }
}
