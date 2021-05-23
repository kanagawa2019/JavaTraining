package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

public class AccountProcessing {

    /** �z��O�̃��b�Z�[�W */
    private static final String UNEXPECTED_ERR = "�z�肳�ꂽ�����͂���܂���B�V�X�e���Ǘ��҂ɘA�����Ă��������B";
//    /** �l�����X�g�\���`�� */
//    public static final String DISPLAY_FORMAT_OF_PERSONAL_LIST = "%2d";
//    /** �������X�g�\���`�� */
//    public static final String DISPLAY_FORMAT_OF_PERSONAL_ATTRIBUTE_LIST = "%2d";
//    /** �������X�g�J�n�ԍ� */
//    public static final int START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST = 0;
//    /** �O�ɖ߂�̃��b�Z�[�W */
//    private static final String BACK = "�O�ɖ߂�";

    /** �I�����̍ő�l */
    private static final int RANGE_MAX = 4;

    /**
     * �����̏�������
     */
    public Account inputAccount() {
        Account account = null;
        String displayMsg = getDisplayString(Account.getSelectAccountString());
        do {
            account = Account.convertAccount(Util.inputStr(displayMsg));
            if (account == null) {
                System.out.println("�Y�����鏈����������܂���ł����B");
            }

        } while (account == null);
        return account;
    }

    /**
     * �����I��\�������擾
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
     * ��������̏���
     * 
     * @param personalList ���[�U�[��񃊃X�g
     */
    public void changeAccountInfo(List<Personal> personalList) {

        do {

            // �C������l���\�������쐬
            String toModifyPersonMsg = Util.displayToCorrectPerson(personalList, "�ǂ̃��[�U�̏��������܂����H");

            // �C������l���̔ԍ����擾
            int personOfNumber = Util.getCorrectPerson(toModifyPersonMsg, personalList);

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (personOfNumber == 0) {
                return;
            }

            int idx = personOfNumber - 1;

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            Personal personal = personalList.get(idx);

            DepositProcessiong deposit = new DepositProcessiong();
            TransferProcessiong transfer = new TransferProcessiong();
            BalanceProcessiong balance = new BalanceProcessiong();

            try {

                do {

                    // �C�����鑮���̔ԍ����擾
                    String toCorrectPropertyMsg = displayToCorrectProperty(personal.getName());

                    // �C�����郆�[�U�[���ԍ����擾
                    int propertyOfNumber = getModifyUserInfo(toCorrectPropertyMsg);

                    // 0 �̏ꍇ�A�C���l����I�����鏈���܂Ŗ߂�
                    if (propertyOfNumber == 0) {
                        break;
                    }

                    // --------------------------------------------------
                    // �����E�o��
                    // --------------------------------------------------

                    switch (Bank.convertBank(String.valueOf(propertyOfNumber))) {
                        case DEPOSIT:
                            // ��������
                            deposit.depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // �U������
                            transfer.transferMoney(personal, personalList);
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
                            System.out.println(UNEXPECTED_ERR);
                            break;
                    }

                } while (true);

            } catch (FileReadException | FileWriteException | IOException e) {
                System.out.println("�����𒆒f���܂����B�V�X�e���Ǘ��҂֖₢���킹���Ă��������B");
            }

        } while (true);

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

        } while (Util.isOutOfRange(propertyOfNumber, Util.RANGE_MIN, RANGE_MAX));
        return propertyOfNumber;
    }

//    /**
//     * ���͐��l�͈͊O�`�F�b�N
//     * 
//     * @param number ���͂��ꂽ���l
//     * @param min    �ŏ��l
//     * @param max    �ő�l
//     * @return ���͂��ꂽ���l���͈͊O�̏ꍇ��True�B�͈͓��̏ꍇ��false�B
//     */
//    private static boolean isOutOfRange(final int number, final int min, final int max) {
//
//        // �͈͊O�̏ꍇ
//        if (!Util.isWithinRange(number, min, max)) {
//            System.out.println(String.format(WITHIN_RANGE, min, max));
//            return true;
//        }
//        return false;
//    }

    /**
     * �C�����鑮���\�������쐬
     * 
     * @param modifyName �C������l������
     * @return �\������
     */
    private static String displayToCorrectProperty(final String correctName) {

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("%S������C�����܂��B", correctName)).append("\n");
        sb.append("�ǂ̏����C�����܂����H").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(String.format(Util.DISPLAY_FORMAT_OF_PERSONAL_LIST, Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST)).append(".").append(Util.BACK)
                .append("\n");
        sb.append(Bank.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }
}
