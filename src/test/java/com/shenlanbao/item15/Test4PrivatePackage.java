package com.shenlanbao.item15;

public class Test4PrivatePackage {
    public static void main(String[] args) {
        TestForAccessor testForAccessor = new TestForAccessor();
        Integer protectedInteger = testForAccessor.protectedInteger;
        Integer publicInteger = testForAccessor.publicInteger;
        Integer privatePackageInteger = testForAccessor.privatePackageInteger;

    }
}
