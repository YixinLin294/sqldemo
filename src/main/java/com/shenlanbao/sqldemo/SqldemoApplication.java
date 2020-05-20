package com.shenlanbao.sqldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
@MapperScan(value = "com.shenlanbao.sqldemo.mapper")
@EnableAsync
public class SqldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqldemoApplication.class, args);
    }

}
