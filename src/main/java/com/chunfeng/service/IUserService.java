package com.chunfeng.service;

import com.chunfeng.dao.entity.User;
import com.chunfeng.result.JsonRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
public interface IUserService {

    /**
     * 分类查询用户
     *
     * @param user 条件
     * @return JSON
     */
    JsonRequest<List<User>> lookUser(User user);

    /**
     * 查看所有用户
     *
     * @return JSON
     */
    JsonRequest<List<User>> lookAllUser();

    /**
     * 按ID值批量查询用户数据
     *
     * @param ids ID值
     * @return JSON
     */
    JsonRequest<List<User>> lookUserById(String[] ids);

    /**
     * 登录逻辑
     *
     * @param name     用户名
     * @param password 密码
     * @return JSON
     */
    JsonRequest<String> login(String name, String password);

    /**
     * 注册逻辑
     *
     * @param user 用户信息
     * @return JSON
     */
    JsonRequest<Integer> register(User user);

    /**
     * 修改一条用户信息
     *
     * @param user 用户信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneUser(User user);

    /**
     * 批量删除用户信息
     *
     * @param ids 用户ID
     * @return JSON
     */
    JsonRequest<Integer> deleteUser(String[] ids);

    /**
     * 头像显示
     *
     * @param userId 用户ID
     * @return 文件响应流
     */
    ResponseEntity<Resource> avatarDownload(String userId);

    /**
     * 头像上传
     *
     * @param file   头像文件
     * @param userId 用户ID
     * @return 是否成功
     */
    JsonRequest<Boolean> avatarUpload(MultipartFile file, String userId);

    /**
     * 用户退出登录
     *
     * @param token 令牌
     * @return JSON
     */
    JsonRequest<Boolean> logout(String token);
}
