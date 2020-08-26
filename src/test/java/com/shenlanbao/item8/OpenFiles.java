package com.shenlanbao.item8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenFiles {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\SLB0195\\Desktop\\shenlanbao\\consult-service-test\\src\\main\\java\\com\\shenlanbao\\consult");
        List<File> files = new ArrayList<>();
        List<InputStreamReader> inputStreamReaders = new ArrayList<>();
        List<OutputStreamWriter> outputStreamWriters = new ArrayList<>();
        getFiles(file, files);
        System.out.println(files.size());
        files.forEach(System.out::println);
        files.forEach(file1 -> {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file1));
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file1));
                char[] cbuf = new char[64];
                inputStreamReader.read(cbuf);
                System.out.println(cbuf);
                inputStreamReaders.add(inputStreamReader);
//                outputStreamWriters.add(outputStreamWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println(inputStreamReaders.size());
        inputStreamReaders.forEach(System.out::println);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getFiles(File file, List<File> result) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    getFiles(file1, result);
                } else {
                    result.add(file1);
                }
            }
        } else {
            result.add(file);
        }
    }
}
