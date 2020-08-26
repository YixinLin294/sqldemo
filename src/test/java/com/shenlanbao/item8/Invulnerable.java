package com.shenlanbao.item8;

public class Invulnerable {
    private int value = 0;

    Invulnerable(int value) {
        this(checkValues(value));
        this.value = value;
    }

    private Invulnerable(Void checkValues) {}

    private static Void checkValues(int value) {
        if(value <= 0) {
            throw new IllegalArgumentException("Invulnerable value must be positive");
        }
        return null;
    }

}
