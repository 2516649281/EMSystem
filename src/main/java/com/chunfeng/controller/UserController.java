package com.chunfeng.controller;

import com.chunfeng.dao.entity.User;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * @param username 用户名
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
    public JsonRequest<List<User>> lookUser(
            @ApiParam(value = "条件", required = true)
            User user) {
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
    public JsonRequest<List<User>> lookAllUser() {
        return userService.lookAllUser();
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
    public JsonRequest<Integer> deleteUser(
            @ApiParam(value = "待删除的用户ID", required = true)
            @RequestBody String[] ids) {
        return userService.deleteUser(ids);
    }

    /**
     * 用户退出登录
     *
     * @param token 令牌
     * @return JSON
     */
    @GetMapping("/logout")
    @ApiOperation(value = "用户退出接口")
    @ApiResponses(value = {
            @ApiResponse(code = 406, message = "退出失败!"),
            @ApiResponse(code = 200, message = "退出成功!")})
    public JsonRequest<Boolean> logout(
            @ApiParam(value = "登录的token", required = true)
            @RequestParam String token) {
        return userService.logout(token);
    }

    /**
     * 头像显示
     *
     * @param userId 用户ID
     * @return 文件响应流
     */
    @GetMapping("/avatar")
    @ApiOperation(value = "下载用户头像")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public ResponseEntity<Resource> avatarDownload(
            @ApiParam(value = "用户ID", required = true)
            @RequestParam String userId) {
        return userService.avatarDownload(userId);
    }

    /**
     * 头像上传
     *
     * @param file   头像文件
     * @param userId 用户ID
     * @return 是否成功
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传用户头像")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Boolean> avatarUpload(
            @ApiParam(value = "头像文件", required = true)
            @RequestBody MultipartFile file,
            @ApiParam(value = "操作的用户ID", required = true)
            @RequestParam String userId) {
        return userService.avatarUpload(file, userId);
    }
}
