package com.znn.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周闹闹
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String list() {
        return "userlist";
    }


}
