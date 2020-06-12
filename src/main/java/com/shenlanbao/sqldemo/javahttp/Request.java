package com.shenlanbao.sqldemo.javahttp;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class Request {

    private String method;

    private String uri;

    private String version;

    private Map<String, String> headers;

    private String message;


}
