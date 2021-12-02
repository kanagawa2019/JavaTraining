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
 * @version 1.2 2021/05/30 No.110～122指摘対応
 * @version 1.3 2021/06/01 No.126,128,130,131指摘対応
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
    private static final String HEADER_FORMAT_FOR_TRANSACTIONAMOUNT = "%-10S";
    /** データ部の表示形式：お取引日 */
    private static final String DATA_FORMAT_FOR_DATE = "%10S";
    /** データ部の表示形式：区分 */
    private static final String DATA_FORMAT_FOR_CLASSIFICATION = "%-3S";
    /** データ部の表示形式：取引金額 */
    private static final String DATA_FORMAT_FOR_TRANSACTIONAMOUNT = "%,12d円";
    /** データ部の表示形式：残高 */
    private static final String DATA_FORMAT_FOR_BALANCE = "%,26d円";
    /** 日付形式 ：yyyy年MM月dd日 */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy年MM月dd日";

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------

    /**
     * お取引履歴表示
     * 
     * @param accountNumber 口座番号
     * @throws FileReadException
     * @throws IOException
     */
    public static void displayHistory(final int accountNumber) throws FileReadException, IOException {
        // 履歴の中で口座番号一致のものを表示
        matchAccountNo(accountNumber, FileProcessing.getAccountHistory());
    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 表示対象口座番号と一致する履歴を表示
     * 
     * @param accountNumber 表示対象口座番号
     * @param historyList         取引履歴リスト
     */
    private static void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // 履歴あり
        Boolean isExistHistory = false;

        // ヘッダーの設定
        sb.append("****************************************************************").append("\n");
        sb.append(String.format(HEADER_FORMAT_FOR_DATE, "お取引日")).append(DISPLAY_CONNECT)
           .append(String.format(HEADER_FORMAT_FOR_CLASSIFICATION, "区分")).append(DISPLAY_CONNECT)
           .append(String.format(HEADER_FORMAT_FOR_TRANSACTIONAMOUNT, "取引金額")).append(DISPLAY_CONNECT)
           .append("残高").append("\n");

        // 明細の設定
        for (AccountHistory history : historyList) {

            if (history.getAccountNumber() != accountNumber) continue;

            if (!isExistHistory) {
                isExistHistory = true;
            }

            sb.append(String.format(DATA_FORMAT_FOR_DATE, dateToString(history.getDate()))).append(DISPLAY_CONNECT)
               .append(String.format(DATA_FORMAT_FOR_CLASSIFICATION, history.getClassification().getName())).append(DISPLAY_CONNECT)
               .append(String.format(DATA_FORMAT_FOR_TRANSACTIONAMOUNT, history.getTransactionAmount())).append(DISPLAY_CONNECT)
               .append(String.format(DATA_FORMAT_FOR_BALANCE, history.getBalance())).append("\n");

        }

        sb.append("****************************************************************");

        System.out.println(isExistHistory ? sb.toString() : "お取引履歴はありません。");
    }

    /**
     * 日付を文字列に変換
     * 
     * @param date 日付
     * @return 文字列型日付
     */
    private static String dateToString(final Date date) {
        return new SimpleDateFormat(DATE_OF_STANDARD_BIRTH).format(date);
    }
}
