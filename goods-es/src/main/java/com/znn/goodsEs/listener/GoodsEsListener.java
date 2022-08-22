package com.znn.goodsEs.listener;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author 周闹闹
 * @version 1.0
 */
public class GoodsEsListener {

    // 新增或更新
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("save"),
            exchange = @Exchange(value = "exchange", type = "topic"),
            key = {"save"}
    ))
    public void save(Long id) {

    }

}
