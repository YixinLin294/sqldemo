package com.shenlanbao.sqldemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveData {

    @ExcelProperty("序号")
    private Integer number;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("联系电话")
    private String phone;
}
