package agatasan_java;

import java.util.stream.IntStream;

/**
 * Java1クラス 九九表示クラス
 *
 * @author 菱田 美紀
 * @version 1.0 2020/03/04 新規作成
 * @version 1.1 2020/03/05 2020/03/05のRV指摘取り込み　マジックナンバーを定数化、表示を整形
 * @version 1.2 2020/03/07 2020/03/06のRV指摘取り込み
 * @version 1.3 2020/03/11 2020/03/10のRV指摘取り込み
 * @version 1.4 2020/04/04 2020/03/24のRV指摘取り込み　Git管理化、ファイルを1つに集約
 */
public class Java1 {

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
