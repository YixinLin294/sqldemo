package com.shenlanbao.sqldemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Consultant {
    @ExcelProperty("name")
    private String name;

    @ExcelProperty("id")
    private Integer id;
}
