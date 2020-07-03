package com.shenlanbao.sqldemo.test;

import java.util.*;

public class RemoveAllTest {
    public static void main(String[] args) {
        List<Integer> source = new ArrayList<>();
        List<Integer> destioation = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20000000; i++) {
            source.add(random.nextInt(10000000));
            destioation.add(random.nextInt(10000000));
        }
        System.out.println("generate 2 lists");
//        method01(source, destioation);
        List<Integer> result1 = method02(source, destioation);
        System.out.println(result1.size());
        List<Integer> result2 = method03(source, destioation);
        System.out.println(result2.size());
    }

    public static <T> List<T> method01(List<T> source, List<T> destination) {
        long start = System.currentTimeMillis();
        List<T> result = new LinkedList<>();
        for (T t : source) {
            if (!destination.contains(t)) {
                result.add(t);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("method01 cost time: " + (end - start));
        return result;
    }

    public static <T> List<T> method02(List<T> source, List<T> destination) {
        long start = System.currentTimeMillis();
        List<T> result = new LinkedList<>();
        Map<T, Integer> sourceMap = new HashMap<>();
        for (T t : source) {
            if (sourceMap.containsKey(t)) {
                sourceMap.put(t, sourceMap.get(t) + 1);
            } else {
                sourceMap.put(t, 1);
            }
        }

        Set<T> all = new HashSet<T>(destination);
        for (Map.Entry<T, Integer> entry : sourceMap.entrySet()) {
            T key = entry.getKey();
            Integer value = entry.getValue();
            if (all.add(key)) {
                for (int i = 0; i < value; i++) {
                    result.add(key);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("method02 cost time: " + (end - start));
        return result;
    }

    public static <T> List<T> method03(List<T> source, List<T> destination) {
        long start = System.currentTimeMillis();
        List<T> result = new LinkedList<>();
        Set<T> destinationSet = new HashSet<T>(destination);
        for (T t : source) {
            if (!destinationSet.contains(t)) {
                result.add(t);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("method03 cost time: " + (end - start));
        return result;
    }

}
