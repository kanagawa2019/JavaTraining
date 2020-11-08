package agatasan_java;

import java.util.Date;

/**
 * ユーザー情報クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2020/11/03 新規作成
 * @version 1.1 2020/11/07 No.60,62,63指摘対応
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

    /**
     * コンストラクタ
     * 
     * @param name             氏名
     * @param sex              性別
     * @param birthday         生年月日
     * @param favoriteLanguage 得意言語
     */
    public User(final String name, final Sex sex, final Date birthday, String favoriteLanguage) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.favoriteLanguage = favoriteLanguage;
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
         * 性別の名称取得
         * 
         * @param inputId 入力値
         * @return 性別の名称が存在しない場合はnull値
         */
        public static String getByid(final int inputId) {
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
        public static Sex getSex(final String inputSex) {
            if (inputSex == null) {
                return null;
            }
            for (final Sex t : Sex.values()) {
                if (t.id == Integer.parseInt(inputSex)) {
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
