package agatasan_java;

/**
 * ファイル書き込み例外クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/18 新規作成
 */
public class FileWriteException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * エラーメッセージ表示
     * 
     * @param strPass 書き込みファイルまでのパス
     * @param e       エラー情報
     */
    public FileWriteException(String strPass, Exception e) {
        System.out.println(String.format("ファイルの書き込みに失敗しました。ファイル名:%s", strPass));
        e.printStackTrace();
    }

}
