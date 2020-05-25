package com.shenlanbao.sqldemo.noweb;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Test {
    public static void main(String[] args) throws IOException {
        Enumeration<URL> resources =
                Test.class.getClassLoader().getResources("Test");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url.toString());
        }
    }
    private int a = 1;
    public int getA() {
        return a;
    }
    public void setA(int a){
        this.a = a;
    }
}
