/*
package com.shenlanbao.sqldemo.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConsumer {
    private static String host = "127.0.0.1";
    private static String username = "guest";
    private static String password = "guest";
    private static int port = 5672;

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(username);
            factory.setPassword(password);
            Connection connect = factory.newConnection();
            Channel channel = connect.createChannel();
            AMQP.Queue.DeclareOk declareOK = channel.queueDeclare("test", false, false, false, null);
            Consumer consumer = new Consumer(channel);
        }
    }
}
*/
