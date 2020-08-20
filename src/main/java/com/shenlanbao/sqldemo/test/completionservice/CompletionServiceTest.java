package com.shenlanbao.sqldemo.test.completionservice;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < 10; i++) {
            int thread = i;
            Future<Integer> submit = completionService.submit(() -> {
                Random random = new Random();
                int rand = random.nextInt(1000);
                Thread.sleep(rand);
                System.out.println(thread);
                return rand;
            });
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> take = completionService.take();
                System.out.println(take.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
