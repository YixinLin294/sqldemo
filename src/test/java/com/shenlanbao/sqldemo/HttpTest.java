package com.shenlanbao.sqldemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.shenlanbao.sqldemo.common.model.ApiResult;
import com.shenlanbao.sqldemo.common.model.PageResult;
import com.shenlanbao.sqldemo.model.dto.ProductDTO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HttpTest {

    public static RestTemplate restTemplate = new RestTemplate();

//    public static final String BASE_URL = "https://consult-dashboard-api.shenlanbao.com";
    public static final String BASE_URL = "http://127.0.0.1:8080";

//    public static final String Authorization = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFkb3JGbVQ4Wll5aXpxZWpLanAifQ.eyJqdGkiOiJ1ZU1CT1V1aF9Wc05FdldjTTJfUHlBIiwiaWF0IjoxNTk3NDg0MjA2LCJleHAiOjE1OTc1NzA2MDYsIm5iZiI6MTU5NzQ4NDE0Niwic3ViIjoiMjAwMSIsImF1ZCI6IlNMQiJ9.N1l3b9Egv4JzoYa1OBbaIsT1tskuvGqKJMARJKssCekoz_KeoNLec7m-H4C9ME5dZVtE2wRrU54z-MfW074xeVWhH87g4xJRnrmIGlRJcZlQlKJSdver94MTFvC5HiJh2rhj9MqWPOyMo2IFFG0hyTtyjkhE3AU5sf5pidhFH5iDQg1ZqdlZmeuUoEHZUkYK3KOzHuhfZbJ0IK9WIJiOxSQoeG2B7n9kTQCVDVWT0qigsuq5GROgZV9Wdh5jkjlcukdh3xw8XrqMRJ19myXEze9q2k_VJ9Q0x0pXQUHfimCMJSf_JjJ3635ymae3CHr99BKrN9uWBw5snJNGxdAANA";

    public static final String Authorization = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVmMTBkODI0MDJlMjQ2NmY4N2I5YWY3NzlhY2Q4NWViIn0.eyJqdGkiOiJsRC04MUJMaTZvVnVxUjA1VjZwLXRRIiwiaWF0IjoxNTk3NTAyNTA1LCJleHAiOjE1OTc1ODg5MDUsIm5iZiI6MTU5NzUwMjQ0NSwic3ViIjoiMTAwMiIsImF1ZCI6IlNMQiJ9.b529BHCzrlROUV9pLBJf94ALR4lJRu3f4LR7obb1DS4YWntkUixkx5OC93ZemqScDoVoJIrLwvThELU1ni-EM-Uaj6ArUkASG3xdQakQiimhXVTC2M_wtD6WajlBn0WWsfy7W_tMnAYA_Ny_UhiwcLxT83xIb0GTC7caye4INoytoLGF55dZOBbz450xGf5Ap8lsC0oCtu1gnLkCQe72CLyhLKrUeDrrm3-AFgwGo0M92bDNCETTcHTXqjASrIshJxMbPnNEJk5GlLatpai5DNwvI67Z9uN1Pae-CwFjunB4LnGzs8Z_VSR5YkkeyPJlxBOCgD4fWPzYybZ6kIENDg";

    public static void main(String[] args) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", Authorization);
        HttpEntity<String> parameters = new HttpEntity<>(httpHeaders);

        String productsUrl = BASE_URL +  "/products?page_size=10000&alternative=false";
        ResponseEntity<String> exchange = restTemplate.exchange(productsUrl, HttpMethod.GET, parameters, String.class);
        String body = exchange.getBody();
//        SerializeConfig config = new SerializeConfig();
//        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        ApiResult<PageResult<ProductDTO>> pageResultApiResult = JSONObject.parseObject(body, new TypeReference<ApiResult<PageResult<ProductDTO>>>() {
        });

        PageResult<ProductDTO> data = ((PageResult<ProductDTO>) pageResultApiResult.getData());

        List<ProductDTO> list = data.getList();

        for (ProductDTO productDTO : list) {
            new Thread(() ->
                {Integer id = productDTO.getId();
                String tryUrl = "/ins_products/" + id  + "/trial_settings?insurant_date_of_birth=1990-01-09&insurant_social_security=%E6%98%AF&applicant_date_of_birth=1990-01-09&applicant_gender=%E5%A5%B3&insurant_gender=%E5%A5%B3&for_self=true";
                ResponseEntity<String> exchange1 = restTemplate.exchange(productsUrl, HttpMethod.GET, parameters, String.class);
//                String body1 = exchange.getBody();
//                System.out.println(body1);
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(exchange1.getStatusCodeValue()); }, productDTO.getId().toString()
                ).start();
        }

//        new Gson().fromJson(body,  new TypeToken<ApiResult<PageResult>>)

    }
}
