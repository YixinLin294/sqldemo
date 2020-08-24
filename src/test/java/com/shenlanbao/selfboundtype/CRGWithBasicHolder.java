package com.shenlanbao.selfboundtype;

class BasicHolder<T> {

    T element;
    void set(T arg) {
        element = arg;
    }

    T get() {
        return element;
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

class Subtype extends BasicHolder<Subtype> {}

public class CRGWithBasicHolder {
    public static void main(String[] args) {
        Subtype st1 = new Subtype();
        Subtype st2 = new Subtype();
        Subtype st3 = new Subtype();

        st1.set(st2);
        st2.set(st3);
        Subtype st4 = st1.get().get();
        st1.f();
    }
}


