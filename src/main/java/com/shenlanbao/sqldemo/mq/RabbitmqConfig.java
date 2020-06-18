package com.shenlanbao.sqldemo.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.StringTokenizer;

@Configuration
public class RabbitmqConfig {

    public static final String HELLO_WORLD_QUEUE = "hello_world_queue";

    public static final String WORK_QUEUE = "work_queue";

    public static final String FANOUT_QUEUE_ONE = "fanout_queue_one";

    public static final String FANOUT_QUEUE_TWO = "fanout_queue_two";

    public static final String DIRECT_QUEUE_ONE = "direct_queue_one";

    public static final String DIRECT_QUEUE_TWO = "direct_queue_two";

    public static final String TOPIC_QUEUE_ONE = "topic_queue_one";

    public static final String TOPIC_QUEUE_TWO = "topic_queue_two";

    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    public static final String DIRECT_EXCHANGE = "direct_exchange";

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String ROUTING_KEY = "my_rounting_key";

    public static final String TOPICS_ONE = "my_topic.*";

    public static final String TOPICS_MORE = "my_topic.#";

    @Bean
    public Queue helloWorldQueue(){
        return new Queue(HELLO_WORLD_QUEUE, true, false, false);
    }

    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE, true);
    }

    @Bean
    public Queue fanoutQueueOne() {
        return new Queue(FANOUT_QUEUE_ONE, true);
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue(FANOUT_QUEUE_TWO, true);
    }

    @Bean
    public Queue directQueueOne() {
        return new Queue(DIRECT_QUEUE_ONE, true);
    }

    @Bean
    public Queue directQueueTwo() {
        return new Queue(DIRECT_QUEUE_TWO, true);
    }

    @Bean
    public Queue topicQueueOne() {
        return new Queue(TOPIC_QUEUE_ONE, true);
    }

    @Bean
    public Queue topicQueueTwo() {
        return new Queue(TOPIC_QUEUE_TWO, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE, true, true);
    }

    @Bean
    public Binding bindingFanoutQueueOne(Queue fanoutQueueOne, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueOne).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutQueueTwo(Queue fanoutQueueTwo, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueTwo).to(fanoutExchange);
    }

    @Bean
    public Binding bindingDirectQueueOne(Queue directQueueOne, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueOne).to(directExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingDirectQueueTwo(Queue directQueueTwo, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueTwo).to(directExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingTopicQueueOne(Queue topicQueueOne, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueOne).to(topicExchange).with(TOPICS_ONE);
    }

    @Bean
    public Binding bindingTopicQueueTwo(Queue topicQueueTwo, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueTwo).to(topicExchange).with(TOPICS_MORE);
    }
}
