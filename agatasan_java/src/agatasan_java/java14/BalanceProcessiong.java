package agatasan_java.java14;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 残高表示関係処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 * 
 */
public class BalanceProcessiong {
    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 履歴表示接続文字 */
    private static final String DISPLAY_CONNECT = "|";
    /** ヘッダー部の表示形式：お取引日 */
    private static final String HEADER_FORMAT_FOR_DATE = "%-11S";
    /** ヘッダー部の表示形式：区分 */
    private static final String HEADER_FORMAT_FOR_CLASSIFICATION = "%-3S";
    /** ヘッダー部の表示形式：取引金額 */
    private static final String HEADER_FORMAT_FOR_TRANSACTIONAMOUNT = "%-8S";
    /** データ部の表示形式：お取引日 */
    private static final String DATA_FORMAT_FOR_DATE = "%10S";
    /** データ部の表示形式：区分 */
    private static final String DATA_FORMAT_FOR_CLASSIFICATION = "%-3S";
    /** データ部の表示形式：取引金額 */
    private static final String DATA_FORMAT_FOR_TRANSACTIONAMOUNT = "%,10d円";
    /** データ部の表示形式：残高 */
    private static final String DATA_FORMAT_FOR_BALANCE = "%,10d円";

    /** 日付形式 ：yyyy年MM月dd日 */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy年MM月dd日";

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------
    /**
     * 残高表示
     * 
     * @param balance 残高
     */
    public void displayBalance(final long balance) {
        System.out.println(String.format("残高は、%,d円です", balance));
    }

    /**
     * お取引履歴表示
     * 
     * @param accountNumber 口座番号
     * @throws FileReadException
     * @throws IOException
     */
    public static void displayHistory(final int accountNumber) throws FileReadException, IOException {

        // 履歴データ取得
        FileProcessing fp = new FileProcessing();
        List<AccountHistory> historyList = fp.getAccountHistory();

        // 履歴の中で口座番号一致のものを表示
        matchAccountNo(accountNumber, historyList);

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 表示対象口座番号と一致する履歴を表示
     * 
     * @param accountNumber 表示対象口座番号
     * @param historyList   取引履歴リスト
     */
    private static void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // 履歴あり
        Boolean isExistHistory = false;

        sb.append("***********************************************").append("\n");
        sb.append(String.format(HEADER_FORMAT_FOR_DATE, "お取引日")).append(DISPLAY_CONNECT).append(String.format(HEADER_FORMAT_FOR_CLASSIFICATION, "区分"))
            .append(DISPLAY_CONNECT).append(String.format(HEADER_FORMAT_FOR_TRANSACTIONAMOUNT, "取引金額")).append(DISPLAY_CONNECT).append("残高")
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

        System.out.println(isExistHistory == true ? sb.toString() : "お取引履歴はありません。");
    }

    /**
     * 日付を文字列に変換
     * 
     * @param date 日付
     * @return 文字列型日付
     */
    private static String dateToString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return dateFormat.format(date);
    }
}
