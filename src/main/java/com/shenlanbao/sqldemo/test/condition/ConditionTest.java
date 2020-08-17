package com.shenlanbao.sqldemo.test.condition;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionTest {

    private String[] objects = new String[1];

    private Integer count = 0;

    private Integer put = 0;

    private Integer take = 0;

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();


    public void putObject(String str) {
        try {
            lock.lock();
            while (count == objects.length) {
                notFull.await();
            }
            objects[put] = str;
            put++;
            if (put == objects.length) {
                put = 0;
            }
            count++;
            System.out.println(Arrays.asList(objects));
            notEmpty.signalAll();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("unlock");
        }
    }

    public String takeObject() {
        String str = null;
        try {
            lock.lock();
            while (count == 0) {
                notEmpty.await();
            }
            str = objects[take];
            objects[take] = null;
            take++;
            if (take == objects.length) {
                take = 0;
            }
            count--;
            System.out.println(Arrays.asList(objects));
            notFull.signalAll();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return str;
    }

    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();

/*        new Thread(() -> conditionTest.putObject("hello")).start();
        System.out.println("thread 1");

        new Thread(() -> conditionTest.putObject("hello")).start();*/

        new Thread(() -> conditionTest.takeObject()).start();
        System.out.println("thread 1");

        new Thread(() -> conditionTest.putObject("hello")).start();


//        IntStream.range(0, 20).forEach(i -> new Thread(() -> conditionTest.putObject("hello")).start());

//        IntStream.range(0, 10).forEach(i -> new Thread(() -> conditionTest.takeObject()).start());
    }

}
