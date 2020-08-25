package com.shenlanbao.create_objects;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sum {
    private static long sum() {
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String s = bufferedReader.readLine();
                System.out.println(s);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long start = System.currentTimeMillis();
        long sum = Sum.sum();
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start));
    }
}
