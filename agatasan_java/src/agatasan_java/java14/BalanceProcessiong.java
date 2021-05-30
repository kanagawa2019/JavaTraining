package agatasan_java.java14;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * �c���\���֌W����
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 * @version 1.1 2021/05/26 No.109�`113�w�E�Ή�
 * 
 */
public class BalanceProcessiong {
    // --------------------------------------------------
    // �萔
    // --------------------------------------------------
    /** ����\���ڑ����� */
    private static final String DISPLAY_CONNECT = "|";
    /** �w�b�_�[���̕\���`���F������� */
    private static final String HEADER_FORMAT_FOR_DATE = "%-11S";
    /** �w�b�_�[���̕\���`���F�敪 */
    private static final String HEADER_FORMAT_FOR_CLASSIFICATION = "%-3S";
    /** �w�b�_�[���̕\���`���F������z */
    private static final String HEADER_FORMAT_FOR_TRANSACTIONAMOUNT = "%-8S";
    /** �f�[�^���̕\���`���F������� */
    private static final String DATA_FORMAT_FOR_DATE = "%10S";
    /** �f�[�^���̕\���`���F�敪 */
    private static final String DATA_FORMAT_FOR_CLASSIFICATION = "%-3S";
    /** �f�[�^���̕\���`���F������z */
    private static final String DATA_FORMAT_FOR_TRANSACTIONAMOUNT = "%,10d�~";
    /** �f�[�^���̕\���`���F�c�� */
    private static final String DATA_FORMAT_FOR_BALANCE = "%,10d�~";

    /** ���t�`�� �Fyyyy�NMM��dd�� */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy�NMM��dd��";

    // --------------------------------------------------
    // public�֐�
    // --------------------------------------------------
    /**
     * �c���\��
     * 
     * @param balance �c��
     */
    public void displayBalance(final long balance) {
        System.out.println(String.format("�c���́A%,d�~�ł�", balance));
    }

    /**
     * ���������\��
     * 
     * @param accountNumber �����ԍ�
     * @throws FileReadException
     * @throws IOException
     */
    public static void displayHistory(final int accountNumber) throws FileReadException, IOException {

        // �����f�[�^�擾
        FileProcessing fp = new FileProcessing();
        List<AccountHistory> historyList = fp.getAccountHistory();

        // �����̒��Ō����ԍ���v�̂��̂�\��
        matchAccountNo(accountNumber, historyList);

    }

    // --------------------------------------------------
    // private�֐�
    // --------------------------------------------------
    /**
     * �\���Ώی����ԍ��ƈ�v���闚����\��
     * 
     * @param accountNumber �\���Ώی����ԍ�
     * @param historyList   ����������X�g
     */
    private static void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // ��������
        Boolean isExistHistory = false;

        sb.append("***********************************************").append("\n");
        sb.append(String.format(HEADER_FORMAT_FOR_DATE, "�������")).append(DISPLAY_CONNECT).append(String.format(HEADER_FORMAT_FOR_CLASSIFICATION, "�敪"))
            .append(DISPLAY_CONNECT).append(String.format(HEADER_FORMAT_FOR_TRANSACTIONAMOUNT, "������z")).append(DISPLAY_CONNECT).append("�c��")
            .append("\n");

        for (AccountHistory history : historyList) {
            if (accountNumber == history.getAccountNumber()) {

                if (isExistHistory == false) {
                    isExistHistory = true;
                }

                sb.append(String.format(DATA_FORMAT_FOR_DATE, dateToString(history.getDate()))).append(DISPLAY_CONNECT)
                    .append(String.format(DATA_FORMAT_FOR_CLASSIFICATION, history.getClassification().getName())).append(DISPLAY_CONNECT)
                    .append(String.format(DATA_FORMAT_FOR_TRANSACTIONAMOUNT, history.getTransactionAmount())).append(DISPLAY_CONNECT)
                    .append(String.format(DATA_FORMAT_FOR_BALANCE, history.getBalance())).append("\n");

            }

        }

        sb.append("***********************************************");

        System.out.println(isExistHistory == true ? sb.toString() : "����������͂���܂���B");
    }

    /**
     * ���t�𕶎���ɕϊ�
     * 
     * @param date ���t
     * @return ������^���t
     */
    private static String dateToString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return dateFormat.format(date);
    }
}
