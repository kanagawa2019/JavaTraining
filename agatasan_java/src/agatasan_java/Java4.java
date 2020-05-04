package agatasan_java;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java4クラス Java課題４(ステータス管理）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/05/04
 */
public class Java4 {

    /** 九九の始まりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** 九九の終わりの値 */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** 表示形式 */
    private static final String DISPLAY_FORMAT = "%2s  ";

    /** 倍数 */
    private static final int MULTIPLE = 3;

    /** s倍数時の表示 */
    private static final String MULTIPLES_OF_DISPLAY = "NA";

    /** ユーザー入力促し文言 */
    private static final String USER_INPUT_SENTENCES = "数値を入力してください。";

    /** 入力許容最小値 */
    private static final int USER_INPUT_MIN_VALUE = 1;

    /** 入力許容最大値 */
    private static final int USER_INPUT_MAX_VALUE = 9;

    /** 入力許容範囲 */
    private static final String USER_INPUT_RANGE = USER_INPUT_MIN_VALUE + "～" + USER_INPUT_MAX_VALUE;

    /** 数値入力促し文言 */
    private static final String NUMBER_INPUT_SENTENCES = USER_INPUT_RANGE + "の" + USER_INPUT_SENTENCES;

    /** 数値以外の入力時エラー文言 */
    private static final String NON_NUMERIC_ERROR = "エラー！！" + USER_INPUT_RANGE + "以外の数値が入力されました。";

    /** 処理モード数値：九九表示 */
    private static final int MODE_DISPLAY_MULTIPLICATION_TABLE = 1;

    /** 処理モード数値：指定の段のみ表示 */
    private static final int MODE_DISPLAY_MULTIPLICATION_LINE = 2;

    /** 処理モード数値：モード切替 */
    private static final int MODE_SWITCHING = 3;

    /** 処理モード数値：終了 */
    private static final int MODE_END = 9;

    /**
     * 入力された数値で九九の計算を行い、コンソールに表示します。
     * 
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 切替モードオン・オフ
        boolean displaySwitching = false;

        displaySentence(displaySwitching);

        while (scanner.hasNext()) {

            // 値取得
            String input = scanner.next();

            // 数値か判定
            try {
                int line = Integer.valueOf(input).intValue();

                // 1が入力された場合
                if (line == MODE_DISPLAY_MULTIPLICATION_TABLE) {
                    // 九九表示処理
                    displayMultiplicationTable(displaySwitching);
                    displaySentence(displaySwitching);
                    continue;
                }

                // 2が入力された場合
                if (line == MODE_DISPLAY_MULTIPLICATION_LINE) {
                    displayMultiplicationLine(scanner, displaySwitching);
                    displaySentence(displaySwitching);
                    continue;
                }

                // 3が入力された場合
                if (line == MODE_SWITCHING) {
                    // 切替
                    displaySwitching = displaySwitching ? false : true;
                    System.out.println("モードを切り替えました。");
                    displaySentence(displaySwitching);
                    continue;
                }

                // 9が入力された場合
                if (line == MODE_END) {
                    System.out.println("終了します。");
                    break;
                }

                // 指定数値以外の場合
                List<String> list = Arrays.asList(String.valueOf(MODE_DISPLAY_MULTIPLICATION_TABLE),
                        String.valueOf(MODE_DISPLAY_MULTIPLICATION_LINE), String.valueOf(MODE_SWITCHING),
                        String.valueOf(MODE_END));

                System.out.println(String.join(",", list) + "のいずれかを入力してください。");

                displaySentence(displaySwitching);
                continue;

            } catch (NumberFormatException e) {
                // 入力内容が数値以外の場合
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }

        }

        scanner.close();

    }

    /**
     * 数値範囲チェック
     * 
     * @param targetNumber チェック対象数値
     * @param minValue     最小値
     * @param maxValue     最大値
     * @return true:最小値～最大値の範囲内にある
     */
    private static boolean isWithinRange(int targetNumber, int minValue, int maxValue) {
        return (minValue <= targetNumber && targetNumber <= maxValue);
    }

    /**
     * 九九１行表示の処理
     * 
     * @param displaySwitching 切替モードの状態
     * @param inputNumber      入力文字
     */
    private static void displaySingleLine(boolean displaySwitching, int inputNumber) {

        // 切替モードオンの場合
        if (displaySwitching) {
            // TODO NA表示？
            displaySwitchingModeOn(inputNumber);
            return;
        }

        // 切替モードオフの場合
        displaySwitchingModeOff(inputNumber);

    }

    /**
     * 切替モードオフの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    private static void displaySwitchingModeOff(int inputNumber) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            System.out.printf(DISPLAY_FORMAT, i * inputNumber, " ");
        });
        System.out.printf("%n");
    }

    /**
     * 切替モードオンの場合の表示
     * 
     * @param inputNumber 入力文字
     */
    private static void displaySwitchingModeOn(int inputNumber) {

        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {

            // 3の倍数は別表示
            if ((i * inputNumber) % MULTIPLE == 0) {
                System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_DISPLAY, " ");
            } else {
                System.out.printf(DISPLAY_FORMAT, i * inputNumber, " ");
            }

        });
        System.out.printf("%n");
    }

    /**
     * 九九表示の処理
     * 
     * @param displaySwitching 切替モードの状態
     */
    private static void displayMultiplicationTable(boolean displaySwitching) {

        // 切替モードオンの場合
        if (displaySwitching) {
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                displaySwitchingModeOn(i);
            });
            return;
        }

        // 切替モードオフの場合
        IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
            displaySwitchingModeOff(i);

        });

    }

    /**
     * 九九１行表示の入力判定処理
     * 
     * @param scanner          入力情報
     * @param displaySwitching 切替モードの状態
     */
    private static void displayMultiplicationLine(Scanner scanner, boolean displaySwitching) {
        System.out.println("表示する段を入力してください");

        while (scanner.hasNext()) {

            // 数値か判定
            try {
                int inputLine = Integer.valueOf(scanner.next()).intValue();

                // 入力許容範囲外はエラー
                if (!isWithinRange(inputLine, USER_INPUT_MIN_VALUE, USER_INPUT_MAX_VALUE)) {
                    System.out.println(NON_NUMERIC_ERROR);
                    System.out.println(NUMBER_INPUT_SENTENCES);
                    continue;
                }

                displaySingleLine(displaySwitching, inputLine);
                break;
            } catch (NumberFormatException e) {
                // 入力内容が数値以外の場合
                System.out.println(NON_NUMERIC_ERROR);
                System.out.println(NUMBER_INPUT_SENTENCES);
            }

        }
    }

    /**
     * コンソールに表示する文言
     * 
     * @param displaySwitching 切替モードの状態
     */
    private static void displaySentence(boolean displaySwitching) {

        System.out.println("***********************************");
        System.out.println("処理モードを選択してください");
        System.out.println(MODE_DISPLAY_MULTIPLICATION_TABLE + ".九九表示");
        System.out.println(MODE_DISPLAY_MULTIPLICATION_LINE + ".指定の段のみ表示");
        if (displaySwitching) {
            System.out.println(MODE_SWITCHING + ".モード切替(世界のナベアツ⇒通常)");
        } else {
            System.out.println(MODE_SWITCHING + ".モード切替(通常⇒世界のナベアツ)");
        }

        System.out.println(MODE_END + ".終了");
        System.out.println("***********************************");
    }
}
