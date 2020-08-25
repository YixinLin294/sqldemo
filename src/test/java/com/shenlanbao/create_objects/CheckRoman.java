package com.shenlanbao.create_objects;

import java.util.regex.Pattern;

public class CheckRoman {
    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralOptimize(String s) {
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        String input = "MMMCCCXXXIII";
        long start = System.currentTimeMillis();
        int round = 1000;
        for (int i = 0; i < round; i++) {
            CheckRoman.isRomanNumeral(input);
        }
        long end1 = System.currentTimeMillis();
        long cost1 = end1 - start;
        System.out.println("isRomanNumeral cost time: " + cost1);
        start = System.currentTimeMillis();
        for (int i = 0; i < round; i++) {
            CheckRoman.isRomanNumeralOptimize(input);
        }
        long end2 = System.currentTimeMillis();
        long cost2 = end2 - start;
        System.out.println("isRomanNumeralOptimize cost time: " + cost2);
        System.out.println(cost1 / cost2);
    }
}
