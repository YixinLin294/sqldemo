package com.shenlanbao.selfboundtype;

public class Person {
    private String name;
    private int age;
    private String gender;

    protected Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
    }


    public static class Builder<T extends Builder<T>> {
        private String name;
        private int age;
        private String gender;

        public T age(int age) {
            this.age = age;
            return (T) this;
        }

        public T gender(String gender) {
            this.gender = gender;
            return (T) this;
        }

        public Builder(String name) {
            this.name = name;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person person = new Builder("zhangsan").age(20).gender("male").build();

        System.out.println(person.gender);
    }
}
