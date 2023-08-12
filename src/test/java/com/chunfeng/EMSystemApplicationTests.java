package com.chunfeng;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.Role;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IRoleService;
import com.chunfeng.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class EMSystemApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Test
    void login() {
        System.out.println("开始测试!");
        System.out.println(userService.login("root", "12345678"));
    }

    @Test
    void testUser() {
        Role role = new Role();
        role.setId("1");
        List<Permission> permissionList = new ArrayList<>();
        Permission permission = new Permission();
        permission.setId("1e1426fad12b4e09bc58d7fe2e5f4e19");
        Permission permission2 = new Permission();
        permission2.setId("125235b9c2fa400983a2adf55fc0218f");
        permissionList.add(permission);
        role.setPermissionList(permissionList);
        JsonRequest<Integer> request = roleService.updateOneRole(role);
        System.out.println(request);
    }
}
