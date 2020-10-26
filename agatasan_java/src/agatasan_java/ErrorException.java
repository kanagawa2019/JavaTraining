package agatasan_java;

public class ErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private int code;

    public ErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void FileReadException(String strPass) {
        System.out.println(String.format("ファイルの読み込みに失敗しました。ファイル名:%s", strPass));
    }

}
