package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java2クラス Java課題２(標準入力・関数化・入力チェック）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/08
 */
public class Java2_bk {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /**
     * 入力された数値で九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("数値を入力してください。");
        
        // 連続入力
        while(scanner.hasNext()) {
            
            // 入力チェック
            String result = checkInputData(scanner);
                
            // 数値以外が入力された場合
            if (result == null) {
                System.out.println("エラー！！数値以外が入力されました。");
                continue;
            }
            int line = Integer.valueOf(result);
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                System.out.printf("%2d ", i * line);
            });
            
        }
        // ctr + z で入力終了
        System.out.println("");
        System.out.println("入力終了です。");

    }
    
    /**
     * 入力チェック
     * 
     * @param scanner
     * @return　数値を返します。数値以外が入力された場合は、nullを返します。
     */
    private static String checkInputData (Scanner scanner) {
        String result = null;
        try {
            int line = scanner.nextInt();
            result = String.valueOf(line);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

}
