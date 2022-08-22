package com.znn.goodsEs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 周闹闹
 * @version 1.0
 */
@SpringBootApplication
public class GoodsEsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GoodsEsApplication.class, args);
        System.out.println("商品Es端启动成功...");
    }
}
