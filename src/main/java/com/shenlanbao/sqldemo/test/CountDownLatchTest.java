package com.shenlanbao.sqldemo.test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        IntStream.range(0, 1).forEach(i -> new Thread(() -> {
            System.out.println("before countDown" + Thread.currentThread().getName());
            countDownLatch.countDown();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after countDown" + Thread.currentThread().getName());

        }, "thread-" + i).start());


        IntStream.range(1, 2).forEach(i -> new Thread(() -> {
            System.out.println("before countDown" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("after countDown" + Thread.currentThread().getName());

        }, "thread-" + i).start());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread continue");
    }
}
