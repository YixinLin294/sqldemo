package com.shenlanbao.sqldemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class JspController {

    @GetMapping("jsp")
    public String jsp(Map<String, String> model) {
        System.out.println("jsp");
        model.put("subject", "jsp");
        model.put("date", new Date().toString());

        return "result";
    }
}
