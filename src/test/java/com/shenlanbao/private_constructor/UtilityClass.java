package com.shenlanbao.private_constructor;

public class UtilityClass {

    public static void method() {
        System.out.println("invoke method");
    }

    private UtilityClass() {
        System.out.println("utilityclass constructor invoke");
//        throw new AssertionError();
    }
}
