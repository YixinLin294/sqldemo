package com.shenlanbao.sqldemo.service.impl;

import com.shenlanbao.sqldemo.mapper.TestMapper;
import com.shenlanbao.sqldemo.model.SingleTable;
import com.shenlanbao.sqldemo.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
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

    @Override
    public void renameBilibili(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            log.error("this path is not exists or this path is not a directory: {}", path);
            return;
        }
        File[] files = file.listFiles();
        if (files == null) {
            log.error("listFiles error");
            return;
        }
        for (File file1 : files) {
            try {
                String name = file1.getName();
                String[] split = name.split("@");
                if (split.length != 3) {
                    log.error("file name is not correct: {}", name);
                    return;
                }
                // 哔哩哔哩截图文件命名规则，第一个@分隔的是截图时视频所在的时长(毫秒值) 和 截图时的现实时间(unix时间戳)
                String timestamp = split[1];
                String videotime = split[0];
                // unix时间戳转 日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时mm分ss秒");
                // 视频所在时长(毫秒值)转时分秒
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH时mm分ss秒");
                sdf2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));

                long ts = Long.valueOf(timestamp).longValue() * 1000;
                String date = sdf.format(new Date(ts));

                long vt = Long.valueOf(videotime).longValue();
                String video = sdf2.format(vt);

                String suffix = split[2].split("\\.")[1];

                String newName = date + " " + video + " " + name;

                boolean success = file1.renameTo(new File(file, newName));

                if (success) {
                    log.info("截图重命名成功，原文件名: {}, 现文件名： {}", name, file1.getName());
                } else {
                    log.info("截图重命名失败，原文件名: {}, 现文件名： {}", name, file1.getName());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String name = "946507@1594316453@2.png";
        String[] split = name.split("@");
        String timestamp = split[1];
        String videotime = split[0];
        // unix时间戳转 日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时mm分ss秒");
        // 视频所在时长(毫秒值)转时分秒
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH时mm分ss秒");
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));

        long ts = Long.valueOf(timestamp).longValue() * 1000;
        String date = sdf.format(new Date(ts));

        long vt = Long.valueOf(videotime).longValue();
        String video = sdf2.format(vt);

        String suffix = split[2].split("\\.")[1];

        String newName = date + " " + video + "." + suffix;

        System.out.println(name + "  " + newName);
    }
}
