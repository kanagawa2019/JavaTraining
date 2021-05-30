package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 出金処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 * @version 1.2 2021/05/30 No.110～122指摘対応
 *
 */
public class WithdrawProcessiong extends AccountService {
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

        // 限度額上限の場合は、中止
        if (target.getBalance() == MIN_BALANCE) {
            System.out.println("預金額が0円のため、出金を中止致します。");
            return;
        }

        // 出金情報取得
        long inputWithdraw = getInputMoneyInfo(AccountHandlingMenu.WITHDRAW, "出金", target, null);

        // 残高の合計
        long sum = target.getBalance() - inputWithdraw;
        displayBalance(sum);

        // 残高の設定
        target.setBalance(sum);

        // 口座の更新
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // 出金履歴
        fp.writeHistory(target.getAccountNumber(), AccountHandlingMenu.WITHDRAW.getId(), inputWithdraw, sum);

    }

}
