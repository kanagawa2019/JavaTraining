package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * 出金処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public class WithdrawProcessiong {

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
    public void withdrawMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 出金対象
        Personal target = personalList.get(depositIdx);

        // 出金情報取得
        int inputWithdraw = Util.inputMoney("出金");

        // 残高の合計
        int sum = target.getBalance() - inputWithdraw;
        new BalanceProcessiong().displayBalance(sum);

        // 残高の設定
        target.setBalance(sum);

        // 口座の更新
        FileProcessing fp = new FileProcessing();
        fp.createFile(true, personalList, 0);

        // 出金履歴
        fp.writeHistory(target.getAccountNumber(), Bank.WITHDRAW.getId(), inputWithdraw, sum);

    }
}
