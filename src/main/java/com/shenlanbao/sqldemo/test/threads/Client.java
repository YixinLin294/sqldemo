package com.shenlanbao.sqldemo.test.threads;

public class Client {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        Thread increaseThread = new IncreaseThread(myObject, 10);
        Thread increaseThread2 = new IncreaseThread(myObject, 90);
        Thread decreaseThread = new DecreaseThread(myObject,50);
        Thread decreaseThread2 = new DecreaseThread(myObject,50);

        increaseThread.start();
        increaseThread2.start();

        decreaseThread.start();
        decreaseThread2.start();
    }
}
