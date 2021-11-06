package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * 口座処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 * @version 1.2 2021/05/30 No.110～122指摘対応
 * @version 1.3 2021/05/31 No.123～131指摘対応
 * @version 1.4 2021/06/01 No.126,128,130,131指摘対応
 * @version 1.5 2021/06/02 No.132～136指摘対応
 *
 */
public class AccountProcessing extends AccountService {

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------

    /**
     * 口座の処理入力
     */
    public static DepositBusiness inputAccount() {
        DepositBusiness account = null;
        String displayMsg = getDisplayString(DepositBusiness.getSelectAccountString());
        do {
            account = DepositBusiness.convertAccount(Util.inputStr(displayMsg));
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
    public static void changeAccountInfo(List<Personal> personalList) {

        do {

            // 取り扱う人物の番号を取得
            int personOfNumber = Util.getTargetNo(personalList, "どのユーザの処理をしますか？");

            // 0の場合は、最初に戻る
            if (personOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
                break;
            }

            int idx = personOfNumber - 1;

            // 入力された番号に紐づく名前を取得
            Personal personal = personalList.get(idx);

            try {

                do {
                    // 取り扱うユーザ情報番号を取得
                    int propertyOfNumber = getModifyUserInfo(displayToTreatProperty(personal.getName()));

                    // 0 の場合、人物を選択する処理まで戻る
                    if (propertyOfNumber == Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST) {
                        break;
                    }

                    // --------------------------------------------------
                    // 処理・出力
                    // --------------------------------------------------

                    switch (AccountHandlingMenu.convertBank(String.valueOf(propertyOfNumber))) {
                        case DEPOSIT:
                            // 入金処理
                            depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // 振込処理
                            TransferProcessiong.transferMoney(personal, personalList);
                            break;
                        case WITHDRAW:
                            // 出金処理
                            WithdrawProcessiong.withdrawMoney(idx, personalList);
                            break;
                        case BALANCE:
                            // 残高表示処理
                            displayBalance(personal.getBalance());
                            break;
                        case HISTORY:
                            // お取引履歴表示
                            BalanceProcessiong.displayHistory(personal.getAccountNumber());
                            break;
                        default:
                            System.out.println(Util.UNEXPECTED_ERR);
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
    public static void createAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 新規顧客を作成
        personalList.add(new Personal(
                inputName(),
                createNewAccountNo(),
                0)
                );

        // 口座を更新
        depositMoney(personalList.size() - 1, personalList);

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
    public static void releaseAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 取り扱う人物の番号を取得
        int personOfNumber = Util.getTargetNo(personalList, "どのユーザの処理をしますか？");

        // 0の場合は、最初に戻る
        if (personOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
            return;
        }

        int idx = personOfNumber - 1;

        // 廃止予定口座の残高出金
        Personal releaseTarget = personalList.get(idx);
        System.out.println(String.format("%,d円出金しました。", releaseTarget.getBalance()));

        // 履歴更新
        FileProcessing.writeHistory(releaseTarget.getAccountNumber(), AccountHandlingMenu.WITHDRAW.getId(), releaseTarget.getBalance(), 0);

        // リストから口座削除
        personalList.remove(idx);

        // 全件口座再作成
        FileProcessing.createFile(true, personalList, 0);

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
    private static String getDisplayString(String msg) {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("処理を選択してください").append("\n");
        sb.append(msg);
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * 取り扱う情報を取得
     * 
     * @param toModifyPropertyMsg コンソールに表示する文言
     * @return 対象番号
     */
    private static int getModifyUserInfo(final String toModifyPropertyMsg) {
        int propertyOfNumber = 0;
        do {
            // 入力値を取得
            propertyOfNumber = Util.inputInt(toModifyPropertyMsg);

        } while (Util.isOutOfRange(propertyOfNumber, Util.RANGE_MIN, AccountHandlingMenu.values().length));
        return propertyOfNumber;
    }

    /**
     * 取り扱う属性表示文言作成
     * 
     * @param modifyName 取り扱う人物名称
     * @return 表示文言
     */
    private static String displayToTreatProperty(final String correctName) {

        StringBuffer sb = new StringBuffer();

        sb.append(correctName + "さんを取り扱います。").append("\n");
        sb.append("どの情報を取り扱いますか？").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(String.format(Util.DISPLAY_FORMAT_OF_PERSONAL_LIST, Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST))
                       .append(".").append(Util.BACK).append("\n");
        sb.append(AccountHandlingMenu.getSelectBankString());
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
    private static int createNewAccountNo() throws FileWriteException, FileReadException, IOException {

        // 採番用のファイルを読み込み
        // +1する
        int nextAccountNo = sumUpAccountNo(FileProcessing.getAccountNo());

        // 採番した番号を採番用のファイルに書き込み
        FileProcessing.createFile(false, null, nextAccountNo);

        // 採番した番号を返す
        return nextAccountNo;

    }

    /**
     * 口座番号加算処理
     * 
     * @param accountNo 口座番号
     * @return 加算した口座番号
     */
    private static int sumUpAccountNo(String accountNo) {
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
    private static String inputName() {
        return Util.inputStr("氏名を入力してください");
    }
}
