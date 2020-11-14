package agatasan_java;

/**
 * ファイル書き込み例外クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/18 新規作成
 * @version 1.0 2020/11/08 No.57,58指摘対応
 */
public class FileWriteException extends Exception {

    private static final long serialVersionUID = 2L;

    /**
     * エラーメッセージ表示
     * 
     * @param e   エラー情報
     * @param msg 表示メッセージ
     */
    public FileWriteException(Exception e, String msg) {
        super(msg);
        e.printStackTrace();
    }

}
