package com.shenlanbao.sqldemo.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxySubject implements InvocationHandler {

    Subject sub;

    public ProxySubject(Subject sub) {
        this.sub = sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before: " + proxy.getClass().getName());
        method.invoke(sub, args);
        System.out.println("after: " + proxy.getClass().getName());
        return null;
    }
}
