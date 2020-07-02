package com.shenlanbao.sqldemo.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SingleProductData {

    @ExcelProperty("投保单号")
    private String insureNum;

    @ExcelIgnore
    private Integer consultantId;

    @ExcelProperty("新规划师")
    private String consultantName;
}
