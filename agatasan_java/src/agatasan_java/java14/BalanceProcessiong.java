package agatasan_java.java14;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import agatasan_java.FileReadException;

/**
 * �c���\���֌W����
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 *
 */
public class BalanceProcessiong {

    /** ���t�`�� �Fyyyy�NMM��dd�� */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy�NMM��dd��";

    /**
     * �c���\��
     * 
     * @param balance �c��
     */
    public void displayBalance(final int balance) {
        System.out.println(String.format("�c���́A%,d�~�ł�", balance));
    }

    /**
     * ���������\��
     * 
     * @param accountNumber �����ԍ�
     * @throws FileReadException
     * @throws IOException
     */
    public void displayHistory(final int accountNumber) throws FileReadException, IOException {

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
}
