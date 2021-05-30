package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * ��������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 * @version 1.2 2021/05/29 No.118�w�E�Ή�
 *
 */
public class AccountProcessing {

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------

    /**
     * �����̏�������
     */
    public DepositBusiness inputAccount() {
        DepositBusiness account = null;
        String displayMsg = getDisplayString(DepositBusiness.getSelectAccountString());
        do {
            account = DepositBusiness.convertAccount(Util.inputStr(displayMsg));
            if (account == null) {
                System.out.println("�Y�����鏈����������܂���ł����B");
            }

        } while (account == null);
        return account;
    }

    /**
     * ��������̏���
     * 
     * @param personalList ���[�U��񃊃X�g
     */
    public void changeAccountInfo(List<Personal> personalList) {

        do {

            // �C������l���̔ԍ����擾
            int personOfNumber = Util.getTargetNo(personalList, "�ǂ̃��[�U�̏��������܂����H");

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (personOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
                return;
            }

            int idx = personOfNumber - 1;

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            Personal personal = personalList.get(idx);

            DepositProcessiong deposit = new DepositProcessiong();
            TransferProcessiong transfer = new TransferProcessiong();
            BalanceProcessiong balance = new BalanceProcessiong();
            WithdrawProcessiong withdraw = new WithdrawProcessiong();

            try {

                do {
                    // �C�����郆�[�U���ԍ����擾
                    int propertyOfNumber = getModifyUserInfo(displayToCorrectProperty(personal.getName()));

                    // 0 �̏ꍇ�A�C���l����I�����鏈���܂Ŗ߂�
                    if (propertyOfNumber == Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST) {
                        return;
                    }

                    // --------------------------------------------------
                    // �����E�o��
                    // --------------------------------------------------

                    switch (AccountHandlingMenu.convertBank(String.valueOf(propertyOfNumber))) {
                        case DEPOSIT:
                            // ��������
                            deposit.depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // �U������
                            transfer.transferMoney(personal, personalList);
                            break;
                        case WITHDRAW:
                            // �o������
                            withdraw.withdrawMoney(idx, personalList);
                            break;
                        case BALANCE:
                            // �c���\������
                            balance.displayBalance(personal.getBalance());
                            break;
                        case HISTORY:
                            // ���������\��
                            balance.displayHistory(personal.getAccountNumber());
                            break;
                        default:
                            System.out.println(Util.UNEXPECTED_ERR);
                            break;
                    }

                } while (true);

            } catch (FileReadException | FileWriteException | IOException e) {
                System.out.println("�����𒆒f���܂����B�V�X�e���Ǘ��҂֖₢���킹���Ă��������B");
            }

        } while (true);

    }

    /**
     * �����V�K�쐬
     * 
     * @param personalList ���[�U�[��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �������擾
        String inputName = inputName();

        // �����ԍ��͐V�K�̔�
        int accountNumber = createNewAccountNo();

        // �l��ݒ�
        Personal personal = new Personal(inputName, accountNumber, 0);
        personalList.add(personal);
        // �������X�V
        DepositProcessiong deposit = new DepositProcessiong();
        deposit.depositMoney(personalList.size() - 1, personalList);

        System.out.println("������V�K�o�^���܂����B");

    }

    /**
     * ���������̏���
     * 
     * @param personalList ���[�U��񃊃X�g
     * @throws IOException
     * @throws FileReadException
     * @throws FileWriteException
     */
    public void releaseAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �C������l���̔ԍ����擾
        int personOfNumber = Util.getTargetNo(personalList, "�ǂ̃��[�U�̏��������܂����H");

        // 0�̏ꍇ�́A�ŏ��ɖ߂�
        if (personOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
            return;
        }

        int idx = personOfNumber - 1;

        // �p�~�\������̎c���o��
        Personal releaseTarget = personalList.get(idx);
        System.out.println(String.format("%,d�~�o�����܂����B", releaseTarget.getBalance()));

        // �����X�V
        FileProcessing fp = new FileProcessing();
        fp.writeHistory(releaseTarget.getAccountNumber(), AccountHandlingMenu.WITHDRAW.getId(), releaseTarget.getBalance(), 0);

        // ���X�g��������폜
        personalList.remove(idx);

        // �S�������č쐬
        fp.createFile(true, personalList, 0);

        System.out.println(releaseTarget.getName() + "����̌������폜���܂����B");

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * �����I��\�������擾
     * 
     * @param msg �\������������
     * @return �����I�𕶌�
     */
    private String getDisplayString(String msg) {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("������I�����Ă�������").append("\n");
        sb.append(msg);
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * �C����������擾
     * 
     * @param toModifyPropertyMsg �R���\�[���ɕ\�����镶��
     * @return �C���ԍ�
     */
    private int getModifyUserInfo(final String toModifyPropertyMsg) {
        int propertyOfNumber = 0;
        do {
            // ���͒l���擾
            propertyOfNumber = Util.inputInt(toModifyPropertyMsg);

        } while (Util.isOutOfRange(propertyOfNumber, Util.RANGE_MIN, AccountHandlingMenu.values().length));
        return propertyOfNumber;
    }

    /**
     * �C�����鑮���\�������쐬
     * 
     * @param modifyName �C������l������
     * @return �\������
     */
    private String displayToCorrectProperty(final String correctName) {

        StringBuffer sb = new StringBuffer();

        sb.append(correctName + "������C�����܂��B").append("\n");
        sb.append("�ǂ̏����C�����܂����H").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(String.format(Util.DISPLAY_FORMAT_OF_PERSONAL_LIST, Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST)).append(".").append(Util.BACK)
            .append("\n");
        sb.append(AccountHandlingMenu.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }

    /**
     * �����ԍ��V�K�̔�
     * 
     * @return �V�K�̔Ԕԍ�
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private int createNewAccountNo() throws FileWriteException, FileReadException, IOException {

        FileProcessing fp = new FileProcessing();
        // �̔ԗp�̃t�@�C����ǂݍ���
        // +1����
        int nextAccountNo = sumUpAccountNo(fp.getAccountNo());

        // �̔Ԃ����ԍ����̔ԗp�̃t�@�C���ɏ�������
        fp.createFile(false, null, nextAccountNo);

        // �̔Ԃ����ԍ���Ԃ�
        return nextAccountNo;

    }

    /**
     * �����ԍ����Z����
     * 
     * @param accountNo �����ԍ�
     * @return ���Z���������ԍ�
     */
    private int sumUpAccountNo(String accountNo) {
        // �������琔�l�ɕϊ�
        int retVlalue = Integer.parseInt(accountNo);
        // ���Z
        return ++retVlalue;

    }

    /**
     * ���͂��ꂽ�������擾
     * 
     * @return ����
     */
    private String inputName() {
        return Util.inputStr("��������͂��Ă�������");
    }
}
