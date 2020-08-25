package com.shenlanbao.private_constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UtilityTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        UtilityClass.method();

        Constructor<UtilityClass> declaredConstructor = UtilityClass.class.getDeclaredConstructor();

        declaredConstructor.setAccessible(true);

        UtilityClass utilityClass = declaredConstructor.newInstance();

    }
}
