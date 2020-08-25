package com.shenlanbao.builder;

abstract public class Animal {
    protected Integer age;
    protected Double height;
    protected Double weight;
    protected String color;

    public static void method() {
        System.out.println("static method in abstract class can be invoke");
    }
    abstract static class Builder<T extends Builder<T>> {
        private Integer age;
        private Double height;
        private Double weight;
        private String color;

        public T height(Double height) {
            this.height = height;
            return self();
        }

        public T weight(Double weight) {
            this.weight = weight;
            return self();
        }

        public T color(String color) {
            this.color = color;
            return self();
        }

        Builder(Integer age) {
            this.age = age;
        }

        protected abstract T self();

        abstract Animal build();
    }

    Animal(Builder<?> builder) {
        this.age = builder.age;
        this.height = builder.height;
        this.weight = builder.weight;
        this.color = builder.color;
    }

    public static void main(String[] args) {
        Animal.method();
    }

}
