package com.shenlanbao.sqldemo.controller;

import com.shenlanbao.sqldemo.common.model.ApiResult;
import com.shenlanbao.sqldemo.model.BiliTitle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class BiliController {

    public RestTemplate restTemplate = new RestTemplate();

    public final String suffix = ".txt";

//    public static String url = "https://api.bilibili.com/x/player/pagelist?bvid=BV1Je411W7Ew&jsonp=jsonp";

    @GetMapping("/bilititle")
    public void biliTitle(@RequestParam String url, @RequestParam String filename) {

        ApiResult<List<BiliTitle>> result = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResult<List<BiliTitle>>>() {
        }).getBody();

        StringBuilder sb = new StringBuilder();
        List<BiliTitle> list = result.getData();
        for (BiliTitle biliTitle : list) {
            sb.append(biliTitle.getPart()).append("\n");
        }
        String res = sb.toString();
        File file = new File(filename + suffix);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
