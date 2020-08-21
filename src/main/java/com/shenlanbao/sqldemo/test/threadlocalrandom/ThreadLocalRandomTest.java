package com.shenlanbao.sqldemo.test.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ThreadLocalRandomTest {
    public static void main(String[] args) {

        IntStream.range(0, 100).forEach(i -> new Thread(() -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int nextInt = random.nextInt(100);
            System.out.println(nextInt);
        }).start());

    }
}
