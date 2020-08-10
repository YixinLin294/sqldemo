package com.shenlanbao.sqldemo.test.threads;

public class DecreaseThread extends Thread{

    private MyObject myObject;

    private int count = 30;

    public DecreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    public DecreaseThread(MyObject myObject, int count) {
        this.myObject = myObject;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; ++i) {
/*                try {
                    Thread.sleep((long)(Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            myObject.decrease();
        }
    }
}
