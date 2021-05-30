package agatasan_java.java14;

import java.util.List;
import java.util.Scanner;

/**
 * 共通処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 * @version 1.1 2021/05/26 No.109～113指摘対応
 *
 */
public class Util {
    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    public static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 入力カーソル */
    private static final String CURSOL = ">";
    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";
    /** 範囲内のメッセージ */
    private static final String WITHIN_RANGE = "%d～%dの範囲で入力してください。";
    /** 想定外のメッセージ */
    public static final String UNEXPECTED_ERR = "想定された処理はありません。システム管理者に連絡してください。";
    /** 前に戻るのメッセージ */
    public static final String BACK = "前に戻る";
    /** 人物リスト表示形式 */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_LIST = "%2d";
    /** 人物リスト開始番号 */
    public static final int START_NUMBER_OF_PERSONAL_LIST = 0;
    /** 属性リスト表示形式 */
    public static final String DISPLAY_FORMAT_OF_PERSONAL_ATTRIBUTE_LIST = "%2d";
    /** 属性リスト開始番号 */
    public static final int START_NUMBER_OF_PERSONAL_ATTRIBUTE_LIST = 0;
    /** 選択肢の最小値 */
    public static final int RANGE_MIN = 0;
    /** 口座保有限度額 */
    public static final long MAX_BALANCE = 9000000000000000000l;
    /** 入金下限金額 */
    private static final int MINIMUM_AMOUNT = 1;
    /** 入金上限金額 */
    private static final int MAXIMUM_AMOUNT = 10000000;

    // --------------------------------------------------
    // public関数
    // --------------------------------------------------
    /**
     * 文字入力
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
     */
    public static String inputStr(final String inputMsg) {
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
    public static int inputInt(final String inputMsg) {
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
     * 数値入力(long型)
     * 
     * @param inputMsg 入力コンソールに表示する文言
     * @return 入力値
     */
    public static long inputLong(final String inputMsg) {
        long num = 0;
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
    public static boolean isContinue() {
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
     * 終了処理
     */
    public static void scannerClose() {
        System.out.println("終了します。");
        mScanner.close();
    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return true:最小値～最大値の範囲内にある
     */
    public static boolean isWithinRange(final long targetNumber, final int minValue, final int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 入力数値範囲外チェック
     * 
     * @param number 入力された数値
     * @param min    最小値
     * @param max    最大値
     * @return 入力された数値が範囲外の場合はTrue。範囲内の場合はfalse。
     */
    public static boolean isOutOfRange(final long number, final int min, final int max) {

        // 範囲外の場合
        if (!Util.isWithinRange(number, min, max)) {
            System.out.println(String.format(WITHIN_RANGE, min, max));
            return true;
        }
        return false;
    }

    /**
     * 修正する人物表示文言作成
     * 
     * @param userList ユーザ情報リスト
     * @param msg
     * @return 表示文言
     */
    public static String displayToCorrectPerson(final List<Personal> userList, String msg) {

        System.out.println(msg);

        // 操作文言表示
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        int cnt = START_NUMBER_OF_PERSONAL_LIST;
        sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, cnt)).append(".").append(BACK).append("\n");

        for (Personal u : userList) {
            sb.append(String.format(DISPLAY_FORMAT_OF_PERSONAL_LIST, ++cnt)).append(".").append(u.getName()).append("\n");
        }
        sb.append("---------------------------").append("\n");

        return sb.toString();
    }

    /**
     * 訂正する対象ユーザを取得
     * 
     * @param toCorrectPersonMsg コンソールに表示する文言
     * @param userList           ユーザ情報リスト
     * @return 訂正するユーザ番号
     */
    public static int getCorrectPerson(final String toCorrectPersonMsg, final List<Personal> userList) {
        int personOfNumber = 0;
        do {
            // 訂正する人物の番号を取得
            personOfNumber = Util.inputInt(toCorrectPersonMsg);

        } while (Util.isOutOfRange(personOfNumber, RANGE_MIN, userList.size()));
        return personOfNumber;
    }

    /**
     * 入力された金額を取得
     * 
     * @return 金額
     */
    public static long inputMoney(final String process) {
        return Util.inputLong(String.format("%s金額を入力してください", process));
    }

    /**
     * リストから修正対象番号を取得
     * 
     * @param personalList ユーザ情報リスト
     * @param msg          コンソールに表示する文言
     * @return 修正番号
     */
    public static int getTargetNo(final List<Personal> personalList, String msg) {
        // 修正番号を取得
        return getCorrectPerson(displayToCorrectPerson(personalList, msg), personalList);
    }

    /**
     * 残高内で払えるかのチェック
     * 
     * @param personal     ユーザ情報
     * @param inputDeposit 振込金額
     * @return true:残高内で払える
     */
    public static boolean canPay(final Personal personal, final long inputDeposit) {

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
    public static boolean isMaxBalance(final long inputDeposit, final long balance) {

        if (Util.MAX_BALANCE < inputDeposit + balance) {
            System.out.println(String.format("入金は%,d円までしか受付られません。", Util.MAX_BALANCE - balance));
            return true;
        }
        return false;
    }

    public static long getInputMoneyInfo(final AccountHandlingMenu menu, final String msg, final Personal transfer, final Personal payee) {
        long inputDeposit = 0;
        do {
            // 入力値を取得
            inputDeposit = Util.inputMoney(msg);

        } while (isMatchCondition(menu, inputDeposit, transfer, payee));

        return inputDeposit;
    }

    private static boolean isMatchCondition(final AccountHandlingMenu menu, final long inputDeposit, final Personal transfer, final Personal payee) {

        if (menu == AccountHandlingMenu.DEPOSIT) {
            if (isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (Util.isMaxBalance(inputDeposit, payee.getBalance())) {
                return true;
            }
            return false;
        }

        if (menu == AccountHandlingMenu.TRANSFER) {
            if (isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (Util.canPay(transfer, inputDeposit)) {
                return true;
            } else if (Util.isMaxBalance(inputDeposit, payee.getBalance())) {
                return true;
            }
            return false;

        }

        if (menu == AccountHandlingMenu.WITHDRAW) {
            if (isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT)) {
                return true;
            } else if (Util.canPay(transfer, inputDeposit)) {
                return true;
            }
            return false;
        }
        return true;
    }

}
