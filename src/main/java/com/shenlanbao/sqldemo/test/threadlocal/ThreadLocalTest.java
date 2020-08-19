package com.shenlanbao.sqldemo.test.threadlocal;

public class ThreadLocalTest {

    public static ThreadLocal<Integer> threadLocal =new ThreadLocal<Integer>();

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocal.set(100);

        System.out.println(threadLocal.get());

        ThreadLocalTest threadLocalTest1 = new ThreadLocalTest();
        System.out.println(threadLocal.get());
    }
}
