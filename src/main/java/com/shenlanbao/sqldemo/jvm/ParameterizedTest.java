package com.shenlanbao.sqldemo.jvm;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTest {
    public static void main(String[] args) {
        Field[] declaredFields = ParameterizedBean.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName() + ":"
                    + (declaredField.getGenericType() instanceof ParameterizedType));
            Type genericType = declaredField.getGenericType();
            System.out.println(genericType);
        }
    }
}
