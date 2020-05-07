package com.shenlanbao.sqldemo.jvm;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("request");
    }
}
