package com.shenlanbao.sqldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shenlanbao.sqldemo.common.model.ApiResult;
import com.shenlanbao.sqldemo.common.model.PageResult;
import com.shenlanbao.sqldemo.model.*;
import com.shenlanbao.sqldemo.model.db.OrderDB;
import com.shenlanbao.sqldemo.model.dto.OrderAndCustomerDTO;
import com.shenlanbao.sqldemo.model.dto.ProductDTO;
import com.shenlanbao.sqldemo.service.OrderService;
import com.shenlanbao.sqldemo.service.TemplateService;
import com.shenlanbao.sqldemo.service.TestService;
import com.shenlanbao.sqldemo.utils.AESUtil;
import com.shenlanbao.sqldemo.utils.EasyExcelUtils;
import com.shenlanbao.sqldemo.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private OrderService orderService;

    RestTemplate restTemplate = new RestTemplate();

    //    public static final String BASE_URL = "https://consult-dashboard-api.shenlanbao.com";
    public static final String BASE_URL_POLICY = "http://127.0.0.1:8081";
    public static final String BASE_URL_CONSULT = "http://127.0.0.1:8080";

//    public static final String Authorization = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFkb3JGbVQ4Wll5aXpxZWpLanAifQ.eyJqdGkiOiJ1ZU1CT1V1aF9Wc05FdldjTTJfUHlBIiwiaWF0IjoxNTk3NDg0MjA2LCJleHAiOjE1OTc1NzA2MDYsIm5iZiI6MTU5NzQ4NDE0Niwic3ViIjoiMjAwMSIsImF1ZCI6IlNMQiJ9.N1l3b9Egv4JzoYa1OBbaIsT1tskuvGqKJMARJKssCekoz_KeoNLec7m-H4C9ME5dZVtE2wRrU54z-MfW074xeVWhH87g4xJRnrmIGlRJcZlQlKJSdver94MTFvC5HiJh2rhj9MqWPOyMo2IFFG0hyTtyjkhE3AU5sf5pidhFH5iDQg1ZqdlZmeuUoEHZUkYK3KOzHuhfZbJ0IK9WIJiOxSQoeG2B7n9kTQCVDVWT0qigsuq5GROgZV9Wdh5jkjlcukdh3xw8XrqMRJ19myXEze9q2k_VJ9Q0x0pXQUHfimCMJSf_JjJ3635ymae3CHr99BKrN9uWBw5snJNGxdAANA";

    public static final String Authorization = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVmMTBkODI0MDJlMjQ2NmY4N2I5YWY3NzlhY2Q4NWViIn0.eyJqdGkiOiJsRC04MUJMaTZvVnVxUjA1VjZwLXRRIiwiaWF0IjoxNTk3NTAyNTA1LCJleHAiOjE1OTc1ODg5MDUsIm5iZiI6MTU5NzUwMjQ0NSwic3ViIjoiMTAwMiIsImF1ZCI6IlNMQiJ9.b529BHCzrlROUV9pLBJf94ALR4lJRu3f4LR7obb1DS4YWntkUixkx5OC93ZemqScDoVoJIrLwvThELU1ni-EM-Uaj6ArUkASG3xdQakQiimhXVTC2M_wtD6WajlBn0WWsfy7W_tMnAYA_Ny_UhiwcLxT83xIb0GTC7caye4INoytoLGF55dZOBbz450xGf5Ap8lsC0oCtu1gnLkCQe72CLyhLKrUeDrrm3-AFgwGo0M92bDNCETTcHTXqjASrIshJxMbPnNEJk5GlLatpai5DNwvI67Z9uN1Pae-CwFjunB4LnGzs8Z_VSR5YkkeyPJlxBOCgD4fWPzYybZ6kIENDg";

    public static final Integer userId = 1012;



    @GetMapping(value = "/hello")
    public String hello() {
        System.out.println("热部署");
        return "hello_world_test";
    }

    @GetMapping("/insert_batch")
    public Integer insertBatch() {
        return testService.insertBatch();
    }

    @GetMapping("insert")
    public Integer insert() {
        return testService.insert();
    }

    /**
     * 话术
     */
    @PostMapping("template")
    public void template(@RequestParam MultipartFile file) {
        ExcelUtils excelUtils = new ExcelUtils();
        List<Template> templateList = new ArrayList<>();
        String contentSql1 = "INSERT INTO `verbal_trick_template_content` (subtitle, content, title_id) values ('','',);";
        String contentSql2 = "INSERT INTO `verbal_trick_template_content` (content, title_id) values ('',);";
        List<String> sqls = new ArrayList<>();
        for (int sheetIndex = 1; sheetIndex < 6; sheetIndex++) {
            ArrayList<Map<String, String>> result = excelUtils.readExcelToObj(file, sheetIndex);
            // 插入内容
            for (Map<String, String> map : result) {
                String question = map.get("question");
                Integer titleId = templateService.getTitleId(question, sheetIndex);
                Template template = new Template();
                template.setTitleId(titleId);
                String answer = map.get("answer");
                if (answer.startsWith("【")) {
                    int end = answer.indexOf("】");
                    String subTitle = answer.substring(1, end);
                    String content = answer.substring(end + 1);
                    template.setContent(content);
                    template.setSubtitle(subTitle);
                } else {
                    template.setContent(answer);
                }
                templateList.add(template);
            }
        }
        for (Template template : templateList) {
            if(template.getSubtitle() != null) {
                String sql = contentSql1.substring(0, 83) + template.getSubtitle() + "','" + template.getContent() + "', "
                        + template.getTitleId() + ");";
                sqls.add(sql);
            } else {
                String sql = contentSql2.substring(0, 73) + template.getContent() + "', " + template.getTitleId() + ");";
                sqls.add(sql);
            }
        }
        for (String sql : sqls) {
            System.out.println(sql);
        }
    }

    @PostMapping("/data_abstraction")
    public void dataAbstraction(@RequestParam MultipartFile file) throws IOException {
        List<ActiveData> activeData = new ArrayList<>();
        activeData = EasyExcelUtils.readExcel(file, new ActiveData());
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT\n" +
                "case ");
        for (ActiveData activeDatum : activeData) {
            sb.append("when s.applicant_phone like '");
            sb.append(activeDatum.getPhone());
            sb.append("' then '");
            sb.append(activeDatum.getName());
            sb.append("' \n");
        }
        sb.append("end as '姓名',");
        sb.append("\ts.applicant_phone AS '联系电话',\n" +
                "\ts.payment_time AS '支付时间',\n" +
                "CASE\n" +
                "\t\t\n" +
                "\t\tWHEN DATE_ADD( p.start_date, INTERVAL p.period_hesitation DAY ) <= NOW( ) THEN '是' WHEN DATE_ADD( p.start_date, INTERVAL p.period_hesitation DAY ) > NOW( ) THEN\n" +
                "\t\t'否' \n" +
                "\tEND AS '是否过犹豫期',\n" +
                "CASE\n" +
                "\t\t\n" +
                "\t\tWHEN p.long_insurance = 1 THEN\n" +
                "\t\t'长险' \n" +
                "\t\tWHEN p.long_insurance = 0 THEN\n" +
                "\t\t'短险' \n" +
                "\tEND AS '是否为长险' \n" +
                "FROM\n" +
                "\tpolicy AS p\n" +
                "\tLEFT JOIN insurance AS s ON s.id = p.insurance_id \n" +
                "WHERE\n" +
                "\tp.long_insurance = 1 \n" +
                "\tAND s.effectiveness = 'EFFECTIVE' \n" +
                "\tAND s.applicant_phone IN (");
        for (ActiveData activeDatum : activeData) {
            sb.append("'");
            sb.append(activeDatum.getPhone());
            sb.append("', \n");
        }
        if (activeData != null && activeData.size() != 0) {
            sb.delete(sb.length() - 3, sb.length());
        }
        sb.append("\n) order by field(s.applicant_phone,");
        for (ActiveData activeDatum : activeData) {
            sb.append("'");
            sb.append(activeDatum.getPhone());
            sb.append("', \n");
        }
        sb.delete(sb.length()-3, sb.length());
        sb.append(");");
        System.out.println(sb.toString());
    }

    @PostMapping("/excel_match")
    public void excelMatch(@RequestParam MultipartFile file1, @RequestParam MultipartFile file2) throws IOException {
        List<ResultData> resultData;
        List<ActiveData> activeData;
        resultData =  EasyExcelUtils.readExcel(file1, new ResultData());
        activeData = EasyExcelUtils.readExcel(file2, new ActiveData());
        HashMap<String, String> hashMap = new HashMap<>();
        for (ActiveData activeDatum : activeData) {
            hashMap.put(activeDatum.getPhone(), activeDatum.getName());
        }
        for (ResultData resultDatum : resultData) {
            resultDatum.setName(hashMap.get(resultDatum.getPhone()));
        }
        String fileName = file1.getOriginalFilename();
        EasyExcel.write(fileName, ResultData.class).sheet().doWrite(resultData);
    }

    @GetMapping("/mongo_find")
    public void mongoFind() {
        String before = "db.questionnaire.find({\n" +
                "        orderId: {\n" +
                "            $in: [" ;
        String after = "]\n" +
                "        }\n" +
                "    })\n" +
                "    .sort({\n" +
                "        _id: -1\n" +
                "    })\n" +
                "    .limit(100)";
        List<OrderDB> orderDBList = orderService.findUnfinishedQuestionnaireOrder();
        StringBuilder sb = new StringBuilder();
        sb.append(before);
        for (OrderDB orderDB : orderDBList) {
            sb.append(orderDB.getId()).append(", ");
        }
        if (orderDBList != null && orderDBList.size() != 0) {
            sb.delete(sb.length()-2, sb.length());
        }
        sb.append(after);
        System.out.println(sb.toString());
    }

    @PostMapping("/excel_upload")
    public void excelUpload(@RequestParam MultipartFile file1) throws IOException {
        List<OrderAndCustomerDTO> orderAndCustomerDTOS = EasyExcelUtils.readExcel(file1, new OrderAndCustomerDTO());
        String before = "select * from `order` as o left join `customer` as c on c.id = o.`customer_id` where c.`phone` in (";
        String after = ");";
        StringBuilder sb = new StringBuilder();
        sb.append(before);
        for (OrderAndCustomerDTO orderAndCustomerDTO : orderAndCustomerDTOS) {
            sb.append("'");
            sb.append(orderAndCustomerDTO.getPhone());
            sb.append("',");
        }
        if (orderAndCustomerDTOS != null && orderAndCustomerDTOS.size() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append(after);
        System.out.println(sb.toString());
    }

    @PostMapping("/excel_in")
    public void jointIn(@RequestParam MultipartFile file1) throws IOException {
        List<OrderAndCustomerDTO> orderAndCustomerDTOS = EasyExcelUtils.readExcel(file1, new OrderAndCustomerDTO());
        String before = "select * from ( select p.product_name, pc.renewal_rule, p.end_date, s.partner_id as supplier, s.insure_num, ri.offline_flag, ri.latest_renewal_date, ri.origin_url, ri.renewal_url from insurance as s left join policy as p on p.insurance_id = s.id left join product_coverage as pc on p.product_name = pc.product_name left join renewal_info as ri on ri.insure_num = s.insure_num WHERE s.`effectiveness` = 'EFFECTIVE' AND p.`coverage_desc` IN ('医疗险','防癌医疗险','意外险') AND p.`long_insurance` IS false and s.`applicant_phone` in (";
        String after = ")) as t1 order by end_date desc;";
        StringBuilder sb = new StringBuilder();
        sb.append(before);
        for (OrderAndCustomerDTO orderAndCustomerDTO : orderAndCustomerDTOS) {
            sb.append("'");
            sb.append(orderAndCustomerDTO.getPhone());
            sb.append("',");
        }
        if (orderAndCustomerDTOS != null && orderAndCustomerDTOS.size() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append(after);
        System.out.println(sb.toString());
    }

    @PostMapping("/encode_base64")
    public Base64File encodeBase64(@RequestParam MultipartFile file) {
        byte[] data = null;
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64.Encoder encoder = Base64.getEncoder();
        Base64File base64File = new Base64File();
        base64File.setFile(encoder.encodeToString(data));
        return base64File;
    }
    @PostMapping("/aes_encrypt")
    public String aesEncrypt(@RequestBody String original) {
        try {
            String encrypt = AESUtil.Encrypt(original);
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/aes_decrypt")
    public String aesDecrypt(@RequestBody String encrypt) {
        try {
            String original = AESUtil.Decrypt(encrypt);
            return original;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping("/test")
    public Map test(@RequestBody Integer max) throws URISyntaxException {
        String s = "test";
        List<String> strings = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
//            sb.append(s).append(s);
//            strings.add(s);
            map.put(s + i, s);
        }
        return map;
/*        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(new URI("http://localhost:9999/test2"), sb.toString(), String.class);
        String body = stringResponseEntity.getBody();
        System.out.println(body);*/
/*        restTemplate.postForEntity(new URI("http://localhost:9999/test3"), strings, String.class);*/

    }

    @PostMapping("/test2")
    public String test2(@RequestBody String test) {
        System.out.println(test);
        return "success";
    }

    @PostMapping("/test3")
    public String test3(@RequestBody List<String> strings) {
        System.out.println(strings.size());
        return "success2";
    }

    @PostMapping("/test4")
    public String test4(@RequestBody Map<String, String> stringMap) {
        System.out.println(stringMap.size());
        return "success2";
    }

    @GetMapping("/test5")
    public String test5(@RequestParam("max") Integer max) {
        HttpResponse<String> response;
        response = this.getResponseResult( max, "/test");
        System.out.println(response.body());
        return JSONObject.toJSONString(response.body());
    }

    private HttpResponse<String> getResponseResult(Integer max, String uri) {
        String json = JSONObject.toJSONString(max);

        HttpClient client = HttpClient.newBuilder().build();

        String url = "http://localhost:8080" + uri;

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/excel_util_test")
    public void excelUtil() {
        List<ActiveData> list = new ArrayList<>();
        list.add(new ActiveData(1,"11", "111"));
        list.add(new ActiveData(1,"11", "111"));
        list.add(new ActiveData(2,"22", "222"));
        EasyExcelUtils.writeExcel("test.xlsx", list);
    }

    @PostMapping("/excel_single_product")
    public void excelSingleProduct(@RequestBody MultipartFile file) throws IOException {
        List<SingleProductData> singleProductDataList = EasyExcelUtils.readExcel(file, new SingleProductData());
        HashMap<String, Integer> consultantIdMap = new HashMap<>();
        consultantIdMap.put("林新",1060    );
        consultantIdMap.put("王淑梅",1061  );
        consultantIdMap.put("任一平",1062  );
        consultantIdMap.put("蒲瑜",1063    );
        consultantIdMap.put("卢亭秀",1064  );
        consultantIdMap.put("林海花",1065  );
        consultantIdMap.put("陈霞",1066    );
        consultantIdMap.put("谢春华",1067  );
        consultantIdMap.put("罗喻枫",1068  );
        consultantIdMap.put("陈香香",1095  );
        consultantIdMap.put("史方圆",1096  );
        consultantIdMap.put("邱宝欣",1102  );
        consultantIdMap.put("熊国强",1103  );
        consultantIdMap.put("李东",1147    );
        consultantIdMap.put("蔡立儿",1180  );
        for (SingleProductData singleProductData : singleProductDataList) {
            String consultantName = singleProductData.getConsultantName();
            Integer consultantId = consultantIdMap.get(consultantName);
            singleProductData.setConsultantId(consultantId);
            StringBuilder sb = new StringBuilder();
            sb.append("update insurance set group_id = ");
            sb.append(119);
            sb.append(" where insure_num = ");
            sb.append("\"");
            sb.append(singleProductData.getInsureNum()).append("\";");
            System.out.println(sb.toString());
        }

        for (SingleProductData singleProductData : singleProductDataList) {
            String consultantName = singleProductData.getConsultantName();
            Integer consultantId = consultantIdMap.get(consultantName);
            singleProductData.setConsultantId(consultantId);
            StringBuilder sb = new StringBuilder();
            sb.append("update insurance set consultant_id = ");
            sb.append(singleProductData.getConsultantId());
            sb.append(" where insure_num = ");
            sb.append("\"");
            sb.append(singleProductData.getInsureNum()).append("\";");
            System.out.println(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select * from insurance where insure_num in (");
        for (SingleProductData singleProductData : singleProductDataList) {
            sb.append("\"").append(singleProductData.getInsureNum()).append("\", \n");
        }
        sb.delete(sb.length()-3, sb.length());
        sb.append(");");
        System.out.println(sb.toString());
    }

    @GetMapping("/rename/bilibili")
    public void renameBilibili(@RequestParam String path) {
        testService.renameBilibili(path);
    }

    @GetMapping("pressure")
    public void pressureTest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", Authorization);
        httpHeaders.add("userId", userId.toString());
        HttpEntity<String> parameters = new HttpEntity<>(httpHeaders);

        String productsUrl = BASE_URL_CONSULT +  "/products?page_size=10000&alternative=false";
        ResponseEntity<String> exchange = restTemplate.exchange(productsUrl, HttpMethod.GET, parameters, String.class);
        String body = exchange.getBody();
//        SerializeConfig config = new SerializeConfig();
//        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        ApiResult<com.shenlanbao.sqldemo.common.model.PageResult<ProductDTO>> pageResultApiResult = JSONObject.parseObject(body, new TypeReference<ApiResult<com.shenlanbao.sqldemo.common.model.PageResult<ProductDTO>>>() {
        });

        com.shenlanbao.sqldemo.common.model.PageResult<ProductDTO> data = ((PageResult<ProductDTO>) pageResultApiResult.getData());

        List<ProductDTO> list = data.getList();
        System.out.println("size: " + list.size());
        for (int i = 0; i < 5; i++) {
            for (ProductDTO productDTO : list) {
                new Thread(() ->
                {
                    Integer id = productDTO.getId();
                    String tryUrl = BASE_URL_CONSULT + "/ins_products/" + id + "/trial_settings?insurant_date_of_birth=1990-01-09&insurant_social_security=%E6%98%AF&applicant_date_of_birth=1990-01-09&applicant_gender=%E5%A5%B3&insurant_gender=%E5%A5%B3&for_self=true";
                    ResponseEntity<String> exchange1 = restTemplate.exchange(tryUrl, HttpMethod.GET, parameters, String.class);
//             String body1 = exchange.getBody();
//             System.out.println(body1);
//             System.out.println(Thread.currentThread().getName());
                    System.out.println(exchange1.getStatusCodeValue());
                }, productDTO.getId().toString()
                ).start();
            }
        }
        for (ProductDTO productDTO : list) {
            new Thread(() ->
            {
                Integer id = productDTO.getId();
                String tryUrl = BASE_URL_CONSULT + "/ins_products/" + id + "/trial_settings?insurant_date_of_birth=1990-01-09&insurant_social_security=%E6%98%AF&applicant_date_of_birth=1990-01-09&applicant_gender=%E5%A5%B3&insurant_gender=%E5%A5%B3&for_self=true";
                ResponseEntity<String> exchange1 = restTemplate.exchange(tryUrl, HttpMethod.GET, parameters, String.class);
//             String body1 = exchange.getBody();
//             System.out.println(body1);
//             System.out.println(Thread.currentThread().getName());
               System.out.println(exchange1.getStatusCodeValue());
               }, productDTO.getId().toString()
            ).start();
        }


//        new Gson().fromJson(body,  new TypeToken<ApiResult<PageResult>>)
    }
}
