package com.shenlanbao.sqldemo;

import com.shenlanbao.sqldemo.echo.DefaultEchoService;
import com.shenlanbao.sqldemo.echo.EchoService;
import com.shenlanbao.sqldemo.echo.EchoWebSocketHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

//@EnableScheduling
@SpringBootApplication
@MapperScan(value = "com.shenlanbao.sqldemo.mapper")
@EnableAsync
@EnableWebSocket
public class SqldemoApplication implements WebSocketConfigurer {

    public static void main(String[] args) throws IOException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> resources =
                systemClassLoader.getResources("shenlanbao/sqldemo" +
                        "");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url.toString());
        }

        final ConfigurableApplicationContext run = SpringApplication.run(SqldemoApplication.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/echo").withSockJS();
    }

    @Bean
    public EchoService echoService() {
        return new DefaultEchoService("You said \"%s\" !");
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(echoService());
    }
}
