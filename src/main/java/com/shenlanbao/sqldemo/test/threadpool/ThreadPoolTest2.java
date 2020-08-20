package com.shenlanbao.sqldemo.test.threadpool;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolTest2 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 15; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Future<Integer> future = threadPoolExecutor.submit(() -> {
            Random random = new Random();
            int rand = random.nextInt(1000);
            try {
                Thread.sleep(rand);
                System.out.println("future get");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return rand;
        });

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        threadPoolExecutor.shutdown();
    }
}
