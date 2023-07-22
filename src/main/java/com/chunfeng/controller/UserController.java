package com.chunfeng.controller;

import com.chunfeng.dao.entity.User;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户控制类", tags = {"用户操作访问接口"})
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
    @ApiOperation(value = "用户登录接口")
    @ApiResponses(value = {
            @ApiResponse(code = 402, message = "登录失败!"),
            @ApiResponse(code = 200, message = "登录成功!")})
    public JsonRequest<String> login(
            @ApiParam(value = "用户名", required = true)
            @RequestParam String username,
            @ApiParam(value = "密码", required = true)
            @RequestParam String password) {
        return userService.login(username, password);
    }

    /**
     * 注册逻辑
     *
     * @param user 用户信息
     * @return JSON
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiResponses({
            @ApiResponse(code = 402, message = "注册失败!"),
            @ApiResponse(code = 200, message = "注册成功!")
    })
    public JsonRequest<Integer> register(
            @ApiParam(value = "用户数据", required = true)
            @RequestBody User user) {
        return userService.register(user);
    }


    /**
     * 分类查询用户
     *
     * @param user 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类查询用户")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:user:select','user:user:select')")
    public JsonRequest<List<User>> lookUser(
            @ApiParam(value = "条件", required = true)
            @RequestParam User user) {
        return userService.lookUser(user);
    }

    /**
     * 查看所有用户
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查看所有用户")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAuthority('sys:user:select')")
    public JsonRequest<List<User>> lookAllUser() {
        return lookUser(new User());
    }

    /**
     * 修改一条用户信息
     *
     * @param user 用户信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条用户信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:user:update','user:user:update')")
    public JsonRequest<Integer> updateOneUser(
            @ApiParam(value = "待修改的用户数据", required = true)
            @RequestBody User user) {
        return userService.updateOneUser(user);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 用户ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除用户信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:user:delete','user:user:delete')")
    public JsonRequest<Integer> deleteUser(
            @ApiParam(value = "待删除的用户ID", required = true)
            @RequestBody String[] ids) {
        return userService.deleteUser(ids);
    }
}
