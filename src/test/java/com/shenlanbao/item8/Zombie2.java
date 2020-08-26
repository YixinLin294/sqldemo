package com.shenlanbao.item8;

public class Zombie2 {
    static Zombie2 zombie2;
    int value;

    public Zombie2(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative Zombie2 value");
        }
        this.value = value;
    }

    public void finalize() {
        zombie2 = this;
    }

    public static void main(String[] args) {
        Zombie2 demo = new Zombie2(1);
        System.out.println(demo.value);
        demo.value = -1;
        demo.finalize();
        System.out.println(demo.value);
    }
}
