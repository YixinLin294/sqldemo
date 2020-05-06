package com.shenlanbao.sqldemo.controller;

import java.net.URLClassLoader;
import java.util.List;
import java.util.Optional;

public class Test07 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        System.out.println("----------");
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        System.out.println("-----------");
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        System.out.println("------------");
        ClassLoader classLoader = Class.forName("com.shenlanbao.sqldemo.controller.Test07").getClassLoader();
        System.out.println(classLoader);
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("-------------");
        System.out.println(System.getProperty("java.ext.dirs"));
        String property = System.getProperty("java.version");
        Runtime.Version parse = Runtime.Version.parse(property);
        int major = parse.major();
        System.out.println("major:" + major);
        int minor = parse.minor();
        System.out.println("minor:" + minor);
        int security = parse.security();
        System.out.println("security: " + security);
        Optional<String> pre = parse.pre();
        pre.ifPresent(System.out::println);
        Optional<Integer> build = parse.build();
        build.ifPresent(System.out::println);
        Optional<String> optional = parse.optional();
        optional.ifPresent(System.out::println);
        List<Integer> version = parse.version();
        for (Integer integer : version) {
            System.out.println(integer);
        }
        System.out.println(parse);
        Runtime.Version parse1 = Runtime.Version.parse("9.1.1");
        Runtime.Version parse2 = Runtime.Version.parse("9.1.1-ea");
        System.out.println(parse1.compareToIgnoreOptional(parse2));
        String property1 = System.getProperty("java.endorsed.dirs");
        System.out.println("--------");
        System.out.println(System.getProperty("jdk.boot.class.path.append"));
        System.out.println(ClassLoader.getPlatformClassLoader());
    }
}
