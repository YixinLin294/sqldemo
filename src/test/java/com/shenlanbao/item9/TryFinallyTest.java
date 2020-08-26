package com.shenlanbao.item9;

import java.io.*;

public class TryFinallyTest {

    private static Integer BUFFER_SIZE = 1024;

    static String firstLineOfFile(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        try {
            return bufferedReader.readLine();
        } finally {
            bufferedReader.close();
        }
    }


    static void copy(String src, String dst) throws IOException {
        FileInputStream in = new FileInputStream(src);
        try {
            FileOutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
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
