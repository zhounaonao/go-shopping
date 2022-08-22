package com.znn.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 周闹闹
 * @version 1.0
 */
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UserApplication.class, args);
        System.out.println("用户系统启动成功...");
    }

}
