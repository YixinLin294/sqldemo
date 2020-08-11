package com.shenlanbao.sqldemo.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Person {

    private String name;

    private Date birth;

    private List<String> interests;

    public void sayHello() {
        System.out.println("hello " + this.name);
    }
}
