package agatasan_java.java14;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import agatasan_java.FileReadException;

/**
 * 残高表示関係処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public class BalanceProcessiong {

    /** 日付形式 ：yyyy年MM月dd日 */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy年MM月dd日";

    /**
     * 残高表示
     * 
     * @param balance 残高
     */
    public void displayBalance(final int balance) {
        System.out.println(String.format("残高は、%,d円です", balance));
    }

    /**
     * お取引履歴表示
     * 
     * @param accountNumber 口座番号
     * @throws FileReadException
     * @throws IOException
     */
    public void displayHistory(final int accountNumber) throws FileReadException, IOException {

        // 履歴データ取得
        FileProcessing fp = new FileProcessing();
        List<AccountHistory> historyList = fp.getAccountHistory();

        // 履歴の中で口座番号一致のものを表示
        matchAccountNo(accountNumber, historyList);

    }

    /**
     * 表示対象口座番号と一致する履歴を表示
     * 
     * @param accountNumber 表示対象口座番号
     * @param historyList   取引履歴リスト
     */
    private void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // 履歴あり
        Boolean isExistHistory = false;

        sb.append("***********************************").append("\n");
        sb.append("お取引日、区分、取引金額、残高").append("\n");

        for (AccountHistory history : historyList) {
            if (accountNumber == history.getAccountNumber()) {

                if (isExistHistory == false) {
                    isExistHistory = true;
                }

                sb.append(dateToString(history.getDate())).append("、").append(history.getClassification().getName()).append("、")
                        .append(String.format("%,d円", history.getTransactionAmount())).append("、").append(String.format("%,d円", history.getBalance()))
                        .append("\n");

            }

        }

        sb.append("***********************************");

        System.out.println(isExistHistory == true ? sb.toString() : "お取引履歴はありません。");
    }

    /**
     * 日付を文字列に変換
     * 
     * @param date 日付
     * @return 文字列型日付
     */
    private String dateToString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return dateFormat.format(date);
    }
}
