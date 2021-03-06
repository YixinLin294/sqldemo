package com.shenlanbao.item10;

import java.util.Objects;

public class EqualsTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        class Status {
            public String status;

            @Override
            public boolean equals(Object o) {
                return Objects.equals(status, ((Status) o).status);
            }
        }

        Status s1 = new Status();
        Status s2 = new Status();

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        PhoneNumber phoneNumber = new PhoneNumber(1, 1, 1);
        PhoneNumber clone = phoneNumber.clone();
        System.out.println(clone);
    }
}
