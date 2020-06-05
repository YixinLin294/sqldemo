package com.shenlanbao.sqldemo.java8test;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IfPresent {
    public static void main(String[] args) {
        String s = null;
        Optional<String> option = Optional.of(s);
        List<String> list = new ArrayList<>();
        option.ifPresent(str -> list.add(str));
        for (String s1 : list) {
            System.out.println(s1);
        }
    }
}
