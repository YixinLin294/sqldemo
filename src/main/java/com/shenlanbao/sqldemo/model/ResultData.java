package com.shenlanbao.sqldemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ResultData {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("联系电话")
    private String phone;
    @ExcelProperty("支付时间")
    private Date paytime;
    @ExcelProperty("是否过犹豫期")
    private String hesitatePeriod;
    @ExcelProperty("是否为长险")
    private String longInsurance;
}
