package com.shenlanbao.sqldemo.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelUtils {

    public static <T> List<T> readExcel(String file, T data) throws IOException {
        List<T> dataList = EasyExcel.read(file, data.getClass(), new DataListener<T>()).sheet().doReadSync();
        return dataList;
    }

    public static <T> List<T> readExcel(MultipartFile file, T data) throws IOException {
        List<T> dataList = EasyExcel.read(file.getInputStream(), data.getClass(), new DataListener<T>()).sheet().doReadSync();
        return dataList;
    }

    public static <T> void writeExcel(String path, List<T> data) {
        Type type = data.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            EasyExcel.write(path, clazz).sheet().doWrite(data);
        }
    }
}


class DataListener<T> extends AnalysisEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataListener.class);
    List<T> list = new ArrayList<>();

    public DataListener() {}

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(o));
        list.add((T) o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
