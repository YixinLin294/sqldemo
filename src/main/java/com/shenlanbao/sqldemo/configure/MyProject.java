package com.shenlanbao.sqldemo.configure;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class MyProject implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        String property = environment.getProperty("server.port");
        System.out.println(property);
    }
}
