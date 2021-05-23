package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * 口座処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public class AccountProcessing {

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 想定外のメッセージ */
    private static final String UNEXPECTED_ERR = "想定された処理はありません。システム管理者に連絡してください。";

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------

    /**
     * 口座の処理入力
     */
    public Account inputAccount() {
        Account account = null;
        String displayMsg = getDisplayString(Account.getSelectAccountString());
        do {
            account = Account.convertAccount(Util.inputStr(displayMsg));
            if (account == null) {
                System.out.println("該当する処理が見つかりませんでした。");
            }

        } while (account == null);
        return account;
    }

    /**
     * 口座操作の処理
     * 
     * @param personalList ユーザ情報リスト
     */
    public void changeAccountInfo(List<Personal> personalList) {

        do {

            // 修正する人物の番号を取得
            int personOfNumber = Util.getTargetNo(personalList, "どのユーザの処理をしますか？");

            // 0の場合は、最初に戻る
            if (personOfNumber == 0) {
                return;
            }

            int idx = personOfNumber - 1;

            // 入力された番号に紐づく名前を取得
            Personal personal = personalList.get(idx);

            DepositProcessiong deposit = new DepositProcessiong();
            TransferProcessiong transfer = new TransferProcessiong();
            BalanceProcessiong balance = new BalanceProcessiong();
            WithdrawProcessiong withdraw = new WithdrawProcessiong();

            try {

                do {

                    // 修正する属性の番号を取得
                    String toCorrectPropertyMsg = displayToCorrectProperty(personal.getName());

                    // 修正するユーザ情報番号を取得
                    int propertyOfNumber = getModifyUserInfo(toCorrectPropertyMsg);

                    // 0 の場合、修正人物を選択する処理まで戻る
                    if (propertyOfNumber == 0) {
                        break;
                    }

                    // --------------------------------------------------
                    // 処理・出力
                    // --------------------------------------------------

                    switch (Bank.convertBank(String.valueOf(propertyOfNumber))) {
                        case DEPOSIT:
                            // 入金処理
                            deposit.depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // 振込処理
                            transfer.transferMoney(personal, personalList);
                            break;
                        case WITHDRAW:
                            // 出金処理
                            withdraw.withdrawMoney(idx, personalList);
                            break;
                        case BALANCE:
                            // 残高表示処理
                            balance.displayBalance(personal.getBalance());
                            break;
                        case HISTORY:
                            // お取引履歴表示
                            balance.displayHistory(personal.getAccountNumber());
                            break;
                        default:
                            System.out.println(UNEXPECTED_ERR);
                            break;
                    }

                } while (true);

            } catch (FileReadException | FileWriteException | IOException e) {
                System.out.println("処理を中断しました。システム管理者へ問い合わせしてください。");
            }

        } while (true);

    }

    /**
     * 口座新規作成
     * 
     * @param personalList ユーザー情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 氏名を取得
        String inputName = inputName();

        // 口座番号は新規採番
        int accountNumber = createNewAccountNo();

        // 値を設定
        Personal personal = new Personal(inputName, accountNumber, 0);
        personalList.add(personal);
        // 口座を更新
        DepositProcessiong deposit = new DepositProcessiong();
        deposit.depositMoney(personalList.size() - 1, personalList);

        System.out.println("口座を新規登録しました。");

    }

    /**
     * 口座解除の処理
     * 
     * @param personalList ユーザ情報リスト
     * @throws IOException
     * @throws FileReadException
     * @throws FileWriteException
     */
    public void releaseAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 修正する人物の番号を取得
        int personOfNumber = Util.getTargetNo(personalList, "どのユーザの処理をしますか？");

        // 0の場合は、最初に戻る
        if (personOfNumber == 0) {
            return;
        }

        int idx = personOfNumber - 1;

        // 廃止予定口座の残高出金
        Personal releaseTarget = personalList.get(idx);
        System.out.println(String.format("%,d円出金しました。", releaseTarget.getBalance()));

        // 履歴更新
        FileProcessing fp = new FileProcessing();
        fp.writeHistory(releaseTarget.getAccountNumber(), Bank.WITHDRAW.getId(), releaseTarget.getBalance(), 0);

        // リストから口座削除
        personalList.remove(idx);

        // 全件口座再作成
        fp.createFile(true, personalList, 0);

        System.out.println(releaseTarget.getName() + "さんの口座を削除しました。");

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * 処理選択表示文言取得
     * 
     * @param msg 表示したい文言
     * @return 処理選択文言
     */
    private String getDisplayString(String msg) {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("処理を選択してください").append("\n");
        sb.append(msg);
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * 修正する情報を取得
     * 
     * @param toModifyPropertyMsg コンソールに表示する文言
     * @return 修正番号
     */
    private int getModifyUserInfo(final String toModifyPropertyMsg) {
        int propertyOfNumber = 0;
        do {
            // 入力値を取得
            propertyOfNumber = Util.inputInt(toModifyPropertyMsg);

        } while (Util.isOutOfRange(propertyOfNumber, Util.RANGE_MIN, Bank.values().length));
        return propertyOfNumber;
    }

    /**
     * 修正する属性表示文言作成
     * 
     * @param modifyName 修正する人物名称
     * @return 表示文言
     */
    private String displayToCorrectProperty(final String correctName) {

        StringBuffer sb = new StringBuffer();

        sb.append(correctName + "さんを修正します。").append("\n");
        sb.append("どの情報を修正しますか？").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(String.format(Util.DISPLAY_FORMAT_OF_PERSONAL_LIST, Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST)).append(".").append(Util.BACK)
                .append("\n");
        sb.append(Bank.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }

    /**
     * 口座番号新規採番
     * 
     * @return 新規採番番号
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private int createNewAccountNo() throws FileWriteException, FileReadException, IOException {

        FileProcessing fp = new FileProcessing();
        // 採番用のファイルを読み込み
        // +1する
        int nextAccountNo = sumUpAccountNo(fp.getAccountNo());

        // 採番した番号を採番用のファイルに書き込み
        fp.createFile(false, null, nextAccountNo);

        // 採番した番号を返す
        return nextAccountNo;

    }

    /**
     * 口座番号加算処理
     * 
     * @param accountNo 口座番号
     * @return 加算した口座番号
     */
    private int sumUpAccountNo(String accountNo) {
        // 文字から数値に変換
        int retVlalue = Integer.parseInt(accountNo);
        // 加算
        return ++retVlalue;

    }

    /**
     * 入力された氏名を取得
     * 
     * @return 氏名
     */
    private String inputName() {
        return Util.inputStr("氏名を入力してください");
    }
}
