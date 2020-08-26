package com.shenlanbao.private_test;

class Father {
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public Father(Integer age) {
        this.age = age;
    }
}

class Son extends Father {
    private Integer age;

    public Son(Integer age) {
        super(age + 20);
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}

public class PrivateTest {
    public static void main(String[] args) {
        Son son = new Son(10);
        System.out.println(son.getAge());
        System.out.println(((Father) son).getAge());
    }
}
