package com.shenlanbao.sqldemo.service.impl;

import com.shenlanbao.sqldemo.mapper.TestMapper;
import com.shenlanbao.sqldemo.model.SingleTable;
import com.shenlanbao.sqldemo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public Integer insertBatch() {
        List<SingleTable> singleTables = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            SingleTable singleTable = new SingleTable();
            singleTable.setKey1(UUID.randomUUID().toString());
            singleTable.setKey2(random.nextInt());
            singleTable.setKey3(UUID.randomUUID().toString());
            singleTable.setKeyPart1(UUID.randomUUID().toString());
            singleTable.setKeyPart2(UUID.randomUUID().toString());
            singleTable.setKeyPart3(UUID.randomUUID().toString());
            singleTable.setCommonField(UUID.randomUUID().toString());
            singleTables.add(singleTable);
        }
        return testMapper.insertBatch(singleTables);
    }

    @Override
    public Integer insert() {
        Random random = new Random();
        SingleTable singleTable = new SingleTable();
        singleTable.setKey1(UUID.randomUUID().toString());
        singleTable.setKey2(random.nextInt());
        singleTable.setKey3(UUID.randomUUID().toString());
        singleTable.setKeyPart1(UUID.randomUUID().toString());
        singleTable.setKeyPart2(UUID.randomUUID().toString());
        singleTable.setKeyPart3(UUID.randomUUID().toString());
        singleTable.setCommonField(UUID.randomUUID().toString());
        return testMapper.insert(singleTable);
    }

    @Override
    @Async
    public void testAsync() {
        try {
            throw new InterruptedException("test");
//            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after sleep");
    }
}
