package com.shenlanbao.builder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityCreator<T> {

    private Field[] fieldArray;
    private Class<T> className;
    private T entityObj;

    public EntityCreator(Class<T> className) throws Exception {
        this.fieldArray = className.getDeclaredFields();
        this.className = className;
        Constructor<T> constructor = className.getDeclaredConstructor();
        //  constructor.setAccessible(true); 是将AccessibleObject类中的字段 override 置为 true 以绕过语言的检查机制。 并非也并没有改变 字段、方法、构造器的属性(private)
        Field override = null;
        Class<?> aClass = constructor.getClass();
        while (aClass != null) {
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.getName().equals("override")) {
                    override = declaredField;
                    break;
                }
            }
            if (override != null) {
                break;
            } else {
                aClass = aClass.getSuperclass();
            }
        }
        if (override != null) {
            override.setAccessible(true);
//            override.set(constructor, true);
        }
//        constructor.setAccessible(true);
        this.entityObj = constructor.newInstance();
    }

    public EntityCreator<T> setValue(String paramName, Object paramValue) throws Exception {
        for (Field field : fieldArray) {
            if (field.getName().equals(paramName)) {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), className);
                Method method = descriptor.getWriteMethod();
                method.invoke(entityObj, paramValue);
            }
        }

        return this;
    }

    public T build() {
        return entityObj;
    }
}
