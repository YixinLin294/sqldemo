package com.shenlanbao.item8;

public class AttackInvulnerable extends Invulnerable {
    static Invulnerable invulnerable;

    AttackInvulnerable(int value) {
        super(value);
    }

    public void finalize() {
        invulnerable = this;
    }

    public static void main(String[] args) {
        try {
            new AttackInvulnerable(-1);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.gc();
        System.runFinalization();
        if (invulnerable != null) {
            System.out.println("Invulnerable object " + invulnerable + "created!");
        } else {
            System.out.println("Attack failed");
        }
    }
}
