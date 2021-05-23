package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

public class AccountProcessing {

    /** 想定外のメッセージ */
    private static final String UNEXPECTED_ERR = "想定された処理はありません。システム管理者に連絡してください。";
//    /** 人物リスト表示形式 */
//    public static final String DISPLAY_FORMAT_OF_PERSONAL_LIST = "%2d";
//    /** 属性リスト表示形式 */
//    public static final String DISPLAY_FORMAT_OF_PERSONAL_ATTRIBUTE_LIST = "%2d";
//    /** 属性リスト開始番号 */
//    public static final int START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST = 0;
//    /** 前に戻るのメッセージ */
//    private static final String BACK = "前に戻る";

    /** 選択肢の最大値 */
    private static final int RANGE_MAX = 4;

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
     * 口座操作の処理
     * 
     * @param personalList ユーザー情報リスト
     */
    public void changeAccountInfo(List<Personal> personalList) {

        do {

            // 修正する人物表示文言作成
            String toModifyPersonMsg = Util.displayToCorrectPerson(personalList, "どのユーザの処理をしますか？");

            // 修正する人物の番号を取得
            int personOfNumber = Util.getCorrectPerson(toModifyPersonMsg, personalList);

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
                            deposit.depositMoney(idx, personalList);
                            break;
                        case TRANSFER:
                            // 振込処理
                            transfer.transferMoney(personal, personalList);
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

        } while (Util.isOutOfRange(propertyOfNumber, Util.RANGE_MIN, RANGE_MAX));
        return propertyOfNumber;
    }

//    /**
//     * 入力数値範囲外チェック
//     * 
//     * @param number 入力された数値
//     * @param min    最小値
//     * @param max    最大値
//     * @return 入力された数値が範囲外の場合はTrue。範囲内の場合はfalse。
//     */
//    private static boolean isOutOfRange(final int number, final int min, final int max) {
//
//        // 範囲外の場合
//        if (!Util.isWithinRange(number, min, max)) {
//            System.out.println(String.format(WITHIN_RANGE, min, max));
//            return true;
//        }
//        return false;
//    }

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
        sb.append(String.format(Util.DISPLAY_FORMAT_OF_PERSONAL_LIST, Util.START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST)).append(".").append(Util.BACK)
                .append("\n");
        sb.append(Bank.getSelectBankString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }
}
