package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java1_2クラス 九九表示クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/07 2020/03/06のRV指摘取り込み
 */
public class Java1_2 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /**
     * 九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                System.out.printf("%2d", i * j);
                System.out.print(" ");
            });
            System.out.printf("%n");
        });

    }

}
