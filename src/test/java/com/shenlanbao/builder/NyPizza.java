package com.shenlanbao.builder;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class NyPizza extends Pizza {
    public enum Size {
        SMALL,
        MEDIUM,
        LAGER
    }

    public Integer batchNum;

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;
        private Integer batchNum;
        private static AtomicInteger batchNumStatic = new AtomicInteger();

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }


        @Override
        public NyPizza build() {
            int i = batchNumStatic.incrementAndGet();
            this.batchNum = i;
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
        batchNum = builder.batchNum;
    }

    public static void main(String[] args) {
        Builder builder = new Builder(Size.LAGER).addTopping(Topping.HAM);
        NyPizza nyPizza1 = builder.build();
        NyPizza nyPizza2 = builder.build();
        System.out.println(nyPizza1.batchNum);
        System.out.println(nyPizza2.batchNum);
    }
}
