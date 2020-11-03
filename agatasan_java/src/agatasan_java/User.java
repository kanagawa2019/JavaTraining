package agatasan_java;

/**
 * ユーザー情報クラス
 * 
 * @author 菱田 美紀
 * @version 1.0 2020/11/03 新規作成
 */
public class User {

    /** 氏名 */
    private String name;
    /** 性別 */
    private int sex;
    /** 生年月日 */
    private String birthday;
    /** 得意言語 */
    private String favoriteLanguage;

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
    public int getSex() {
        return sex;
    }

    /**
     * 性別を設定
     * 
     * @param sex 性別 をセットする
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 生年月日を取得
     * 
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生年月日を設定
     * 
     * @param birthday 生年月日をセットする
     */
    public void setBirthday(String birthday) {
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
