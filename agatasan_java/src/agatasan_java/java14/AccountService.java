package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 口座取り扱い親クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/30 新規作成
 *
 */
public class AccountService {

    /** 口座保有限度額（下限） */
    protected static final long MIN_BALANCE = 0l;
    /** 口座保有限度額（上限） */
    protected static final long MAX_BALANCE = 9000000000000000000l;
    /** 入金下限金額 */
    private static final int MINIMUM_AMOUNT = 1;
    /** 入金上限金額 */
    private static final int MAXIMUM_AMOUNT = 10000000;

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------
    /**
     * 残高表示
     * 
     * @param balance 残高
     */
    protected static void displayBalance(final long balance) {
        System.out.println(String.format("残高は、%,d円です", balance));
    }

    /**
     * 入力金額を取得
     * 
     * @param menu     口座取り扱いメニュー
     * @param msg      表示メッセージ
     * @param transfer 振込元
     * @param payee    振込先
     * @return 取扱金額
     */
    protected static long getInputMoneyInfo(final AccountHandlingMenu menu, final String msg, final Personal transfer, final Personal payee) {
        long inputDeposit = 0;
        do {
            // 入力値を取得
            inputDeposit = Util.inputMoney(msg);

        } while (isMatchCondition(menu, inputDeposit, transfer, payee));

        return inputDeposit;
    }

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
    protected static void depositMoney(final int depositIdx, List<Personal> personalList) throws IOException, FileWriteException, FileReadException {

        // 入金対象
        Personal target = personalList.get(depositIdx);

        // 入金可能かのチェック
        if (target.getBalance() >= MAX_BALANCE) {
            System.out.println("口座保有限度額に達しているため、入金できません。");
            return;
        }

        // 入金情報取得
        long inputDeposit = getInputMoneyInfo(AccountHandlingMenu.DEPOSIT, "入金", target, null);

        // 残高の合計
        long sum = inputDeposit + target.getBalance();
        displayBalance(sum);

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
     * 入力金額が適正かチェック
     * 
     * @param menu         口座取り扱いメニュー
     * @param inputDeposit 入力金額
     * @param transfer     振込元
     * @param payee        振込先
     * @return 不適正の場合はtrue、適性の場合はfalseを返す
     */
    private static boolean isMatchCondition(final AccountHandlingMenu menu, final long inputDeposit, final Personal transfer, final Personal payee) {

        if (menu == AccountHandlingMenu.DEPOSIT) {
            if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (isMaxBalance(inputDeposit, transfer.getBalance())) {
                return true;
            }
        }

        if (menu == AccountHandlingMenu.TRANSFER) {
            if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (canPay(transfer, inputDeposit)) {
                return true;
            } else if (isMaxBalance(inputDeposit, payee.getBalance())) {
                return true;
            }

        }

        if (menu == AccountHandlingMenu.WITHDRAW) {
            if (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (canPay(transfer, inputDeposit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 残高内で払えるかのチェック
     * 
     * @param personal     ユーザ情報
     * @param inputDeposit 振込金額
     * @return true:残高内で払える
     */
    private static boolean canPay(final Personal personal, final long inputDeposit) {

        // 自分の口座から払えない場合
        if (personal.getBalance() - inputDeposit < 0) {
            System.out.println(String.format("ご自分の残高%,d円内でお取り扱いできます。", personal.getBalance()));
            return true;
        }
        return false;
    }

    /**
     * 入金可能かの確認
     * 
     * @param inputDeposit 入金金額
     * @param balance      残高
     * @return
     */
    private static boolean isMaxBalance(final long inputDeposit, final long balance) {

        if (MAX_BALANCE < inputDeposit + balance) {
            System.out.println(String.format("入金は%,d円までしか受付られません。", MAX_BALANCE - balance));
            return true;
        }
        return false;
    }
}
