package com.shenlanbao.sqldemo.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateConverter implements Converter<LocalDate> {

    @Override
    public Class supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public LocalDate convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Date javaDate = HSSFDateUtil.getJavaDate(cellData.getNumberValue().doubleValue(), false);
        LocalDate localDate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    @Override
    public CellData convertToExcelData(LocalDate localDate, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        double excelDate = HSSFDateUtil.getExcelDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return new CellData(excelDate);
    }
}

