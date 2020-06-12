package com.shenlanbao.sqldemo.test;

public class Request {
    private String body;

    public Request(String body) {
        this.body = body;
    }

    public String getUrl() {
        String url = "";
        int i = body.indexOf(" ");
        String substring = body.substring(i+2);
        int j = substring.indexOf(" ");
        url = substring.substring(0,j);
        int k = url.indexOf("?");
        if (k != -1) {
            url = url.substring(0, k);
        }
        return url;
    }
}
