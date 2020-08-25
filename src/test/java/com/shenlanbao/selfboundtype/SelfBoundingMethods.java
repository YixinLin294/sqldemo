package com.shenlanbao.selfboundtype;

class TestNoBoundary<T> {}

public class SelfBoundingMethods {

    static <T extends SelfBounded<T>> T f(T arg) {
        return arg.set(arg).get();
    }

    static <T extends TestNoBoundary<T>> T f1(T arg) {
        return arg;
    }

    public static void main(String[] args) {
        A a = f(new A());

        class SelfBound extends TestNoBoundary<SelfBound> {}

        SelfBound b = f1(new SelfBound());
    }
}
