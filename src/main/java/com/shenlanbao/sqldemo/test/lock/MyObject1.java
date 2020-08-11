package com.shenlanbao.sqldemo.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyObject1 {
    public Lock lock = new ReentrantLock();

    public void myMethod1() {
        boolean b = false;
        try {
            b = lock.tryLock(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (b) {
            try {
                System.out.println("myMethod1 invoke");
            } finally {
                lock.unlock();
            }
        }
    }

    public void myMethod2() {
        try {
            lock.lock();
            System.out.println("myMethod2 invoke");
        } finally {
//            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyObject1 myObject1 = new MyObject1();

        Runnable runnable1 = () -> {
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep((long)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myObject1.myMethod1();
            }
        };

        Runnable runnable2 = () -> {
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep((long)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myObject1.myMethod2();

            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}
