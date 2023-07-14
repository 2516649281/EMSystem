package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户的业务层实现类
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements IUserService {

    /**
     * 数据层注入
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询逻辑
     *
     * @param user 条件
     * @return JSON
     */
    @Override
    public JsonRequest<List<User>> selectUser(User user) {
        return null;
    }

    /**
     * 登录逻辑
     *
     * @param name     用户名
     * @param password 密码
     * @return JSON
     */
    @Override
    public JsonRequest<Long> login(String name, String password) {
        return null;
    }

    /**
     * 注册逻辑
     *
     * @param user 用户信息
     * @return JSON
     */
    @Override
    public JsonRequest<Integer> register(User user) {
        return null;
    }

    /**
     * 修改一条用户信息
     *
     * @param user 用户信息
     * @return JSON
     */
    @Override
    public JsonRequest<Integer> updateOneUser(User user) {
        return null;
    }

    /**
     * 批量删除用户信息
     *
     * @param longs 用户ID
     * @return JSON
     */
    @Override
    public JsonRequest<Integer> deleteUser(Long[] longs) {
        return null;
    }
}
