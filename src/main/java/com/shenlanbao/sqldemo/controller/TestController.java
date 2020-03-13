package com.shenlanbao.sqldemo.controller;

import com.shenlanbao.sqldemo.model.Template;
import com.shenlanbao.sqldemo.service.TemplateService;
import com.shenlanbao.sqldemo.service.TestService;
import com.shenlanbao.sqldemo.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TemplateService templateService;

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

    @GetMapping("template")
    public void template() {
        ExcelUtils excelUtils = new ExcelUtils();
        List<Template> templateList = new ArrayList<>();
        String contentSql1 = "INSERT INTO `verbal_trick_template_content` (subtitle, content, title_id) values ('','',);";
        String contentSql2 = "INSERT INTO `verbal_trick_template_content` (content, title_id) values ('',);";
        List<String> sqls = new ArrayList<>();
        for (int sheetIndex = 1; sheetIndex < 6; sheetIndex++) {
            ArrayList<Map<String, String>> result = excelUtils.readExcelToObj("C:\\Users\\slb\\Downloads\\异议处理0220.xlsx", sheetIndex);
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
}
