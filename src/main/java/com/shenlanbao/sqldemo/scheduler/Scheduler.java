package com.shenlanbao.sqldemo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 2000)
    public void testTasks1() {
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0/2 * * ? * *")
    public void testTasks2() {
        System.out.println("定时任务执行时间2：" + dateFormat.format(new Date()));
    }
}
