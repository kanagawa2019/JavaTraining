package agatasan_java;

public class UserEnum {
    /** 番号_氏名 */
    public static final int NUMBER_NAME = 1;
    /** 番号_性別 */
    public static final int NUMBER_SEX = 2;
    /** 番号_生年月日 */
    public static final int NUMBER_BIRTHDAY = 3;
    /** 番号_得意言語 */
    public static final int NUMBER_FAVORITELANGUAGE = 4;

    /**
     * メンバ変数文字列取得
     * 
     * @return メンバ変数文字列取得
     */
    public static String getSelectPropertyString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(Integer.valueOf(NUMBER_NAME)).append(".氏名").append("\n");
        sb.append(Integer.valueOf(NUMBER_SEX)).append(".性別").append("\n");
        sb.append(Integer.valueOf(NUMBER_BIRTHDAY)).append(".生年月日").append("\n");
        sb.append(Integer.valueOf(NUMBER_FAVORITELANGUAGE)).append(".得意言語").append("\n");
        return sb.toString();
    }
}
