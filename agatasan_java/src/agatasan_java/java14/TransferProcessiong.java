package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 振込処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 * @version 1.2 2021/05/30 No.110～122指摘対応
 * @version 1.3 2021/05/31 No.123～131指摘対応
 * @version 1.4 2021/06/01 No.126,128,130,131指摘対応
 * 
 */
public class TransferProcessiong extends AccountService {

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------
    /**
     * 振込処理
     * 
     * @param transfer       振込元
     * @param personalList  ユーザ情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        Personal payee;

        do {
            // 振込先の人物の番号を取得
            int payeeOfNumber = Util.getTargetNo(personalList, "どのユーザに振込しますか？");

            // 0の場合は、1段階前に戻る
            if (payeeOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) return;

            // 入力された番号に紐づく名前を取得
            payee = personalList.get(payeeOfNumber - 1);

            // 自分に振込しない場合
            if (!transfer.getName().equals(payee.getName())) break;

            System.out.println("ご自分には振込めません。");
        } while (true);

        // 振込処理実施
        remitMoney(transfer, payee);

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------

    /**
     * 相手先に振込む処理
     * 
     * @param transfer   振込元
     * @param payee     振込先
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void remitMoney(Personal transfer, Personal payee) throws FileWriteException, FileReadException, IOException {

        // 振込先の限度額上限の場合は、振込中止
        if (payee.getBalance() == MAX_BALANCE) {
            System.out.println("振込先の預金上限額が達しているため、お振込を中止致します。");
            return;
        }

        // 振込情報取得
        long inputDeposit = getInputMoneyInfo(AccountHandlingMenu.TRANSFER, "入金", transfer, payee);

        // 振込元の残高を設定
        transfer.setBalance(transfer.getBalance() - inputDeposit);
        // 振込先の残高を設定
        payee.setBalance(payee.getBalance() + inputDeposit);

        // 振込元履歴の更新
        FileProcessing.writeHistory(transfer.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
        // 振込先履歴の更新
        FileProcessing.writeHistory(payee.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), inputDeposit, payee.getBalance());

        System.out.println(String.format("%Sさんに振込完了しました。", payee.getName()));
    }
}
