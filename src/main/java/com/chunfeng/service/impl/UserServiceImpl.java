package com.chunfeng.service.impl;

import com.alibaba.fastjson.JSON;
import com.chunfeng.dao.domain.UserDetail;
import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.service.IUserService;
import com.chunfeng.utils.RedisClientsUtils;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.TokenUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
public class UserServiceImpl implements IUserService, UserDetailsService {

    /**
     * 数据层注入
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * Redis工具类
     */
    @Autowired
    private RedisClientsUtils redisClientsUtils;

    /**
     * 密码加密器
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 身份验证对象
     */
    @Autowired
    private AuthenticationManager authenticationManager;

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
    public JsonRequest<String> login(String name, String password) {
        //获取UserDetail对象
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(name, password);
        Authentication authenticate = authenticationManager.authenticate(userToken);
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        if (Objects.isNull(userDetail)) {
            log.error("用户名为{}的用户登陆失败!原因:用户名或密码错误!", name);
            return JsonRequest.error(RequestException.LOGIN_ERROR);
        }
        //获取用户实际对象
        User user = userDetail.getUser();
        //生成token对象
        String token = TokenUtils.createToken(user.getId());
        //将用户信息存入Redis数据库
        redisClientsUtils.set(
                "login:" + user.getId(), //key: login:2(数字代表ID)
                JSON.toJSONString(user));//value: 用户序列化后的字符串
        log.info("用户{}登录成功!", name);
        SqlDateUtils.currentUserId = user.getId();
        return JsonRequest.success(token);
    }

    /**
     * 注册逻辑
     *
     * @param user 用户信息
     * @return JSON
     */
    @Override
    public JsonRequest<Integer> register(User user) {
        String name = user.getName();
        User user1 = new User();
        user1.setName(name);
        List<User> users = userMapper.selectAllUser(user1);
        if (users.size() > 0) {
            log.warn("名为{}的用户一再数据库中存在!", name);
            return JsonRequest.error(RequestException.REGISTER_ERROR);
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())//加密后存储
        );
        //填充ID
        user.setId(UIDCreateUtil.getUUId());
        user.setCreateUser("localUser");
        user.setCreateTime(SqlDateUtils.date);
        //向数据库中添加
        Integer column = userMapper.insertUser(user);
        if (column < 0) {
            log.error("名为{}的用户注册失败!原因:数据库异常!", name);
            return JsonRequest.error(RequestException.REGISTER_ERROR);
        }
        log.info("名为{}的用户注册成功!", name);
        return JsonRequest.success(column);
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

    /**
     * 加载用户权限对象
     *
     * @param username 用户名
     * @return 用户权限对象
     * @throws UsernameNotFoundException 如果用户名未使用或错误则抛出此异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息是否存在
        User user = new User();
        user.setName(username);
        List<User> users = userMapper.selectAllUser(user);
        //如果结果为空
        if (users == null || users.isEmpty()) {
            log.error("用户名为{}的用户登陆失败!原因:用户名或密码错误!", username);
            throw new ServiceException(RequestException.LOGIN_ERROR);
        }
        //包装
        User user1 = users.get(0);
        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user1);
        List<String> permission = new ArrayList<>(Arrays.asList("admin", "test"));
        //封装权限信息
        userDetail.setPermission(permission);
        return userDetail;
    }
}
