package com.shenlanbao.sqldemo.service.impl;

import com.shenlanbao.sqldemo.mapper.OrderMapper;
import com.shenlanbao.sqldemo.model.db.OrderDB;
import com.shenlanbao.sqldemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDB> findUnfinishedQuestionnaireOrder() {
        return orderMapper.findUnfinishedQuestionnaireOrder();
    }
}
