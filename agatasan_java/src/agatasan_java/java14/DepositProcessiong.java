package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 入金処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 *
 */
public class DepositProcessiong {

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
     * 入金処理
     * 
     * @param depositIdx   選択されたインデックス
     * @param personalList ユーザ情報リスト
     * @return 入金後の残高
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void depositMoney(final int depositIdx, List<Personal> personalList) throws IOException, FileWriteException, FileReadException {

        // 入金対象
        Personal target = personalList.get(depositIdx);

        // 入金可能かのチェック
        if (target.getBalance() >= Util.MAX_BALANCE) {
            System.out.println("口座保有限度額に達しているため、入金できません。");
            return;
        }

        // 入金情報取得
//        long inputDeposit = getDeposit(target);
        long inputDeposit = Util.getInputMoneyInfo(AccountHandlingMenu.DEPOSIT, "入金", target, null);

        // 残高の合計
        long sum = inputDeposit + target.getBalance();
        new BalanceProcessiong().displayBalance(sum);

        // 残高の設定
        target.setBalance(sum);

        // 口座の更新
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // 入金履歴
        fp.writeHistory(target.getAccountNumber(), AccountHandlingMenu.DEPOSIT.getId(), inputDeposit, sum);

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
//    private static long getDeposit(Personal target) {
//        long inputDeposit = 0;
//        do {
//            // 入力値を取得
//            inputDeposit = Util.inputMoney("入金");
//
//        } while (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT) || Util.isMaxBalance(inputDeposit, target.getBalance()));
//
//        return inputDeposit;
//    }

}
