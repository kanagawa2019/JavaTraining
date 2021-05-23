package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * 入金処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public class DepositProcessiong {

    /**
     * 入金処理
     * 
     * @param depositIdx   選択されたインデックス
     * @param personalList ユーザー情報リスト
     * @return 入金後の残高
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public int depositMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 入金対象
        Personal target = personalList.get(depositIdx);

        // 入金情報取得
        int inputDeposit = getDeposit();

        // 残高の合計
        int sum = inputDeposit + target.getBalance();
        new BalanceProcessiong().displayBalance(sum);

        // 残高の設定
        target.setBalance(sum);

        // 口座の更新
        FileProcessing fp = new FileProcessing();
        fp.createFile(personalList);

        // 入金履歴
        fp.writeHistory(target.getAccountNumber(), Bank.DEPOSIT.getId(), inputDeposit, sum);

        return sum;
    }

    /**
     * 入金情報の取得
     * 
     * @return 入金金額
     */
    private int getDeposit() {
        int inputDeposit = 0;
        do {
            // 入力値を取得
            inputDeposit = inputDeposit();

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000));

        return inputDeposit;
    }

    /**
     * 入力された金額を取得
     * 
     * @return 金額
     */
    public int inputDeposit() {
        return Util.inputInt("入金金額を入力してください");
    }

}
