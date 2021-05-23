package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * 振込処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public class TransferProcessiong {
    /**
     * 振込処理
     * 
     * @param transfer     振込元
     * @param personalList ユーザー情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            String toModifyPersonMsg = Util.displayToCorrectPerson(personalList, "どのユーザに振込しますか？");

            // 振込先の人物の番号を取得
            int payeeOfNumber = Util.getCorrectPerson(toModifyPersonMsg, personalList);

            // 0の場合は、最初に戻る
            if (payeeOfNumber == 0) {
                return;
            }

            int idx = payeeOfNumber - 1;

            // 入力された番号に紐づく名前を取得
            Personal payee = personalList.get(idx);

            // 自分に振込は不可
            if (transfer.getName().equals(payee.getName())) {
                System.out.println("ご自分には振込めません。");
                continue;
            }
            // 振込情報取得
            int inputDeposit = getTransferInfo(transfer, payee);

            // 振込元の残高を設定
            transfer.setBalance(transfer.getBalance() - inputDeposit);
            // 振込先の残高を設定
            payee.setBalance(payee.getBalance() + inputDeposit);

            // 振込元履歴の更新
            FileProcessing fp = new FileProcessing();
            fp.writeHistory(transfer.getAccountNumber(), Bank.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
            // 振込先履歴の更新
            fp.writeHistory(payee.getAccountNumber(), Bank.TRANSFER.getId(), inputDeposit, payee.getBalance());

            System.out.println(String.format("%Sさんに振込完了しました。", payee.getName()));

            break;
        } while (true);

    }

    /**
     * 振込情報取得
     * 
     * @param transfer 振込元ユーザー情報
     * @param payee    振込先ユーザー情報
     * @return 振込金額
     */
    private int getTransferInfo(final Personal transfer, final Personal payee) {
        int inputDeposit = 0;
        DepositProcessiong deposit = new DepositProcessiong();
        do {
            // 入力値を取得
            inputDeposit = deposit.inputDeposit();

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000) || canPay(transfer, inputDeposit));

        return inputDeposit;
    }

    /**
     * 残高内で払えるかのチェック
     * 
     * @param transfer     振込先のユーザー情報
     * @param inputDeposit 振込金額
     * @return true:残高内で払える
     */
    private boolean canPay(final Personal transfer, final int inputDeposit) {

        // 自分の口座から払えない場合
        if (transfer.getBalance() - inputDeposit < 0) {
            System.out.println(String.format("ご自分の残高%,d円内で振り込んでください。", transfer.getBalance()));
            return true;
        }
        return false;
    }
}
