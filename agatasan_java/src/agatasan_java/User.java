package agatasan_java;

import java.util.Date;

/**
 * ユーザー情報クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2020/11/03 新規作成
 * @version 1.1 2020/11/07 No.60,62,63指摘対応
 * @version 1.2 2020/11/14 No.67指摘対応
 * @version 1.3 2020/12/16 No.82指摘対応
 * @version 1.4 2021/05/13 No.86指摘対応
 */
public class User {

    /** 氏名 */
    private String name;
    /** 性別 */
    private Sex sex;
    /** 生年月日 */
    private Date birthday;
    /** 得意言語 */
    private String favoriteLanguage;

    /** 番号_氏名 */
    public static final int NUMBER_NAME = 1;
    /** 番号_性別 */
    public static final int NUMBER_SEX = 2;
    /** 番号_生年月日 */
    public static final int NUMBER_BIRTHDAY = 3;
    /** 番号_得意言語 */
    public static final int NUMBER_FAVORITELANGUAGE = 4;

    /**
     * コンストラクタ
     * 
     * @param name             氏名
     * @param sex              性別
     * @param birthday         生年月日
     * @param favoriteLanguage 得意言語
     */
    public User(final String name, final Sex sex, final Date birthday, final String favoriteLanguage) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.favoriteLanguage = favoriteLanguage;
    }

    /**
     * メンバ変数文字列取得
     * 
     * @return メンバ変数文字列取得
     */
    public static String getSelectPropertyString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(String.format("%2d", Integer.valueOf(NUMBER_NAME))).append(".氏名").append("\n");
        sb.append(String.format("%2d", Integer.valueOf(NUMBER_SEX))).append(".性別").append("\n");
        sb.append(String.format("%2d", Integer.valueOf(NUMBER_BIRTHDAY))).append(".生年月日").append("\n");
        sb.append(String.format("%2d", Integer.valueOf(NUMBER_FAVORITELANGUAGE))).append(".得意言語").append("\n");
        return sb.toString();
    }

    /**
     * 性別
     */
    public static enum Sex {

        MALE(1, "男性"), FEMALE(2, "女性"), OTHER(3, "その他");

        /** id */
        private final int id;
        /** 名称 */
        private final String name;

        /**
         * コンストラクタ
         * 
         * @param id
         */
        private Sex(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * 性別表示文字列取得
         * 
         * @return 性別表示文字列
         */
        public static String getSelectSexString() {
            final StringBuffer sb = new StringBuffer();
            int cnt = 0;
            for (final Sex t : Sex.values()) {
                sb.append(t.id).append(".").append(t.name);
                if (Sex.values().length - 1 > cnt) {
                    sb.append("、");
                }
                cnt++;
            }
            return sb.toString();
        }

        /**
         * 数字から性別の名称取得
         * 
         * @param inputId 入力値
         * @return 性別の名称が存在しない場合はnull値
         */
        public static String convertSexNameById(final int inputId) {
            for (final Sex t : Sex.values()) {
                if (t.id == inputId) {
                    return t.name;
                }
            }
            return null;

        }

        /**
         * 性別取得
         * 
         * @param inputSex 入力値
         * @return 性別が存在しない場合はnull値
         */
        public static Sex convertSex(final String inputSex) {
            if (inputSex == null) {
                return null;
            }

            int sex = 0;
            try {
                sex = Integer.parseInt(inputSex);
            } catch (NumberFormatException e) {
                return null;
            }

            for (final Sex t : Sex.values()) {
                if (t.id == sex) {
                    return t;
                }
            }
            return null;
        }

        /**
         * idを取得
         * 
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * 名称を取得
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

    }

    /**
     * 氏名を取得
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 氏名を設定
     * 
     * @param name 氏名をセットする
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 性別を取得
     * 
     * @return sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * 性別を設定
     * 
     * @param sex 性別 をセットする
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * 生年月日を取得
     * 
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 生年月日を設定
     * 
     * @param birthday 生年月日をセットする
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 得意言語を取得
     * 
     * @return favoriteLanguage
     */
    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    /**
     * 得意言語を設定
     * 
     * @param favoriteLanguage 得意言語をセットする
     */
    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

}
