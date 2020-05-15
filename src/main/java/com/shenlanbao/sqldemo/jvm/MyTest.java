package com.shenlanbao.sqldemo.jvm;

import java.lang.reflect.Proxy;

public class MyTest {
    public static void main(String[] args) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Subject sub = new RealSubject();
        ProxySubject proxySubject = new ProxySubject(sub);
        Class<? extends Subject> clazz = sub.getClass();
        Subject subject = (Subject) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), proxySubject);

        subject.request();
        System.out.println("--------");
        System.out.println(subject.toString());
        System.out.println("---------");
        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getName());
        System.out.println(sub.hashCode());
    }
}
