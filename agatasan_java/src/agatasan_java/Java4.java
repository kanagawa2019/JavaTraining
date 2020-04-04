package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java4クラス Java課題４(ステータス管理）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/22
 */
public class Java4 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s%s";
    
    /** 3の倍数時の表示 */
    private static final String MULTIPLES_OF_3_DISPLAY = "NA";
    
    /**
     * 入力された数値で九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // 切替モードオン・オフ
        boolean displaySwitching = false;
        
            while(true) {
                
                System.out.println("***********************************");
                System.out.println("処理モードを選択してください");
                System.out.println("1.九九表示");
                System.out.println("2.指定の段のみ表示");
                if (displaySwitching) {
                    System.out.println("3.モード切替(世界のナベアツ⇒通常)");
                } else {
                    System.out.println("3.モード切替(通常⇒世界のナベアツ)");
                }
                
                System.out.println("9.終了");
                System.out.println("***********************************"); 
                
                
                if (scanner.hasNextInt()) {
                    // 入力内容が数値の場合
                    int line = scanner.nextInt();
                    
                    // 1が入力された場合
                    if (line == 1) {
                        if (displaySwitching) {
                            // 切替モードオンの場合
                            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                                    
                                    // 3の倍数は別表示
                                    if ((i * j) % 3 == 0) {
                                        System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_3_DISPLAY, " ");
                                    } else {
                                        System.out.printf(DISPLAY_FORMAT, i * j, " ");
                                    }
                                });
                                System.out.printf("%n");
                            });
                        } else {
                            // 切替モードオフの場合
                            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                                    System.out.printf(DISPLAY_FORMAT, i * j, " ");
                                });
                                System.out.printf("%n");
                            });
                        }

                    }
                    
                    // 2が入力された場合
                    if (line == 2) {
                        System.out.println("表示する段を入力してください");
                        
                        if (scanner.hasNextInt()) {
                            // 入力内容が数値の場合
                            int inputLine = scanner.nextInt();

                            
                            if (displaySwitching) {
                                // 切替モードオンの場合
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                    
                                    // 3の倍数は別表示
                                    if ((i * inputLine) % 3 == 0) {
                                        System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_3_DISPLAY, " ");
                                    } else {
                                        System.out.printf(DISPLAY_FORMAT, i * inputLine, " ");
                                    }
                                    
                                });
                                System.out.printf("%n");
                            } else {
                                // 切替モードオフの場合
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                    System.out.printf(DISPLAY_FORMAT, i * inputLine, " ");
                                });
                                System.out.printf("%n");
                            }
                            
                        } else {
                            // 入力内容が数値以外の場合
                            System.out.println("エラー！！数値以外が入力されました。");
                            scanner.next();
                        }
                    }
                    
                    // 3が入力された場合
                    if (line == 3) {
                        // 切替
                        if (displaySwitching) {
                            displaySwitching = false;
                        } else  {
                            displaySwitching = true;
                        }
                        System.out.println("モードを切り替えました。");
                    }
                    
                    
                    // 9が入力された場合
                    if (line == 9) {
                        System.out.println("終了します。");
                        break;
                    }
                    
                } else {
                    // 入力内容が数値以外の場合
                    System.out.println("エラー！！数値以外が入力されました。");
                    scanner.next();
                }
            }
            scanner.close();
            
    }
}
