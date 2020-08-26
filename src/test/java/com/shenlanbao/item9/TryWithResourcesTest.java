package com.shenlanbao.item9;

import java.io.*;

public class TryWithResourcesTest {
    private static Integer BUFFER_SIZE = 1024;

    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\SLB0195\\Desktop\\shenlanbao\\consult-service-test\\src\\main\\java\\com\\shenlanbao\\consult\\bo\\ICronJobsBo.java";

        String dst = "C:\\Users\\SLB0195\\Desktop\\shenlanbao\\consult-service-test\\src\\main\\java\\com\\shenlanbao\\consult\\bo\\copy.txt";

        String s = firstLineOfFile(path);
        System.out.println(s);

        copy(path, dst);
    }
}
