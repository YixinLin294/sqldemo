package com.shenlanbao.sqldemo.service;

import com.shenlanbao.sqldemo.model.db.OrderDB;

import java.util.List;

public interface OrderService {
    List<OrderDB> findUnfinishedQuestionnaireOrder();
}
