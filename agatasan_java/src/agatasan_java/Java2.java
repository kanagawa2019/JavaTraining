package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java2クラス Java課題２(標準入力・関数化・入力チェック）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/08
 */
public class Java2 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s ";
    
    /**
     * 入力された数値で九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("数値を入力してください。");
        
            while(true) {
                if (scanner.hasNextInt()) {
                    // 入力内容が数値の場合
                    int line = scanner.nextInt();
                    IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                        System.out.printf(DISPLAY_FORMAT, i * line, " ");
                    });
                } else {
                    // 入力内容が数値以外の場合
                    System.out.println("エラー！！数値以外が入力されました。");
                    System.out.println("数値を入力してください。");
                    scanner.next();
                }
            }
            
    }
}
