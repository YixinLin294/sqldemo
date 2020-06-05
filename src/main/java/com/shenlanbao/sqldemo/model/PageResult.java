package com.shenlanbao.sqldemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class PageResult<T> {

    private long total;
    @JsonProperty("page_no")
    private int pageNo;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonProperty("datas")
    private List<T> datas;

    public static <T> PageResult<T> formatPage(PageInfo<T> pageInfo) {
        PageResult<T> pageResult = new PageResult<>();
        BeanUtils.copyProperties(pageInfo, pageResult);
        return pageResult;
    }

    public static <T> PageResult<T> build(int total, int pageNum, int pageSize, List<T> list) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setPageNo(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setDatas(list);

        return pageResult;
    }

}
