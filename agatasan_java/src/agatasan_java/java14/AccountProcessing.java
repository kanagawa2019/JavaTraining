package agatasan_java.java14;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

public class AccountProcessing {
    /** スキャナー（コンソール入力） */
    private Scanner mScanner = new Scanner(System.in);

    /** 日付形式 ：yyyy年MM月dd日 */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy年MM月dd日";
    /** 入力カーソル */
    private static final String CURSOL = ">";
    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";
    /** 範囲内のメッセージ */
    private static final String WITHIN_RANGE = "%d～%dの範囲で入力してください。";
    /** 想定外のメッセージ */
    private static final String UNEXPECTED_ERR = "想定された処理はありません。システム管理者に連絡してください。";
    /** 人物リスト表示形式 */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_LIST = "%2d";
    /** 属性リスト表示形式 */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_ATTRIBUTE_LIST = "%2d";
    /** 属性リスト開始番号 */
    public static final int START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST = 0;
    /** 前に戻るのメッセージ */
    private static final String BACK = "前に戻る";
    /** 選択肢の最小値 */
    private static final int RANGE_MIN = 0;
    /** 選択肢の最大値 */
    private static final int RANGE_MAX = 4;

    /**
     * 口座の処理入力
     */
    public Account inputAccount() {
        Account account = null;
        String displayMsg = getDisplayString(Account.getSelectAccountString());
        do {
            account = Account.convertAccount(inputStr(displayMsg));
            if (account == null) {
                System.out.println("該当する処理が見つかりませんでした。");
            }

        } while (account == null);
        return account;
    }

    /**
     * 処理選択表示文言取得
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
     * 残高表示
     * 
     * @param balance 残高
     */
    private void displayBalance(final int balance) {
        System.out.println(String.format("残高は、%,d円です", balance));
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
        depositMoney(personalList.size() - 1, personalList);

        System.out.println("口座を新規登録しました。");

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
        fp.createAccountNoFile(nextAccountNo);

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
     * 口座操作の処理
     * 
     * @param personalList ユーザー情報リスト
     */
    public void changeAccountInfo(List<Personal> personalList) {

        do {

            // 修正する人物表示文言作成
            String toModifyPersonMsg = displayToCorrectPerson(personalList, "どのユーザの処理をしますか？");

            // 修正する人物の番号を取得
            int personOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

            // 0の場合は、最初に戻る
            if (personOfNumber == 0) {
                return;
            }

            int idx = personOfNumber - 1;

            // 入力された番号に紐づく名前を取得
            Personal personal = personalList.get(idx);

            try {

                do {

                    // 修正する属性の番号を取得
                    String toCorrectPropertyMsg = displayToCorrectProperty(personal.getName());

                    // 修正するユーザー情報番号を取得
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
                            depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // 振込処理
                            transfer(personal, personalList);
                            break;
                        case BALANCE:
                            // 残高表示処理
                            displayBalance(personal.getBalance());
                            break;
                        case HISTORY:
                            // お取引履歴表示
                            displayHistory(personal.getAccountNumber());
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
     * 入金処理
     * 
     * @param depositIdx   選択されたインデックス
     * @param personalList ユーザー情報リスト
     * @return 入金後の残高
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private int depositMoney(final int depositIdx, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 入金対象
        Personal target = personalList.get(depositIdx);

        // 入金情報取得
        int inputDeposit = getDeposit();

        // 残高の合計
        int sum = inputDeposit + target.getBalance();
        displayBalance(sum);

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

        } while (isOutOfRange(inputDeposit, 1, 10000000));

        return inputDeposit;
    }

    /**
     * 振込処理
     * 
     * @param transfer     振込元
     * @param personalList ユーザー情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private void transfer(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            String toModifyPersonMsg = displayToCorrectPerson(personalList, "どのユーザに振込しますか？");

            // 振込先の人物の番号を取得
            int payeeOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

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
     * 訂正する対象ユーザーを取得
     * 
     * @param toCorrectPersonMsg コンソールに表示する文言
     * @param userList           ユーザー情報リスト
     * @return 訂正するユーザー番号
     */
    private int getCorrectPerson(final String toCorrectPersonMsg, final List<Personal> userList) {
        int personOfNumber = 0;
        do {
            // 訂正する人物の番号を取得
            personOfNumber = inputInt(toCorrectPersonMsg);

        } while (isOutOfRange(personOfNumber, RANGE_MIN, userList.size()));
        return personOfNumber;
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
            propertyOfNumber = inputInt(toModifyPropertyMsg);

        } while (isOutOfRange(propertyOfNumber, RANGE_MIN, RANGE_MAX));
        return propertyOfNumber;
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
        do {
            // 入力値を取得
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000) || canPay(transfer, inputDeposit));

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

    /**
     * お取引履歴表示
     * 
     * @param accountNumber 口座番号
     * @throws FileReadException
     * @throws IOException
     */
    private void displayHistory(final int accountNumber) throws FileReadException, IOException {

        // 履歴データ取得
        FileProcessing fp = new FileProcessing();
        List<AccountHistory> historyList = fp.getAccountHistory();

        // 履歴の中で口座番号一致のものを表示
        matchAccountNo(accountNumber, historyList);

    }

    /**
     * 表示対象口座番号と一致する履歴を表示
     * 
     * @param accountNumber 表示対象口座番号
     * @param historyList   取引履歴リスト
     */
    private void matchAccountNo(final int accountNumber, final List<AccountHistory> historyList) {

        StringBuffer sb = new StringBuffer();
        // 履歴あり
        Boolean isExistHistory = false;

        sb.append("***********************************").append("\n");
        sb.append("お取引日、区分、取引金額、残高").append("\n");

        for (AccountHistory history : historyList) {
            if (accountNumber == history.getAccountNumber()) {

                if (isExistHistory == false) {
                    isExistHistory = true;
                }

                sb.append(dateToString(history.getDate())).append("、").append(history.getClassification().getName()).append("、")
                        .append(String.format("%,d円", history.getTransactionAmount())).append("、").append(String.format("%,d円", history.getBalance()))
                        .append("\n");

            }

        }

        sb.append("***********************************");

        System.out.println(isExistHistory == true ? sb.toString() : "お取引履歴はありません。");
    }

    /**
     * 日付を文字列に変換
     * 
     * @param date 日付
     * @return 文字列型日付
     */
    private String dateToString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return dateFormat.format(date);
    }

    /**
     * 入力された氏名を取得
     * 
     * @return 氏名
     */
    private String inputName() {
        return inputStr("氏名を入力してください");
    }

    /**
     * 入力された金額を取得
     * 
     * @return 金額
     */
    private int inputDeposit() {
        return inputInt("入金金額を入力してください");
    }

    /**
     * 文字入力
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
     */
    private String inputStr(final String inputMsg) {
        String input = null;
        boolean isCheck = false;

        System.out.println(inputMsg);
        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                isCheck = true;
            } catch (Exception e) {
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }
        } while (!isCheck);
        return input;
    }

    /**
     * 数値入力(int型)
     * 
     * @param inputMsg 入力コンソールに表示する文言
     * @return 入力値
     */
    private int inputInt(final String inputMsg) {
        int num = 0;
        String input = null;
        boolean isCheck = false;

        if (inputMsg != null) {
            System.out.println(inputMsg);
        }

        do {
            try {
                System.out.print(CURSOL);
                input = mScanner.next();
                num = Integer.parseInt(input);
                isCheck = true;
            } catch (Exception e) {
                System.out.println("申し訳ありません。正しく処理が行えませんでした。\n再入力をお願いします。");
            }

        } while (!isCheck);
        return num;
    }

    /**
     * 処理継続確認
     * 
     * @return 処理継続はTrue。処理終了はfalse。
     */
    public boolean isContinue() {
        boolean isCheck = false;

        String displayMsg = String.format("処理を続けますか？(%s/%s)", PROCESSING_CONTINUE, PROCESSING_END);

        do {
            String input = inputStr(displayMsg);

            // 入力値の判定
            switch (input.toUpperCase()) {
                case PROCESSING_CONTINUE:
                    return true;
                case PROCESSING_END:
                    return false;
                default:
                    System.out.println(String.format("(%s/%s)以外が入力されました。\n再入力をお願いします。", PROCESSING_CONTINUE, PROCESSING_END));
                    break;
            }

        } while (!isCheck);

        return false;
    }

    /**
     * 入力数値範囲外チェック
     * 
     * @param number 入力された数値
     * @param min    最小値
     * @param max    最大値
     * @return 入力された数値が範囲外の場合はTrue。範囲内の場合はfalse。
     */
    private static boolean isOutOfRange(final int number, final int min, final int max) {

        // 範囲外の場合
        if (!isWithinRange(number, min, max)) {
            System.out.println(String.format(WITHIN_RANGE, min, max));
            return true;
        }
        return false;
    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return true:最小値～最大値の範囲内にある
     */
    private static boolean isWithinRange(final int targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 修正する人物表示文言作成
     * 
     * @param userList ユーザー情報リスト
     * @param msg
     * @return 表示文言
     */
    private static String displayToCorrectPerson(final List<Personal> userList, String msg) {

        System.out.println(msg);

        // 操作文言表示
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        int cnt = 0;
        sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, cnt)).append(".").append(BACK).append("\n");

        for (Personal u : userList) {
            sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, ++cnt)).append(".").append(u.getName()).append("\n");
        }
        sb.append("---------------------------").append("\n");

        return sb.toString();
    }

    /**
     * 修正する属性表示文言作成
     * 
     * @param modifyName 修正する人物名称
     * @return 表示文言
     */
    private static String displayToCorrectProperty(final String correctName) {

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("%Sさんを修正します。", correctName)).append("\n");
        sb.append("どの情報を修正しますか？").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST)).append(".").append(BACK).append("\n");
        sb.append(Bank.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }
}
