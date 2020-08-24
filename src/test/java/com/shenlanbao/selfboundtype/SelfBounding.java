package com.shenlanbao.selfboundtype;

class SelfBounded<T extends SelfBounded<T>> {
    T element;
    SelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }

    T get() {
        return element;
    }
}

class A extends SelfBounded<A> {}

class B extends SelfBounded<A> {}

public class SelfBounding {

    public static void main(String[] args) {
        A a = new A();
    }
}
