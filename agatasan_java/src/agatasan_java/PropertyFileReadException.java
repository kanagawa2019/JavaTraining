package agatasan_java;

/**
 * プロパティファイル読み込み例外クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/18 新規作成
 * @version 1.0 2020/11/08 No.57指摘対応
 */
public class PropertyFileReadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * エラーメッセージ表示
     * 
     * @param e   エラー情報
     * @param msg 表示メッセージ
     */
    public PropertyFileReadException(Exception e, String msg) {
        super(msg);
        e.printStackTrace();
    }

}
