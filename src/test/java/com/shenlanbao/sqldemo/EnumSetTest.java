package com.shenlanbao.sqldemo;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

public class EnumSetTest {

    public static void main(String[] args) {
        EnumSet<StringEnum> stringEnums = EnumSet.noneOf(StringEnum.class);
    }
}

enum  StringEnum {

    STRING_1,
    STRING_2,
    STRING_3,
    STRING_4,
    STRING_5,
    STRING_6,
    STRING_7,
    STRING_8,
    STRING_9,
    STRING_10,
    STRING_11,
    STRING_12,
    STRING_13,
    STRING_14,
    STRING_15,
    STRING_16,
    STRING_17,
    STRING_18,
    STRING_19,
    STRING_20,
    STRING_2_1,
    STRING_2_2,
    STRING_2_3,
    STRING_2_4,
    STRING_2_5,
    STRING_2_6,
    STRING_2_7,
    STRING_2_8,
    STRING_2_9,
    STRING_2_10,
    STRING_2_11,
    STRING_2_12,
    STRING_2_13,
    STRING_2_14,
    STRING_2_15,
    STRING_2_16,
    STRING_2_17,
    STRING_2_18,
    STRING_2_19,
    STRING_2_20,
    STRING_3_1,
    STRING_3_2,
    STRING_3_3,
    STRING_3_4,
    STRING_3_5,
    STRING_3_6,
    STRING_3_7,
    STRING_3_8,
    STRING_3_9,
    STRING_3_10,
    STRING_3_11,
    STRING_3_12,
    STRING_3_13,
    STRING_3_14,
    STRING_3_15,
    STRING_3_16,
    STRING_3_17,
    STRING_3_18,
    STRING_3_19,
    STRING_3_20,
    STRING_4_1,
    STRING_4_2,
    STRING_4_3,
    STRING_4_4,
    STRING_4_5,
    STRING_4_6,
    STRING_4_7,
    STRING_4_8,
    STRING_4_9,
    STRING_4_10,
    STRING_4_11,
    STRING_4_12,
    STRING_4_13,
    STRING_4_14,
    STRING_4_15,
    STRING_4_16,
    STRING_4_17,
    STRING_4_18,
    STRING_4_19,
    STRING_4_20
    ;

    AtomicInteger count = new AtomicInteger();
    StringEnum() {
        count.incrementAndGet();
        System.out.println("constructor" + count);
    }
}
