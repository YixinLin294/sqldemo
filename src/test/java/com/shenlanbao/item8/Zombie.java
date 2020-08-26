package com.shenlanbao.item8;

public class Zombie {
    static Zombie zombie;

    public void finalize() {
        System.out.println("finalize invoked");
        zombie = this;
    }

    public static void main(String[] args) throws InterruptedException {
        Zombie demo = new Zombie();
        System.out.println(demo.zombie);
        System.gc();
        Thread.sleep(1000);

        System.out.println(demo.zombie);
    }
}
