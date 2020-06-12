package com.shenlanbao.sqldemo.test;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8091);
            while (true) {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream();
                byte[] bytes = new byte[1024];
                StringBuilder sb = new StringBuilder();
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, length, "utf-8"));
                }
                if (StringUtils.isEmpty(sb.toString())) {
                    System.out.println("空");
                    continue;
                }
                Request request = new Request(sb.toString());
                Response response = new Response(outputStream, request.getUrl());
                response.response();
            }
        } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
