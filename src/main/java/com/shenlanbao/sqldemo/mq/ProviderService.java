/*
package com.shenlanbao.sqldemo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsgForHelloWorldQueue(String msg) {
        System.out.println("普通队列HelloWorld-生产者发送：" + msg);
        amqpTemplate.convertAndSend(RabbitmqConfig.HELLO_WORLD_QUEUE, msg);
    }

    public void sendMsgForWorkQueue(String msg) {
        System.out.println("工作队列-生产者发送：" + msg);
        amqpTemplate.convertAndSend(RabbitmqConfig.WORK_QUEUE, msg);
    }

    public void sendMsgForFanoutExchange(String msg) {
        System.out.println("FanoutExchange交换机-生产者发送：" + msg);
        amqpTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE, null, msg);
    }

    public void sendMsgForDirectExchange(String msg) {
        System.out.println("DirectExchange交换机-生产者发送：" + msg);
        amqpTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE, RabbitmqConfig.ROUTING_KEY, msg);
    }

    public void sendMsgForTopicExchange(String msg, String wildcard) {
        System.out.println("TopicExchange交换机-生产者发送：" + msg);
        amqpTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE, wildcard, msg);
    }
}
*/
