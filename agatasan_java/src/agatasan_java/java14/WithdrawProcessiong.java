package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 出金処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 * @version 1.2 2021/05/29 No.117指摘対応
 *
 */
public class WithdrawProcessiong {
    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 入金下限金額 */
    private static final int MINIMUM_AMOUNT = 1;
    /** 入金上限金額 */
    private static final int MAXIMUM_AMOUNT = 10000000;
    // --------------------------------------------------
    // public関数
    // --------------------------------------------------

    /**
     * 出金処理
     * 
     * @param depositIdx   出金対象インデックス
     * @param personalList ユーザ情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void withdrawMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 出金対象
        Personal target = personalList.get(depositIdx);

        // 出金情報取得
//        long inputWithdraw = getWithdraw(target);
        long inputWithdraw = Util.getInputMoneyInfo(AccountHandlingMenu.WITHDRAW, "出金", target, null);

        // 残高の合計
        long sum = target.getBalance() - inputWithdraw;
        new BalanceProcessiong().displayBalance(sum);

        // 残高の設定
        target.setBalance(sum);

        // 口座の更新
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // 出金履歴
        fp.writeHistory(target.getAccountNumber(), AccountHandlingMenu.WITHDRAW.getId(), inputWithdraw, sum);

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 入力金額情報の取得
     * 
     * @param target ユーザ情報
     * @return 入力金額
     */
//    private static long getWithdraw(Personal personal) {
//        long inputDeposit = 0;
//        do {
//            // 入力値を取得
//            inputDeposit = Util.inputMoney("出金");
//
//        } while (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT) || Util.canPay(personal, inputDeposit));
//
//        return inputDeposit;
//    }
}
