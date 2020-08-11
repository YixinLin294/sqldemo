package com.shenlanbao.sqldemo.test.synchronize;

public class IncreaseThread extends Thread{

    private MyObject myObject;

    private int count = 30;

    public IncreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    public IncreaseThread(MyObject myObject, int count) {
        this.myObject = myObject;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; ++i) {
/*
            try {
                Thread.sleep((long)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/

            myObject.increase();
        }
    }
}
