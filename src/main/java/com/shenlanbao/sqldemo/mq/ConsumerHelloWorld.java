/*
package com.shenlanbao.sqldemo.mq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerHelloWorld {

    @Value("5672")
    private String port;

    @RabbitListener(queues=RabbitmqConfig.HELLO_WORLD_QUEUE)
    public void recive(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        System.out.println(msg);
        channel.basicAck(deliveryTag, false);
    }
}
*/
