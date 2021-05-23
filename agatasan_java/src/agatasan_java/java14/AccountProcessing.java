package agatasan_java.java14;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

public class AccountProcessing {
    /** �X�L���i�[�i�R���\�[�����́j */
    private Scanner mScanner = new Scanner(System.in);

    /** ���t�`�� �Fyyyy�NMM��dd�� */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy�NMM��dd��";
    /** ���̓J�[�\�� */
    private static final String CURSOL = ">";
    /** �����p�� */
    private static final String PROCESSING_CONTINUE = "Y";
    /** �����I�� */
    private static final String PROCESSING_END = "N";
    /** �͈͓��̃��b�Z�[�W */
    private static final String WITHIN_RANGE = "%d�`%d�͈̔͂œ��͂��Ă��������B";
    /** �z��O�̃��b�Z�[�W */
    private static final String UNEXPECTED_ERR = "�z�肳�ꂽ�����͂���܂���B�V�X�e���Ǘ��҂ɘA�����Ă��������B";
    /** �l�����X�g�\���`�� */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_LIST = "%2d";
    /** �������X�g�\���`�� */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_ATTRIBUTE_LIST = "%2d";
    /** �������X�g�J�n�ԍ� */
    public static final int START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST = 0;
    /** �O�ɖ߂�̃��b�Z�[�W */
    private static final String BACK = "�O�ɖ߂�";
    /** �I�����̍ŏ��l */
    private static final int RANGE_MIN = 0;
    /** �I�����̍ő�l */
    private static final int RANGE_MAX = 4;

    /**
     * �����̏�������
     */
    public Account inputAccount() {
        Account account = null;
        String displayMsg = getDisplayString(Account.getSelectAccountString());
        do {
            account = Account.convertAccount(inputStr(displayMsg));
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
     * �c���\��
     * 
     * @param balance �c��
     */
    private void displayBalance(final int balance) {
        System.out.println(String.format("�c���́A%,d�~�ł�", balance));
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
        depositMoney(personalList.size() - 1, personalList);

        System.out.println("������V�K�o�^���܂����B");

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
        fp.createAccountNoFile(nextAccountNo);

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
     * ��������̏���
     * 
     * @param personalList ���[�U�[��񃊃X�g
     */
    public void changeAccountInfo(List<Personal> personalList) {

        do {

            // �C������l���\�������쐬
            String toModifyPersonMsg = displayToCorrectPerson(personalList, "�ǂ̃��[�U�̏��������܂����H");

            // �C������l���̔ԍ����擾
            int personOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

            // 0�̏ꍇ�́A�ŏ��ɖ߂�
            if (personOfNumber == 0) {
                return;
            }

            int idx = personOfNumber - 1;

            // ���͂��ꂽ�ԍ��ɕR�Â����O���擾
            Personal personal = personalList.get(idx);

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
                            depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // �U������
                            transfer(personal, personalList);
                            break;
                        case BALANCE:
                            // �c���\������
                            displayBalance(personal.getBalance());
                            break;
                        case HISTORY:
                            // ���������\��
                            displayHistory(personal.getAccountNumber());
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
     * ��������
     * 
     * @param depositIdx   �I�����ꂽ�C���f�b�N�X
     * @param personalList ���[�U�[��񃊃X�g
     * @return ������̎c��
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private int depositMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �����Ώ�
        Personal target = personalList.get(depositIdx);

        // �������擾
        int inputDeposit = getDeposit();

        // �c���̍��v
        int sum = inputDeposit + target.getBalance();
        displayBalance(sum);

        // �c���̐ݒ�
        target.setBalance(sum);

        // �����̍X�V
        FileProcessing fp = new FileProcessing();
        fp.createFile(personalList);

        // ��������
        fp.writeHistory(target.getAccountNumber(), Bank.DEPOSIT.getId(), inputDeposit, sum);

        return sum;
    }

    /**
     * �������̎擾
     * 
     * @return �������z
     */
    private int getDeposit() {
        int inputDeposit = 0;
        do {
            // ���͒l���擾
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000));

        return inputDeposit;
    }

    /**
     * �U������
     * 
     * @param transfer     �U����
     * @param personalList ���[�U�[��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private void transfer(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            String toModifyPersonMsg = displayToCorrectPerson(personalList, "�ǂ̃��[�U�ɐU�����܂����H");

            // �U����̐l���̔ԍ����擾
            int payeeOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

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
     * ��������Ώۃ��[�U�[���擾
     * 
     * @param toCorrectPersonMsg �R���\�[���ɕ\�����镶��
     * @param userList           ���[�U�[��񃊃X�g
     * @return �������郆�[�U�[�ԍ�
     */
    private int getCorrectPerson(final String toCorrectPersonMsg, final List<Personal> userList) {
        int personOfNumber = 0;
        do {
            // ��������l���̔ԍ����擾
            personOfNumber = inputInt(toCorrectPersonMsg);

        } while (isOutOfRange(personOfNumber, RANGE_MIN, userList.size()));
        return personOfNumber;
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
            propertyOfNumber = inputInt(toModifyPropertyMsg);

        } while (isOutOfRange(propertyOfNumber, RANGE_MIN, RANGE_MAX));
        return propertyOfNumber;
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
        do {
            // ���͒l���擾
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000) || canPay(transfer, inputDeposit));

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

    /**
     * ���������\��
     * 
     * @param accountNumber �����ԍ�
     * @throws FileReadException
     * @throws IOException
     */
    private void displayHistory(final int accountNumber) throws FileReadException, IOException {

        // �����f�[�^�擾
        FileProcessing fp = new FileProcessing();
        List<AccountHistory> historyList = fp.getAccountHistory();

        // �����̒��Ō����ԍ���v�̂��̂�\��
        matchAccountNo(accountNumber, historyList);

    }

    /**
     * �\���Ώی����ԍ��ƈ�v���闚����\��
     * 
     * @param accountNumber �\���Ώی����ԍ�
     * @param historyList   ����������X�g
     */
    private void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // ��������
        Boolean isExistHistory = false;

        sb.append("***********************************").append("\n");
        sb.append("��������A�敪�A������z�A�c��").append("\n");

        for (AccountHistory history : historyList) {
            if (accountNumber == history.getAccountNumber()) {

                if (isExistHistory == false) {
                    isExistHistory = true;
                }

                sb.append(dateToString(history.getDate())).append("�A").append(history.getClassification().getName()).append("�A")
                        .append(String.format("%,d�~", history.getTransactionAmount())).append("�A").append(String.format("%,d�~", history.getBalance()))
                        .append("\n");

            }

        }

        sb.append("***********************************");

        System.out.println(isExistHistory == true ? sb.toString() : "����������͂���܂���B");
    }

    /**
     * ���t�𕶎���ɕϊ�
     * 
     * @param date ���t
     * @return ������^���t
     */
    private String dateToString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return dateFormat.format(date);
    }

    /**
     * ���͂��ꂽ�������擾
     * 
     * @return ����
     */
    private String inputName() {
        return inputStr("��������͂��Ă�������");
    }

    /**
     * ���͂��ꂽ���z���擾
     * 
     * @return ���z
     */
    private int inputDeposit() {
        return inputInt("�������z����͂��Ă�������");
    }

    /**
     * ��������
     * 
     * @param inputMsg ���̓R���\�[��
     * @return ���͒l
     */
    private String inputStr(final String inputMsg) {
        String input = null;
        boolean isCheck = false;

        System.out.println(inputMsg);
        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * ���l����(int�^)
     * 
     * @param inputMsg ���̓R���\�[���ɕ\�����镶��
     * @return ���͒l
     */
    private int inputInt(final String inputMsg) {
        int num = 0;
        String input = null;
        boolean isCheck = false;

        if (inputMsg != null) {
            System.out.println(inputMsg);
        }

        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("�\���󂠂�܂���B�������������s���܂���ł����B\n�ē��͂����肢���܂��B");
            }

        } while (!isCheck);
        return num;
    }

    /**
     * �����p���m�F
     * 
     * @return �����p����True�B�����I����false�B
     */
    public boolean isContinue() {
        boolean isCheck = false;

        String displayMsg = String.format("�����𑱂��܂����H(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END);

        do {
            String input = inputStr(displayMsg);

            // ���͒l�̔���
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    return true;
                case PROCESSING_END:
                    return false;
                default:
                    System.out.println(String.format("(%s/%s)�ȊO�����͂���܂����B\n�ē��͂����肢���܂��B", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return false;
    }

    /**
     * ���͐��l�͈͊O�`�F�b�N
     * 
     * @param number ���͂��ꂽ���l
     * @param min    �ŏ��l
     * @param max    �ő�l
     * @return ���͂��ꂽ���l���͈͊O�̏ꍇ��True�B�͈͓��̏ꍇ��false�B
     */
    private static boolean isOutOfRange(final int number, final int min, final int max) {

        // �͈͊O�̏ꍇ
        if (!isWithinRange(number, min, max)) {
            System.out.println(String.format(WITHIN_RANGE, min, max));
            return true;
        }
        return false;
    }

    /**
     * ���l�͈̓`�F�b�N
     * 
     * @param targetNumber �`�F�b�N�Ώې��l
     * @param minValue     �ŏ��l
     * @param maxValue     �ő�l
     * @return true:�ŏ��l�`�ő�l�͈͓̔��ɂ���
     */
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * �C������l���\�������쐬
     * 
     * @param userList ���[�U�[��񃊃X�g
     * @param msg
     * @return �\������
     */
    private static String displayToCorrectPerson(final List<Personal> userList, String msg) {

        System.out.println(msg);

        // ���앶���\��
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        int cnt = 0;
        sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, cnt)).append(".").append(BACK).append("\n");

        for (Personal u : userList) {
            sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, ++cnt)).append(".").append(u.getName()).append("\n");
        }
        sb.append("---------------------------").append("\n");

        return sb.toString();
    }

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
        sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST)).append(".").append(BACK).append("\n");
        sb.append(Bank.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }
}
