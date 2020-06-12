package com.shenlanbao.sqldemo.test;

import java.io.*;

public class Response {
    private String url;
    private OutputStream outputStream;

    public Response(OutputStream outputStream, String url) {
        this.outputStream = outputStream;
        this.url = url;
    }

    public void response() {
        File file = new File(url);
        System.out.println(file.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        if (file.exists() && !file.isDirectory()) {
            this.addHeader(sb);
            try {
                outputStream.write(sb.toString().getBytes("utf-8"));
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[1024];
/*                int length;
                while ((length = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private StringBuilder addHeader(StringBuilder sb) {
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8\r\n");
        sb.append("\r\n");
        return sb;
    }
}
