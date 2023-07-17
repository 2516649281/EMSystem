package com.chunfeng.service.impl;

import com.alibaba.fastjson.JSON;
import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.PermissionRole;
import com.chunfeng.dao.entity.Role;
import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.PermissionMapper;
import com.chunfeng.dao.mapper.PermissionRoleMapper;
import com.chunfeng.dao.mapper.RoleMapper;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.dao.security.UserDetail;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户的业务层实现
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
     * 用户数据层注入
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * 角色用户层注入
     */
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 权限数据层注入
     */
    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 关系数据层注入
     */
    @Autowired
    private PermissionRoleMapper permissionRoleMapper;
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
     * 分类查询用户
     *
     * @param user 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "select_user", key = "#user.hashCode()")
    public JsonRequest<List<User>> lookUser(User user) {
        List<User> users = userMapper.selectAllUser(user);
        //判断是否为空
        if (users.isEmpty()) {
            log.warn("未查询到任何用户信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条用户数据!", users.size());
        return JsonRequest.success(users);
    }

    /**
     * 查看所有用户
     *
     * @return JSON
     */
    @Override
    public JsonRequest<List<User>> lookAllUser() {
        return lookUser(new User());
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
    @CacheEvict(value = {"select_user", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> register(User user) {
        //根据用户名查询
        String name = user.getName();
        User userData = userMapper.selectAllByName(name);
        //判断用户唯一性
        if (Objects.nonNull(userData)) {
            log.warn("名为{}的用户以在l数据库中存在!", name);
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
    @CacheEvict(value = {"select_user", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOneUser(User user) {
        //判断数据库中是否存在该用户
        List<User> users = userMapper.selectAllUser(user);
        if (users.isEmpty()) {
            log.error("ID为{}的用户不存在!", user.getId());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        Integer column = userMapper.updateUserById(user);
        //判断是否成功
        if (column < 1) {
            log.error("ID为{}的用户修改失败!", user.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        return JsonRequest.success(column);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 用户ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"select_user", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deleteUser(String[] ids) {
        //判断数据库中是否存在该用户
        List<User> users = userMapper.selectAllByIds(ids);
        if (ids.length != users.size()) {
            log.error("删除角色信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", users.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = userMapper.deleteUserById(ids);
        //判断是否成功
        if (column < 1) {
            log.error("删除用户失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        return JsonRequest.success(column);
    }

    /**
     * 加载用户权限对象
     *
     * @param username 用户名
     * @return 用户权限对象
     * @throws UsernameNotFoundException 如果用户名未使用或错误则抛出此异常
     */
    @Override
    @Cacheable(value = "security_userDetail", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息是否存在
        User user = userMapper.selectAllByName(username);
        //如果结果为空
        if (Objects.isNull(user)) {
            log.error("用户名为{}的用户登陆失败!原因:用户名或密码错误!", username);
            throw new ServiceException(RequestException.LOGIN_ERROR);
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user);
        //查询该用户的角色信息
        List<Role> roles = roleMapper.selectAllRoleById(new String[]{user.getId()});
        //通过角色ID查询对应的权限ID
        List<PermissionRole> permissionRoles = permissionRoleMapper.selectAllPermissionRoleById(
                (String[]) roles
                        .stream()
                        .map(Role::getId)//取出ID值
                        .toArray() //转换为数组
        );
        //获取权限ID
        String[] permissionIds =
                (String[]) permissionRoles
                        .stream()
                        .map(PermissionRole::getPermissionId)//取出权限ID
                        .toArray();//转换为数组
        //获取权限列表
        List<Permission> permissions = permissionMapper.selectAllPermissionById(permissionIds);
        //封装权限信息
        userDetail.setPermission(
                permissions.stream()
                        .map(Permission::getSign)//取出标识符
                        .collect(Collectors.toList()));//转换为List集合
        return userDetail;
    }
}
