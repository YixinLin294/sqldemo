package com.shenlanbao.sqldemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LombokBuilder {

    @ExcelProperty("序号")
    private Integer number;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("联系电话")
    private String phone;

    public static void main(String[] args) {

    }
}
