package com.shenlanbao.sqldemo.mapper;

import com.shenlanbao.sqldemo.model.db.OrderDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {


    List<OrderDB> findUnfinishedQuestionnaireOrder();
}
