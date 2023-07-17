package com.chunfeng.service;

import com.chunfeng.dao.entity.User;
import com.chunfeng.result.JsonRequest;
import springfox.documentation.spring.web.json.Json;

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
}
