package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 振込処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109〜113指摘対応
 * @version 1.2 2021/05/29 No.111,117指摘対応
 * 
 */
public class TransferProcessiong {
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
     * 振込処理
     * 
     * @param transfer     振込元
     * @param personalList ユーザ情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        Personal payee;

        do {
            // 振込先の人物の番号を取得
            int payeeOfNumber = Util.getTargetNo(personalList, "どのユーザに振込しますか？");

            // 0の場合は、最初に戻る
            if (payeeOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
                return;
            }

            // 入力された番号に紐づく名前を取得
            payee = personalList.get(payeeOfNumber - 1);

            // 自分に振込しない場合
            if (!transfer.getName().equals(payee.getName())) {
                break;
            }
            System.out.println("ご自分には振込めません。");
        } while (true);

        remitMoney(transfer, payee);

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 振込情報取得
     * 
     * @param transfer 振込元ユーザ情報
     * @param payee    振込先ユーザ情報
     * @return 振込金額
     */
//    private static long getTransferInfo(final Personal transfer, final Personal payee) {
//        long inputDeposit = 0;
//        do {
//            // 入力値を取得
//            inputDeposit = Util.inputMoney("入金");
//
//        } while (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT) || Util.canPay(transfer, inputDeposit) || Util.isMaxBalance(
//            inputDeposit, payee.getBalance()));
//
//        return inputDeposit;
//    }

    /**
     * 相手先に振込む処理
     * 
     * @param transfer 振込元
     * @param payee    振込先
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void remitMoney(Personal transfer, Personal payee) throws FileWriteException, FileReadException, IOException {
        // 振込情報取得
        long inputDeposit = Util.getInputMoneyInfo(AccountHandlingMenu.TRANSFER, "入金", transfer, payee);

        // 振込元の残高を設定
        transfer.setBalance(transfer.getBalance() - inputDeposit);
        // 振込先の残高を設定
        payee.setBalance(payee.getBalance() + inputDeposit);

        // 振込元履歴の更新
        FileProcessing fp = new FileProcessing();
        fp.writeHistory(transfer.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
        // 振込先履歴の更新
        fp.writeHistory(payee.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), inputDeposit, payee.getBalance());

        System.out.println(String.format("%Sさんに振込完了しました。", payee.getName()));
    }
}
