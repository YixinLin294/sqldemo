package com.shenlanbao.sqldemo.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierTest {
    public CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public void arrive() {
        try {
            Thread.sleep((long)(Math.random()*1000));
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        IntStream.range(0, 3).forEach(i -> new Thread(() -> cyclicBarrierTest.arrive(), "thread-"+ i).start());


    }
}
