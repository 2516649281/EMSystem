package com.chunfeng;

import com.chunfeng.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class EMSystemApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void login() {
        System.out.println("开始测试!");
        System.out.println(userService.login("root", "12345678"));
    }

    @Test
    void testUser() {
    }
}
