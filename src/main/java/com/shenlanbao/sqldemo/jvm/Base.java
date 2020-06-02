package com.shenlanbao.sqldemo.jvm;

import java.lang.reflect.ParameterizedType;

public class Base<T> {
    private Class<T> clazz;

    public Base() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        System.out.println("-----> clazz : " + this.clazz);
    }
}


