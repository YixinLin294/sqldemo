package com.shenlanbao.sqldemo.test.threadpool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i  = 0; i < 100; i++) {
            Future<Integer> future = executorService.submit(() -> {
                Random random = new Random();
                int rand = random.nextInt(1000);

//                Thread.sleep(rand);

                System.out.println("thread" + Thread.currentThread().getName() + ", " + rand);
                return rand;
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
