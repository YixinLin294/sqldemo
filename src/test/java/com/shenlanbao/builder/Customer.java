package com.shenlanbao.builder;

public class Customer extends Person {

    private long phone;
    private String address;
    private String alias;
    private String intro;

    private Customer(Builder builder) {
        super(builder);
        this.phone = builder.phone;
        this.address = builder.address;
        this.alias = builder.alias;
        this.intro = builder.intro;
    }

    public static class Builder extends Person.Builder<Builder> {
        private long phone;
        private String address;
        private String alias;
        private String intro;

        public Builder(String name, long phone, String address) {
            super(name);
            this.phone = phone;
            this.address = address;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder intro(String intro) {
            this.intro = intro;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }


    }

    public static void main(String[] args) {
        Builder builder = new Builder("zhangsan", 1243L, "北京").age(20).alias("lisi");
        Customer customer = builder.build();
        System.out.println(customer.alias);
    }
}
