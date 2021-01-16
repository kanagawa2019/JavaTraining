package agatasan_java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Java11クラス Java課題１２(オブジェクト指向３）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 */
public class Java12 {

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

    /**
     * 処理モード
     */
    private static enum Mode {

        ENTRY(1, "ユーザ登録"), MODIFY(2, "ユ－ザー修正"), DELETE(3, "ユ－ザー削除"), ALL(9, "全ユーザ表示");

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
                case MODIFY:
                    // 修正処理
                    userList = modifyUserInfo(userList);
                    break;
                case DELETE:
                    // 削除処理
                    userList = deleteUserInfo(userList);
                    break;
                case ALL:
                    // 全体表示
                    getDisplayUserInfo(userList);
                    break;
                default:
                    System.out.println(UNEXPECTED_ERR);
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
    private static User inputUserInfo() {

        // 氏名を取得
        String inputName = inputName();
        // 性別の取得
        User.Sex sex = inputSex();
        // 誕生日の取得
        Date birthday = inputBirthday();
        // 得意言語の取得
        String inputLanguage = inputLanguage();

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
     * ユーザー情報表示
     * 
     * @param userList ユーザー情報リスト
     */
    private static void getDisplayUserInfo(List<User> userList) {

        // userListが空の場合
        if (userList == null || userList.size() == 0) {
            System.out.println(NO_DATA);
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
    private static int calcAge(Date formatDate) {
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
    private static Date StringToDate(String date) {
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

    /**
     * ユーザー情報の修正
     * 
     * @param userList ユーザー情報リスト
     * @return ユーザー情報リスト
     */
    private static List<User> modifyUserInfo(final List<User> userList) {

        // userListが空の場合
        if (isEmpty(userList)) {
            System.out.println(NO_DATA);
            return userList;
        }

        do {
            // 修正する人物表示文言作成
            String toModifyPersonMsg = displayToCorrectPerson(userList, "どのユーザを修正しますか？");

            // 修正する人物の番号を取得
            int personOfNumber = getCorrectPerson(toModifyPersonMsg, userList);

            // 0の場合は、処理モードに戻る
            if (personOfNumber == 0) {
                return userList;
            }

            int idx = personOfNumber - 1;
            // 入力された番号に紐づく名前を取得
            String modifyName = userList.get(idx).getName();
            do {

                // 修正する属性の番号を取得
                String toModifyPropertyMsg = displayToModifyProperty(modifyName);

                // 修正するユーザー情報番号を取得
                int propertyOfNumber = getModifyUserInfo(toModifyPropertyMsg);

                // 0 の場合、修正人物を選択する処理まで戻る
                if (propertyOfNumber == 0) {
                    break;
                }

                // 修正処理
                switch (propertyOfNumber) {
                    case User.NUMBER_NAME:
                        String newName = inputName();
                        userList.get(idx).setName(newName);
                        modifyName = newName;
                        break;
                    case User.NUMBER_SEX:
                        userList.get(idx).setSex(inputSex());
                        break;
                    case User.NUMBER_BIRTHDAY:
                        userList.get(idx).setBirthday(inputBirthday());
                        break;
                    case User.NUMBER_FAVORITELANGUAGE:
                        userList.get(idx).setFavoriteLanguage(inputLanguage());
                        break;
                    default:
                        System.out.println(UNEXPECTED_ERR);
                        break;

                }
            } while (true);

        } while (true);
    }

    /**
     * 修正する人物表示文言作成
     * 
     * @param userList ユーザー情報リスト
     * @param msg
     * @return 表示文言
     */
    private static String displayToCorrectPerson(final List<User> userList, final String msg) {

        System.out.println(msg);

        // 操作文言表示
        StringBuffer sb = new StringBuffer();
        sb.append("---------------------------").append("\n");
        sb.append(BACK).append("\n");

        int cnt = 1;
        for (User u : userList) {
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
    private static String displayToModifyProperty(final String modifyName) {

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("%Sさんを修正します。", modifyName)).append("\n");
        sb.append("どの情報を修正しますか？").append("\n");
        sb.append("---------------------------").append("\n");
        sb.append(BACK).append("\n");
        sb.append(User.getSelectPropertyString());
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
     * 入力された性別を取得
     * 
     * @return 性別
     */
    private static User.Sex inputSex() {
        return inputSex(String.format("性別を入力してください(%S)", User.Sex.getSelectSexString()));
    }

    /**
     * 入力された誕生日を取得
     * 
     * @return 誕生日
     */
    private static Date inputBirthday() {
        return inputBirthday(String.format("生年月日を入力してください(%s)", DATE_OF_BIRTH.toLowerCase()));
    }

    /**
     * 入力された得意言語を取得
     * 
     * @return 得意言語
     */
    private static String inputLanguage() {
        return inputStr("得意言語を入力してください");
    }

    /**
     * 訂正する対象ユーザーを取得
     * 
     * @param toCorrectPersonMsg コンソールに表示する文言
     * @param userList           ユーザーリスト
     * @return 訂正するユーザー番号
     */
    private static int getCorrectPerson(final String toCorrectPersonMsg, final List<User> userList) {
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
     * ユーザー情報の削除
     * 
     * @param userList ユーザーリスト
     * @return ユーザーリスト
     */
    private static List<User> deleteUserInfo(final List<User> userList) {

        // userListが空の場合
        if (isEmpty(userList)) {
            System.out.println(NO_DATA);
            return userList;
        }

        do {
            // 削除する人物表示文言作成
            String toCorrectPersonMsg = displayToCorrectPerson(userList, "どのユーザを削除しますか？");

            // 訂正する人物の番号を取得
            int personOfNumber = getCorrectPerson(toCorrectPersonMsg, userList);

            // 0の場合は、処理モードに戻る
            if (personOfNumber == 0) {
                return userList;
            }

            int idx = personOfNumber - 1;
            // 入力された番号に紐づく名前を取得
            String deleteName = userList.get(idx).getName();

            // 削除処理
            deleteUser(userList, deleteName, idx);

        } while (true);

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
     * ユーザーリストから削除する
     * 
     * @param userList   ユーザーリスト
     * @param deleteName 削除する人の名前
     * @param idx        インデックス
     */
    private static void deleteUser(List<User> userList, final String deleteName, final int idx) {

        System.out.println(String.format("%Sさんをこの世から削除しました。", deleteName));
        userList.remove(idx);
    }
}
