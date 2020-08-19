package com.shenlanbao.sqldemo.test.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReentrantReadWriteLockTest {
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    String book = "book";

    Lock readLock = reentrantReadWriteLock.readLock();
    Lock writeLock = reentrantReadWriteLock.writeLock();

    public String read() {
        try {
            readLock.lock();
            Thread.sleep((long)(Math.random()) * 1000);
            System.out.println(Thread.currentThread().getName() + " read " + book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return book;
    }

    public void write(String str) {
        try {
            writeLock.lock();
            book = str;
            Thread.sleep((long)(Math.random() * 1000));
            System.out.println("write, change to: " + book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        IntStream.range(0, 10).forEach(i -> new Thread(() -> test.write("book-" + i), "write thread" + i).start());

        IntStream.range(0, 10).forEach(i -> new Thread(() -> test.read(), "read thread" + i).start());
    }
}
