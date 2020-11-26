package agatasan_java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Java10クラス Java課題１０(オブジェクト指向１）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/11/03 新規作成
 * @version 1.1 2020/11/07 No.60～63指摘対応
 * @version 1.2 2020/11/14 REV2回目指摘対応
 * @version 1.3 2020/11/21 REV3回目指摘対応
 * @version 1.4 2020/11/27 No.74再指摘対応
 */
public class Java10 {

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

    /**
     * 処理モード
     */
    private static enum Mode {

        ENTRY(1, "ユーザ登録"), ALL(9, "全ユーザ表示");

        /** id */
        private final int id;
        /** 名称 */
        private final String name;

        /**
         * コンストラクタ
         * 
         * @param id
         */
        private Mode(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * モード表示文字列取得
         * 
         * @return モード表示文字列
         */
        public static String getSelectModeString() {
            final StringBuffer sb = new StringBuffer();
            for (final Mode t : Mode.values()) {
                sb.append(t.id).append(".").append(t.name).append("\n");
            }
            return sb.toString();
        }

        /**
         * モード取得
         * 
         * @param inputMode入力値
         * @return モードが存在しない場合はnull値
         */
        public static Mode convertMode(final String inputMode) {
            if (inputMode == null) {
                return null;
            }

            int mode = 0;
            try {
                mode = Integer.parseInt(inputMode);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Mode t : Mode.values()) {
                if (t.id == mode) {
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

        List<User> userList = new ArrayList<User>();
        do {
            // --------------------------------------------------
            // 入力
            // --------------------------------------------------
            // 処理モード入力
            final Mode mode = inputMode();
            // --------------------------------------------------
            // 処理・出力
            // --------------------------------------------------

            switch (mode) {
                case ENTRY:
                    // 登録処理
                    userList.add(inputUserInfo());
                    break;
                case ALL:
                    // 全体表示
                    getDisplayUserInfo(userList);
                    break;
                default:
                    System.out.println("想定された処理はありません。システム管理者に連絡してください。");
                    break;
            }

        } while (isContinue());

        // 終了処理
        mScanner.close();
        System.out.println("処理を終了しました。");

    }

    // --------------------------------------------------
    // private関数
    // --------------------------------------------------
    /**
     * モード選択表示文言取得
     */
    private static String getDisplayModeString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("***********************************").append("\n");
        sb.append("処理モードを選択してください").append("\n");
        sb.append(Mode.getSelectModeString());
        sb.append("***********************************");
        return sb.toString();
    }

    /**
     * モード入力
     */
    private static Mode inputMode() {
        Mode mode = null;
        String displayMsg = getDisplayModeString();
        do {
            mode = Mode.convertMode(inputStr(displayMsg));
            if (mode == null) {
                System.out.println("該当する処理モードが見つかりませんでした。");
            }

        } while (mode == null);
        return mode;
    }

    /**
     * 性別入力
     */
    private static User.Sex inputSex(String msg) {
        User.Sex sex = null;

        do {
            sex = User.Sex.convertSex(inputStr(msg));
            if (sex == null) {
                System.out.println("該当する性別が見つかりませんでした。");
            }

        } while (sex == null);
        return sex;
    }

    /**
     * 処理継続確認
     * 
     * @return isProcessingContinue 処理継続はTrue。処理終了はfalse。
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
     * ユーザー情報の取得
     * 
     * @return ユーザー情報
     */
    public static User inputUserInfo() {

        // 氏名を取得
        String inputName = inputStr("氏名を入力してください");
        // 性別の取得
        User.Sex sex = inputSex(String.format("性別を入力してください(%S)", User.Sex.getSelectSexString()));
        // 誕生日の取得
        Date birthday = inputBirthday(String.format("生年月日を入力してください(%s)", DATE_OF_BIRTH.toLowerCase()));
        // 得意言語の取得
        String inputLanguage = inputStr("得意言語を入力してください");

        // 値を設定
        User user = new User(inputName, sex, birthday, inputLanguage);

        return user;
    }

    /**
     * 入力された日付が存在するかのチェック
     * 
     * @param birthDay 入力された年月日
     * @return 正当な日付はTrue。不正な日付はfalse。
     */
    public static boolean isConsistency(String birthDay) {

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
     * ユーザー情報表示
     * 
     * @param userList ユーザー情報リスト
     */
    private static void getDisplayUserInfo(List<User> userList) {

        // userListが空の場合
        if (userList == null || userList.size() == 0) {
            System.out.println("表示するデータがありません。");
            return;
        }

        for (User ur : userList) {
            System.out.println("---------------------------");
            System.out.println(String.format("氏　　名：%S", ur.getName()));
            System.out.println(String.format("性　　別：%S", User.Sex.convertSexNameById(ur.getSex().getId())));
            System.out.println(String.format("生年月日：%S(%d歳)", conversionBirthday(ur.getBirthday()), calcAge(ur.getBirthday())));
            System.out.println(String.format("得意言語：%S", ur.getFavoriteLanguage()));
            System.out.println("---------------------------");
        }
    }

    /**
     * 生年月日に年月日をつけて返す
     * 
     * @param birthday 生年月日
     * @return yyyy年MM月dd日
     */
    private static String conversionBirthday(Date birthday) {

        // 日付から年月日に変換
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_STANDARD_BIRTH);
        return sdf.format(birthday);

    }

    /**
     * 誕生日から年齢を計算して返す
     * 
     * @param formatDate 誕生日
     * @return 年齢
     */
    public static int calcAge(Date formatDate) {
        Date dateObj = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH);
        return (Integer.parseInt(sdf.format(dateObj)) - Integer.parseInt(sdf.format(formatDate))) / 10000;
    }

    /**
     * 文字列の日付をDate型の日付に変換する
     * 
     * @param date 文字列の日付
     * @return Date型の日付
     */
    public static Date StringToDate(String date) {
        if (date == null) {
            return null;
        }
        // 文字列から日付型変換
        SimpleDateFormat tempSdf = new SimpleDateFormat(DATE_OF_BIRTH);
        Date formatDate = new Date();
        try {
            formatDate = tempSdf.parse(date);
        } catch (ParseException e) {
            // 入力時にチェックしているのでありえない
        }
        return formatDate;
    }

    /**
     * 誕生日入力
     */
    private static Date inputBirthday(String msg) {
        String birthdayStr = null;
        do {
            birthdayStr = inputStr(msg);
        } while (!isConsistency(birthdayStr));

        Date date = StringToDate(birthdayStr);

        return date;
    }
}
