package com.shenlanbao.selfboundtype;

public class Cat extends Animal {
    private String master;
    private String name;

    public static class Builder extends Animal.Builder<Builder> {

        private String master;
        private String name;

        public Builder master(String master) {
            this.master = master;
            return this;
        }

        Builder(Integer age, String name) {
            super(age);
            this.name = name;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        Cat build() {
            return new Cat(this);
        }
    }

    private Cat(Builder builder) {
        super(builder);
        this.master = builder.master;
        this.name = builder.name;
    }

    public static void main(String[] args) {
        Cat cat = new Builder(11, "cat").master("master").color("white").height(100.0).build();

        System.out.println(cat.master + ", " + cat.name + ", " + cat.age + ", " + cat.color + ", " + cat.height);

    }
}
