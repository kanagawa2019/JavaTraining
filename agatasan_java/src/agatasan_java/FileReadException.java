package agatasan_java;

/**
 * ファイル読み込み例外クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/18 新規作成
 */
public class FileReadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * エラーメッセージ表示
     * 
     * @param strPass 読み込みファイルまでのパス
     * @param e       エラー情報
     */
    public FileReadException(String strPass, Exception e) {
        System.out.println(String.format("ファイルの読み込みに失敗しました。ファイル名:%s", strPass));
        e.printStackTrace();
    }

}
