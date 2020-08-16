package com.shenlanbao.sqldemo;

import com.alibaba.fastjson.TypeReference;
import com.shenlanbao.sqldemo.common.model.ApiResult;
import com.shenlanbao.sqldemo.model.BiliTitle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.List;

public class BiliBiliTitle {

    public static RestTemplate restTemplate = new RestTemplate();

    public static String url = "https://api.bilibili.com/x/player/pagelist?bvid=BV1Je411W7Ew&jsonp=jsonp";

    public static void main(String[] args) {
//        ApiResult<BiliTitle> forObject = restTemplate.getForObject(url, ApiResult.class);

        ApiResult<List<BiliTitle>> result = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResult<List<BiliTitle>>>() {
        }).getBody();

        StringBuilder sb = new StringBuilder();
        List<BiliTitle> list = result.getData();
        for (BiliTitle biliTitle : list) {
            sb.append(biliTitle.getPart()).append("\n");
        }
        String res = sb.toString();
        File file = new File("多线程课程.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(res.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
