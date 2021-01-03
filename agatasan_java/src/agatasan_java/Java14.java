package agatasan_java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Java11クラス Java課題１２(オブジェクト指向３）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 */
public class Java14 {

    // --------------------------------------------------
    // メンバ変数
    // --------------------------------------------------
    /** スキャナー（コンソール入力） */
    private static Scanner mScanner = new Scanner(System.in);

    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 入力カーソル */
    private static final String CURSOL = ">";
    /** 処理継続 */
    private static final String PROCESSING_CONTINUE = "Y";
    /** 処理終了 */
    private static final String PROCESSING_END = "N";
    /** 日付形式 ：yyyyMMdd */
    private static final String DATE_OF_BIRTH = "yyyyMMdd";
    /** 日付形式 ：yyyy年MM月dd日 */
    private static final String DATE_OF_STANDARD_BIRTH = "yyyy年MM月dd日";
    /** データなしのメッセージ */
    private static final String NO_DATA = "表示するデータがありません。";
    /** 範囲内のメッセージ */
    private static final String WITHIN_RANGE = "%d～%dの範囲で入力してください。";
    /** 想定外のメッセージ */
    private static final String UNEXPECTED_ERR = "想定された処理はありません。システム管理者に連絡してください。";
    /** 前に戻るのメッセージ */
    private static final String BACK = "0.前に戻る";
    /** 選択肢の最小値 */
    private static final int RANGE_MIN = 0;
    /** 選択肢の最大値 */
    private static final int RANGE_MAX = 4;

    /** ファイル出力パス */
    private static final String ACCOUNT_FILE_OUTPUT_PATH = "ACCOUNT_FILE_OUTPUT_PATH";
    /** プロパティ設定パス */
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";
    /** ファイル保存識別子 */
    private static final String SAVE_IDENTIFIER = "'";
    /** ファイル区切り識別子 */
    private static final String SAVA_SEPARATION = ",";
    /** エスケープ識別子 */
    private static final char SAVA_ESCAPE = '\\';

    /**
     * 口座処理
     */
    private static enum Account {

        NEW(1, "口座を新規作成"), CHANGE(2, "すでにある口座を取り扱う");

        /** id */
        private final int id;
        /** 名称 */
        private final String name;

        /**
         * コンストラクタ
         * 
         * @param id
         */
        private Account(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * 口座処理表示文字列取得
         * 
         * @return 口座処理表示文字列
         */
        public static String getSelectAccountString() {
            final StringBuffer sb = new StringBuffer();
            for (final Account t : Account.values()) {
                sb.append(t.id).append(".").append(t.name).append("\n");
            }
            return sb.toString();
        }

        /**
         * 口座処理取得
         * 
         * @param inputAccount入力値
         * @return 口座処理が存在しない場合はnull値
         */
        public static Account convertAccount(final String inputAccount) {
            if (inputAccount == null) {
                return null;
            }

            int account = 0;
            try {
                account = Integer.parseInt(inputAccount);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Account t : Account.values()) {
                if (t.id == account) {
                    return t;
                }
            }
            return null;
        }

    }

    /**
     * 処理モード
     */
    private static enum Bank {

        DEPOSIT(1, "入金"), TRANSFER(2, "振込"), BALANCE(3, "残高表示"), HISTORY(9, "お取引履歴表示");

        /** id */
        private final int id;
        /** 名称 */
        private final String name;

        /**
         * コンストラクタ
         * 
         * @param id
         */
        private Bank(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * モード表示文字列取得
         * 
         * @return モード表示文字列
         */
        public static String getSelectBankString() {
            final StringBuffer sb = new StringBuffer();
            for (final Bank t : Bank.values()) {
                sb.append(t.id).append(".").append(t.name).append("\n");
            }
            return sb.toString();
        }

        /**
         * モード取得
         * 
         * @param inputBank入力値
         * @return モードが存在しない場合はnull値
         */
        public static Bank convertBank(final String inputBank) {
            if (inputBank == null) {
                return null;
            }

            int bank = 0;
            try {
                bank = Integer.parseInt(inputBank);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Bank t : Bank.values()) {
                if (t.id == bank) {
                    return t;
                }
            }
            return null;
        }

    }

    /**
     * 入力された処理モードの処理を表示します。
     * 
     */
    public static void main(String[] args) {

        try {

            List<Personal> personalList = getUserInfo();
            do {
                // --------------------------------------------------
                // 入力
                // --------------------------------------------------

                // 新規・既存かの処理モード入力
                final Account account = inputAccount();

                // 新規作成の場合
                if (account == Account.NEW) {
                    personalList.add(inputUserInfo());
                }

                // ユーザー選択

                // 修正する人物表示文言作成
                String toModifyPersonMsg = displayToCorrectPerson(personalList, "どのユーザの処理をしますか？");

                // 修正する人物の番号を取得
                int personOfNumber = getCorrectPerson(toModifyPersonMsg, personalList);

                // 0の場合は、最初に戻る
                if (personOfNumber == 0) {
                    continue;
                }

                int idx = personOfNumber - 1;

                // 個別の処理
                changePersonal(personalList, idx);

            } while (isContinue());

            // 次回再開時用にユーザー情報をファイルに保存
            createFile(personalList);

        } catch (FileWriteException | FileReadException | IOException e) {
            System.out.println("処理を中断しました。システム管理者へ問い合わせしてください。");
        }

        // 終了処理
        mScanner.close();
        System.out.println("処理を終了しました。");

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------

    private static void changePersonal(List<Personal> personalList, int idx) {

        // 入力された番号に紐づく名前を取得
        String modifyName = personalList.get(idx).getName();
        Personal personal = personalList.get(idx);

        do {

            // 修正する属性の番号を取得
            String toCorrectPropertyMsg = displayToCorrectProperty(modifyName);

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
                    personal.setBalance(depositMoney(personal.getBalance()));
                    break;
                case TRANSFER:
                    // 振込処理
//                    personalList = modifyUserInfo(personalList);
                    break;
                case BALANCE:
                    // 残高表示処理
                    displayBalance(personal.getBalance());
                    break;
                case HISTORY:
                    // お取引履歴表示
                    // TODO
                    displayHistory(personal.getAccountNumber());
                    break;
                default:
                    System.out.println(UNEXPECTED_ERR);
                    break;
            }

        } while (true);

    }

    private static void displayBalance(int balance) {
        System.out.println(String.format("残高は、%,d円です", balance));
    }

    private static void displayHistory(String accountNumber) {

        // 履歴データ取得

        // 履歴の中で口座番号一致のものを取得

        // 表示

    }

    /**
     * 前回のユーザー情報を取得
     * 
     * @return 前回入力したユーザー情報
     * @throws FileReadException
     * @throws IOException
     */
    public static List<Personal> getUserInfo() throws FileReadException, IOException {

        List<Personal> list = new ArrayList<>();

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo();

            File file = new File(strPass);

            // ファイルが存在しない場合(=処理1回目の場合)
            if (!file.exists()) {
                return list;
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {

                List<String> splitList = fileSplit(str);

                Personal line = new Personal();

                line.setAccountNumber(splitList.get(0));
                line.setName(splitList.get(1));
                line.setBalance(Integer.parseInt(splitList.get(2)));

                list.add(line);
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(e, "前回のユーザー情報を保存したファイルを読み込めませんでした。");

        } finally {

            if (br != null) {
                // 閉じる処理
                br.close();
            }
        }
        return list;
    }

    /**
     * ファイルの1行分をユーザー名と金額に分割する
     * 
     * @param line 読み込み行
     * @return 分割後のリスト。1つ目が口座番号、2つ目がユーザー名、3つ目が残高。
     */
    private static List<String> fileSplit(final String line) {
        char c;
        StringBuilder sb = new StringBuilder();
        List<String> data = new ArrayList<String>();
        boolean singleQuoteFlag = false;
        boolean singleQuoteKeepingFlag = false;
        char[] separation = SAVA_SEPARATION.toCharArray();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();

        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (c == identifier[0] && singleQuoteFlag && singleQuoteKeepingFlag) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
                sb.append(c);
            } else if (c == separation[0] && !singleQuoteFlag) {
                data.add(sb.toString());
                sb.delete(0, sb.length());
            } else if (c == separation[0] && singleQuoteFlag) {
                sb.append(c);
            } else if (c == identifier[0]) {
                singleQuoteFlag = !singleQuoteFlag;
            } else if (c == SAVA_ESCAPE) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
            } else {
                sb.append(c);
            }

            // 金額を設定
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

    /**
     * プロパティファイルの値を取得
     * 
     * @return ユーザー情報が記載されてファイルの格納パス
     * @throws FileNotFoundException
     * @throws FileReadException
     * @throws IOException
     */
    private static String getPropertiesInfo() throws FileNotFoundException, FileReadException, IOException {
        Properties properties = new Properties();

        String strPass = null;
        try {
            InputStream istream = new FileInputStream(INIT_PROPERTIES_PATH);
            properties.load(istream);

            strPass = properties.getProperty(ACCOUNT_FILE_OUTPUT_PATH);

            // 設定ファイル読み込み失敗時
            if (strPass == null) {
                throw new FileReadException(new Exception(), "プロパティファイルに記載されている定義が見つかりませんでした。");
            }

        } catch (FileNotFoundException e) {
            throw new FileReadException(e, "プロパティファイルが見つかりませんでした。");
        } catch (IOException e) {
            throw new FileReadException(e, "プロパティファイルの読み込みに失敗しました。");

        }
        return strPass;
    }

    /**
     * ユーザー情報をファイルに保存
     * 
     * @param list ユーザー情報
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void createFile(final List<Personal> list) throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo();

            FileWriter fw = new FileWriter(strPass);
            bw = new BufferedWriter(fw);

            // 口座番号、ユーザー名、金額をカンマ区切りで連結
            for (Personal p : list) {
                String str = String.format("%s%s%s%s%s%s%s%s%d%s", SAVE_IDENTIFIER, p.getAccountNumber(), SAVE_IDENTIFIER, SAVE_IDENTIFIER,
                        conversionEscape(p.getName()), SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, p.getBalance(), SAVE_IDENTIFIER);
                // 書き込み
                bw.write(str);
                // 改行
                bw.newLine();
            }

        } catch (IOException e) {
            throw new FileWriteException(e, String.format("ファイルの書き込みに失敗しました。ファイル名:%s", strPass));

        } finally {

            if (bw != null) {
                // 閉じる処理
                bw.close();
            }
        }

    }

    /**
     * 処理選択表示文言取得
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
     * 処理入力
     */
    private static Account inputAccount() {
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
     * 処理入力
     */
    private static Bank inputBank() {
        Bank bank = null;
        String displayMsg = displayToCorrectProperty(Bank.getSelectBankString());
        do {
            bank = Bank.convertBank(inputStr(displayMsg));
            if (bank == null) {
                System.out.println("該当する処理が見つかりませんでした。");
            }

        } while (bank == null);
        return bank;
    }

//    /**
//     * モード選択表示文言取得
//     */
//    private static String getDisplayModeString() {
//        final StringBuffer sb = new StringBuffer();
//        sb.append("***********************************").append("\n");
//        sb.append("処理モードを選択してください").append("\n");
//        sb.append(Mode.getSelectModeString());
//        sb.append("***********************************");
//        return sb.toString();
//    }
//
//    /**
//     * モード入力
//     */
//    private static Mode inputMode() {
//        Mode mode = null;
//        String displayMsg = getDisplayModeString();
//        do {
//            mode = Mode.convertMode(inputStr(displayMsg));
//            if (mode == null) {
//                System.out.println("該当する処理モードが見つかりませんでした。");
//            }
//
//        } while (mode == null);
//        return mode;
//    }

    /**
     * 処理継続確認
     * 
     * @return isProcessingContinue 処理継続はTrue。処理終了はfalse。
     */
    private static boolean isContinue() {
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
     * 文字入力
     * 
     * @param inputMsg 入力コンソール
     * @return 入力値
     */
    private static String inputStr(final String inputMsg) {
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
     * ユーザー情報の取得
     * 
     * @return ユーザー情報
     */
    private static Personal inputUserInfo() {

        // 氏名を取得
        String inputName = inputName();
        // 金額の取得
        int inputDeposit = depositMoney(0);
        // 口座番号は新規採番
        // TODO
        String balance = "001";

        // 値を設定
        Personal personal = new Personal(inputName, balance, inputDeposit);

        System.out.println("口座を新規登録しました。");

        return personal;
    }

    private static int depositMoney(int balance) {
        int inputDeposit = 0;
        do {
            // 入力値を取得
            inputDeposit = inputDeposit();

        } while (isOutOfRange(inputDeposit, 1, 10000000));

        // 残高の合計
        displayBalance(inputDeposit + balance);

        // TODO 入金履歴

        return inputDeposit;
    }

    /**
     * 入力数値範囲外チェック
     * 
     * @param number 入力された数値
     * @param min    最小値
     * @param max    最大値
     * @return 入力された数値が範囲外の場合はTrue。範囲内の場合はfalse。
     */
    public static boolean isOutOfRange(final int number, final int min, final int max) {

        // 範囲外の場合
        if (!isWithinRange(number, min, max)) {
            System.out.println(String.format(WITHIN_RANGE, min, max));
            return true;
        }
        return false;
    }

    /**
     * 入力された日付が存在するかのチェック
     * 
     * @param birthDay 入力された年月日
     * @return 正当な日付はTrue。不正な日付はfalse。
     */
    private static boolean isConsistency(String birthDay) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH);
        sdf.setLenient(false);
        try {
            sdf.parse(birthDay);
        } catch (ParseException e) {
            System.out.println("正しい日付ではありません");
            return false;
        }
        return true;
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
        sb.append(BACK).append("\n");

        int cnt = 1;
        for (Personal u : userList) {
            sb.append(cnt++).append(".").append(u.getName()).append("\n");
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
        sb.append(BACK).append("\n");
        sb.append(Personal.getSelectPersonalString());
        sb.append("---------------------------").append("\n");

        return sb.toString();

    }

    /**
     * 数値入力(int型)
     * 
     * @param inputMsg 入力コンソールに表示する文言
     * @return 入力値
     */
    private static int inputInt(final String inputMsg) {
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
     * 入力された氏名を取得
     * 
     * @return 氏名
     */
    private static String inputName() {
        return inputStr("氏名を入力してください");
    }

    /**
     * 入力された金額を取得
     * 
     * @return 金額
     */
    private static int inputDeposit() {
        return inputInt("入金金額を入力してください");
    }

    /**
     * 訂正する対象ユーザーを取得
     * 
     * @param toCorrectPersonMsg コンソールに表示する文言
     * @param userList           ユーザーリスト
     * @return 訂正するユーザー番号
     */
    private static int getCorrectPerson(final String toCorrectPersonMsg, final List<Personal> userList) {
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
    private static int getModifyUserInfo(final String toModifyPropertyMsg) {
        int propertyOfNumber = 0;
        do {
            // 入力値を取得
            propertyOfNumber = inputInt(toModifyPropertyMsg);

        } while (isOutOfRange(propertyOfNumber, RANGE_MIN, RANGE_MAX));
        return propertyOfNumber;
    }

    /**
     * userListが空かの判定を行う
     * 
     * @param userList ユーザーリスト
     * @return 渡されたリストが空の場合はTrue。空でない場合はfalse。
     */
    private static boolean isEmpty(final List<User> userList) {

        if (userList == null || userList.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 入力値をエスケープ処理をして返す
     * 
     * @param input 入力値
     * @return エスケープ処理
     */
    private static String conversionEscape(final String input) {
        char c;
        StringBuilder sb = new StringBuilder();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == identifier[0]) {
                sb.append(SAVA_ESCAPE);
            }
            sb.append(c);

        }
        return sb.toString();
    }

}
