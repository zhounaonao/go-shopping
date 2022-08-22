package com.znn.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 周闹闹
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("com.znn.goods.mapper")
public class GoodsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplication().run(GoodsApplication.class, args);
        System.out.println("商品系统启动成功");
    }
}
