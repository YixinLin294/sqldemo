package com.shenlanbao.item8;

public class Vulnerable {
    private Integer value = 0;

    public Integer getValue() {
        return value;
    }

    Vulnerable(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Vulnerable value must be positive");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return (value.toString());
    }
}
