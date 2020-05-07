package com.shenlanbao.sqldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.shenlanbao.sqldemo.model.ActiveData;
import com.shenlanbao.sqldemo.model.Base64File;
import com.shenlanbao.sqldemo.model.ResultData;
import com.shenlanbao.sqldemo.model.Template;
import com.shenlanbao.sqldemo.model.db.OrderDB;
import com.shenlanbao.sqldemo.model.dto.OrderAndCustomerDTO;
import com.shenlanbao.sqldemo.service.OrderService;
import com.shenlanbao.sqldemo.service.TemplateService;
import com.shenlanbao.sqldemo.service.TestService;
import com.shenlanbao.sqldemo.utils.EasyExcelUtils;
import com.shenlanbao.sqldemo.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
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
}
