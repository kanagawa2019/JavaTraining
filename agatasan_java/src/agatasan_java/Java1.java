package agatasan_java;

import java.util.stream.IntStream;

public class Java1 {

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            IntStream.rangeClosed(1, 9).forEach(j -> System.out.print(i * j + " "));
            System.out.println("");
        });

    }

}
