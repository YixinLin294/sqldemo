package com.shenlanbao.sqldemo.test.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        System.out.println("begin to start compute");
        System.out.println("future use thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
//            throw new InterruptedException("throw exception");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(100);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreData, executor);

        System.out.println("=================whenComplete============");

        // whenComplete(Async)
        CompletableFuture<Integer> future1 = future.whenComplete((v, e) -> {
            System.out.println("future1 use thread: " + Thread.currentThread().getName());
            System.out.println(v);
            System.out.println(e);
        });

        System.out.println("future1 result: " + future1.get());

        System.out.println("===============exceptionally=============");

        // exceptionally
     /*   CompletableFuture<Integer> exceptionally = future.exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return 1234;
        });
        System.out.println(exceptionally.get());
      */

        System.out.println("===================handle============");
     // handle(Async)
        CompletableFuture<Integer> handle = future.handle((res, ex) -> {
            System.out.println("handle use thread: " + Thread.currentThread().getName());
            if (ex != null) {
                return 200;
            }
            return res;
        });

        System.out.println(handle.get());

        executor.shutdown();

        System.out.println("===========thenApplyAsync====================");

        CompletableFuture<Integer> futureSupply = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<String> future2 = futureSupply.thenApplyAsync(i -> i * 10).thenApply(Object::toString);

/*        CompletableFuture<Void> afterAccept = future2.thenAccept(System.out::println);
        System.out.println(afterAccept.get());*/

        System.out.println("===========thenAcceptBoth===========");

        CompletableFuture<Void> both = future2.thenAcceptBoth(futureSupply, (String str, Integer i) -> {
            System.out.println(i);
            System.out.println(str);
        });

//        System.out.println(future2.get());

        System.out.println("========thenCombine========");

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> "abc");

        CompletableFuture<String> afterCombine = future3.thenCombine(future4, (x, y) -> y + "-" + x);

        System.out.println(afterCombine.get());

        System.out.println("=========either==============");

        CompletableFuture<Integer> future5 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        CompletableFuture<String> future7 = future5.applyToEither(future6, Object::toString);

        System.out.println("future7: " + future7.get());

        System.out.println("================allOf, anyOf==========");

        CompletableFuture<Integer> future8 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<String> future9 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        CompletableFuture<Object> future10 = CompletableFuture.anyOf(future8, future9);
        System.out.println(future10.get());

        System.out.println("===========allAsList==============");

    }
}
