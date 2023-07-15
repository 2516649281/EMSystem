package com.chunfeng.controller;

import com.chunfeng.dao.entity.User;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户业务层
     */
    @Autowired
    private IUserService userService;

    /**
     * 登录逻辑
     *
     * @param name     用户名
     * @param password 密码
     * @return JSON
     */
    @PostMapping("/login")
    public JsonRequest<String> login(@RequestParam String name, @RequestParam String password) {
        return userService.login(name, password);
    }


    /**
     * 注册逻辑
     *
     * @param user 用户信息
     * @return JSON
     */
    @PostMapping("/register")
    public JsonRequest<Integer> register(@RequestBody User user) {
        return userService.register(user);
    }
}
