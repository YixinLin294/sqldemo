package com.shenlanbao.sqldemo.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.shenlanbao.sqldemo.common.converter.GenderConverter;
import com.shenlanbao.sqldemo.common.converter.ListTypeConverter;
import com.shenlanbao.sqldemo.common.converter.TypeConverter;
import lombok.Data;

@Data
public class OrderAndCustomerDTO {

    @ExcelProperty("投保人姓名")
    private String customerName;
    @ExcelProperty(value = "投保人性别", converter = GenderConverter.class)
    private Integer gender;
    @ExcelIgnore
    private Integer added;
    @ExcelIgnore
    private String contractTimePeroid;
    @ExcelProperty("投保人手机号")
    private String phone;
    @ExcelIgnore
    private String wechat;
    @ExcelProperty("险种")
    private String coverageType;
    @ExcelProperty("流水号")
    private String serialNumber;
    @ExcelIgnore
    private String remark;
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("保险产品全称")
    private String productName;
    @ExcelProperty(value = "渠道来源")
    private String channel;
    @ExcelProperty(value = "业务类型", converter = TypeConverter.class)
    private String type;
    @ExcelProperty(value = "名单类型", converter = ListTypeConverter.class)
    private String listType;
    @ExcelIgnore
    private String firstLevelStatus;
    @ExcelIgnore
    private String secondLevelStatus;
    @ExcelIgnore
    private Integer userId;

    @ExcelIgnore
    private String attr; // list detail

//    @ExcelProperty("投保单号")
    @ExcelIgnore
    private String insuranceNum;

//    @ExcelProperty("起保日期")
    @ExcelIgnore
    private String startDate;

    @ExcelProperty("终保日期")
    private String endDate;

//    @ExcelProperty("保单状态")
    @ExcelIgnore
    private String effectiveness;

//    @ExcelProperty(value = "长短险", converter = LongInsuranceConverter.class)
    @ExcelIgnore
    private Integer longInsurance;

    @ExcelIgnore
    private String batchNumber;

}
