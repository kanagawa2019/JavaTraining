package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java課題３(分岐制御）　九九表示クラス(3の倍数はNA表示)　
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/15
 */
public class Java3 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;
    
    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s%s";
    
    /** 3の倍数時の表示 */
    private static final String MULTIPLES_OF_3_DISPLAY = "NA";

    /**
     * 九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {
        
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

    }

}
