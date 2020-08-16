package com.shenlanbao.sqldemo.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class PageResult<T> {

    private long total;
//    @JsonProperty("page_no")
    @JSONField(name = "page_no")
    private int pageNum;
//    @JsonProperty("page_size")
    @JSONField(name = "page_size")
    private int pageSize;
//    @JsonProperty("datas")
    @JSONField(name = "datas")
    private List<T> list;

    public static <T> PageResult<T> formatPage(PageInfo<T> pageInfo) {
        PageResult<T> pageResult = new PageResult<>();
        BeanUtils.copyProperties(pageInfo, pageResult);
        return pageResult;
    }

    public static <T> PageResult<T> build(int total, int pageNum, int pageSize, List<T> list) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setList(list);

        return pageResult;
    }

}
