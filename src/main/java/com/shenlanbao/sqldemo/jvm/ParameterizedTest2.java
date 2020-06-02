package com.shenlanbao.sqldemo.jvm;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTest2 {
    public static void main(String[] args) {
        Field[] fields = ParameterizedBean.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType() instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) field.getGenericType();
                System.out.println("变量: " + pType.getTypeName() + "   ");
                Type[] types = pType.getActualTypeArguments();
                for (Type type : types) {
                    System.out.println("类型：" + type.getTypeName());
                }
            }
        }
    }
}
