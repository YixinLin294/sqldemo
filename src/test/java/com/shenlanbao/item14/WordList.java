package com.shenlanbao.item14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeSet;

public class WordList {
    public static void main(String[] args) throws IOException {
        TreeSet<String> stringSet = new TreeSet<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = bufferedReader.readLine();
            if (!"exit".equals(s)) {
                Collections.addAll(stringSet, s);
            } else {
                break;
            }
        }
        System.out.println(stringSet);
    }
}
