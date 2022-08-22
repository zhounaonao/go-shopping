package com.znn.goods.config.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 周闹闹
 * @version 1.0
 */
@Configuration
public class GoodsMq {

    // 新增或修改的queue
    @Bean
    public Queue saveQueue() {
        return new Queue("save.queue");
    }

    @Bean
    public Exchange topicExchange() {
        return new TopicExchange("topic.exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(saveQueue()).to(topicExchange()).with("save.#").noargs();
    }



}
