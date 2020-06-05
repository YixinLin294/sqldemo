package com.shenlanbao.sqldemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shenlanbao.sqldemo.model.Consultant;
import com.shenlanbao.sqldemo.model.PageResult;
import com.shenlanbao.sqldemo.model.PerformanceStatistics;
import com.shenlanbao.sqldemo.model.PerformanceStatisticsQueryParam;
import com.shenlanbao.sqldemo.utils.EasyExcelUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
public class StaticController {

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/get_consultant_id")
    public void getPerform(@RequestBody MultipartFile file) throws Exception {
        List<Consultant> consultants = EasyExcelUtils.readExcel(file, new Consultant());
        String[] queryTime = {"2019年11月份","2019年12月份","2020年01月份","2020年02月份","2020年03月份","2020年04月份","2020年05月份","2020年06月份"};
        StringBuilder sb = new StringBuilder();
        for (Consultant consultant : consultants) {
            Integer id = consultant.getId();
            sb.append(consultant.getName()).append(",");
            sb.append(consultant.getId()).append(",");
            for (String s : queryTime) {
                double conversionByTimeAndId = getConversionByTimeAndId(s, id);
                sb.append(conversionByTimeAndId).append(",");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private double getConversionByTimeAndId(String time, Integer id) throws Exception {
        PerformanceStatisticsQueryParam param = new PerformanceStatisticsQueryParam();
        param.setQueryType("consultant");
        param.setSelectConsultant(String.valueOf(id));

        String origin = time.replace("年", "-").replace("月份", "-01") + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(origin, formatter);

        String timeStart = dateTime.format(formatter);
        String timeEnd = dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX).format(formatter);
        param.setQueryTimeStart(timeStart);
        param.setQueryTimeEnd(timeEnd);
        param.setPageNo(1);
        param.setPageSize(5);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(APPLICATION_JSON);
        headers.set("userId", "1012");

        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://120.78.246.240:80" + "/performance_statistics/tab/orders");
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8090" + "/performance_statistics/tab/orders");
        builder.queryParam("query_time_end",param.getQueryTimeEnd());
        builder.queryParam("query_time_start",param.getQueryTimeStart());
        builder.queryParam("page_no",String.valueOf(param.getPageNo()));
        builder.queryParam("page_size",String.valueOf(param.getPageSize()));
        builder.queryParam("select_consultant",param.getSelectConsultant());
        builder.queryParam("query_type", param.getQueryType());

        ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, httpEntity, String.class);
        String body = exchange.getBody();

        PageResult<PerformanceStatistics> performanceStatisticsPageResult = JSON.parseObject(body, new TypeReference<PageResult<PerformanceStatistics>>() {
        });
        List<PerformanceStatistics> list = performanceStatisticsPageResult.getDatas();
        if (list == null || list.size() == 0) {
            throw new Exception();
        }
        PerformanceStatistics performanceStatistics = list.get(0);
        double conversionPercent = performanceStatistics.getConversionPercent();
        System.out.println(conversionPercent);
        return conversionPercent;
    }
}
