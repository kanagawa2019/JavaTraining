package agatasan_java;

/**
 * プロパティファイル読み込み例外クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/10/18 新規作成
 */
public class PropertyFileReadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * エラーメッセージ表示
     * 
     * @param strPass 読み込みファイルまでのパス
     * @param e       エラー情報
     */
    public PropertyFileReadException() {
        System.out.println("プロパティファイルの書き込みに失敗しました。");
    }

}
