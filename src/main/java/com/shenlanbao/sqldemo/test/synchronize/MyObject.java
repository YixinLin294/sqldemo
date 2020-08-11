package com.shenlanbao.sqldemo.test.synchronize;

public class MyObject {
    private int counter;

    public synchronized void increase() {
        while (counter != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter++;

        System.out.println(counter);

//        notify();
        notifyAll();
    }

    public synchronized void decrease() {
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter--;

        System.out.println(counter);

//        notify();
        notifyAll();
    }

}
