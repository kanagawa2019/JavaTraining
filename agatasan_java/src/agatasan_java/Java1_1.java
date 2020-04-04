package agatasan_java;

import java.util.stream.IntStream;

public class Java1_1 {

    private static final int START_RANGE = 1;

    private static final int END_RANGE = 9;

    public static void main(String[] args) {
        IntStream.rangeClosed(START_RANGE, END_RANGE).forEach(i -> {
            IntStream.rangeClosed(START_RANGE, END_RANGE).forEach(j -> {

                if (String.valueOf(i * j).length() < 2) {
                    System.out.print(" ");
                }
                System.out.print(i * j + " ");
            });
            System.out.println("");
        });

    }

}
