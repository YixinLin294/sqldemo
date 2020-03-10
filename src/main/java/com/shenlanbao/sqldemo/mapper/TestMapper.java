package com.shenlanbao.sqldemo.mapper;

import com.shenlanbao.sqldemo.model.SingleTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {

    Integer insertBatch(List<SingleTable> singleTables);

    Integer insert(SingleTable singleTable);
}
