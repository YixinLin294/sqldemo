package com.shenlanbao.sqldemo.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.shenlanbao.sqldemo.common.model.OrderListTypeEnum;


public class ListTypeConverter implements Converter<String> {

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String codeByName = OrderListTypeEnum.getCodeByName(cellData.getStringValue());
        return codeByName;
    }

    @Override
    public CellData convertToExcelData(String listType, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String nameByCode = OrderListTypeEnum.getNameByCode(listType);
        return new CellData(nameByCode);
    }
}

