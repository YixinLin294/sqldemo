package com.shenlanbao.item13;

class Base {
    @Override
    protected Base clone() throws CloneNotSupportedException {
        return (Base) super.clone();
//        return new Base();
    }
}

public class BasePro extends Base implements Cloneable {
    @Override
    protected BasePro clone() throws CloneNotSupportedException {
        return (BasePro) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        BasePro basePro = new BasePro();
        System.out.println(basePro.clone().getClass());
        System.out.println(basePro.getClass());
    }
}
