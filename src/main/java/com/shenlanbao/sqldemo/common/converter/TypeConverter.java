package com.shenlanbao.sqldemo.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.shenlanbao.sqldemo.common.model.OrderTypeEnum;

public class TypeConverter implements Converter<String> {

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
        String stringValue = cellData.getStringValue();
        if("客服咨询".equals(stringValue)) {
            stringValue = "客服";
        }
        String codeByName = OrderTypeEnum.getCodeByName(stringValue);
        return codeByName;
    }

    @Override
    public CellData convertToExcelData(String type, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String nameByCode = OrderTypeEnum.getNameByCode(type);
        if ("客服".equals(nameByCode)) {
            nameByCode = "客服咨询";
        }
        return new CellData(nameByCode);
    }
}

