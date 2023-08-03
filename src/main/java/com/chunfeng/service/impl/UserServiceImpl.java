package com.chunfeng.service.impl;

import com.alibaba.fastjson.JSON;
import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.Role;
import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.dao.security.UserDetail;
import com.chunfeng.note.ExcludeMethods;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.service.IPermissionRoleService;
import com.chunfeng.service.IPermissionService;
import com.chunfeng.service.IRoleService;
import com.chunfeng.service.IUserService;
import com.chunfeng.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class UserServiceImpl implements IUserService, UserDetailsService {
    /**
     * 用户数据层注入
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * 导入角色业务
     */
    @Autowired
    private IRoleService roleService;
    /**
     * 导入权限业务
     */
    @Autowired
    private IPermissionService permissionService;
    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRoleService permissionRoleService;
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
     * 文件的工具类
     */
    @Autowired
    private FileMangerUtils<?> fileMangerUtils;
    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IUserService userService;

    /**
     * 分类查询用户
     *
     * @param user 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "user_select", key = "#user")
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
    @Cacheable(value = "user_select")
    public JsonRequest<List<User>> lookAllUser() {
        return userService.lookUser(new User());
    }

    /**
     * 按ID值批量查询用户数据
     *
     * @param ids ID值
     * @return JSON
     */
    @Override
    @Cacheable(value = "user_select", key = "#ids")
    public JsonRequest<List<User>> lookUserById(String[] ids) {
        List<User> users = userMapper.selectAllByIds(ids);
        if (users.size() != ids.length) {
            log.warn("待查询的用户ID与数据库中的数量不符!数据库:{},实际:{}", users.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条用户数据!", users.size());
        return JsonRequest.success(users);
    }

    /**
     * 登录逻辑
     *
     * @param name     用户名
     * @param password 密码
     * @return JSON
     */
    @Override
    @ExcludeMethods
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
                JSON.toJSONString(userDetail));//value: 用户序列化后的字符串
        log.info("用户{}登录成功!", name);
        return JsonRequest.success(token);
    }

    /**
     * 注册逻辑
     *
     * @param user 用户信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"user_select", "security_userDetail"}, allEntries = true)
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
    @CacheEvict(value = {"user_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOneUser(User user) {
        //判断数据库中是否存在该用户
        JsonRequest<List<User>> request = userService.lookUserById(new String[]{user.getId()});
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
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
    @CacheEvict(value = {"user_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deleteUser(String[] ids) {
        //判断数据库中是否存在该用户
        JsonRequest<List<User>> request = userService.lookUserById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
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
    @ExcludeMethods
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
        JsonRequest<Role> request = roleService.lookOneRole(user.getRoleId());
        if (!request.getSuccess()) {
            log.error("用户{}尚未绑定角色信息!", username);
            throw new ServiceException(RequestException.LOGIN_ERROR);
        }
        //获取权限列表
        List<Permission> permissions = request.getData().getPermissionList();
        //提取权限标识
        List<String> permissionList = permissions.stream()
                .map(Permission::getSign)
                .collect(Collectors.toList());
        //封装权限信息
        userDetail.setPermission(permissionList);
        return userDetail;
    }


    /**
     * 头像显示
     *
     * @param userId 用户ID
     * @return 文件响应流
     */
    @Override
    @ExcludeMethods
    public ResponseEntity<Resource> avatarDownload(String userId) {
        User user = getUserInfo(userId);
        String avatar = user.getAvatar();
        //发送文件流
        return fileMangerUtils.avatarDownload(avatar);
    }

    /**
     * 头像上传
     *
     * @param file 头像文件
     * @return 是否成功
     */
    @Override
    @CacheEvict(value = {"user_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Boolean> avatarUpload(MultipartFile file, String userId) {
        User user = new User();
        user.setId(userId);
        // 获取文件后缀
        String type = file.getOriginalFilename().split("\\.")[1];
        //组合为文件名
        String fileName = userId + "." + type;
        //设置头像
        user.setAvatar(fileName);
        //修改数据库
        JsonRequest<Integer> request = updateOneUser(user);
        if (!request.getSuccess()) {
            log.error("数据库修改失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //上传头像
        Boolean aBoolean = fileMangerUtils.avatarUpload(file, fileName);
        if (!aBoolean) {
            log.error("文件修改失败!");
            return JsonRequest.error(RequestException.FILE_ERROR);
        }
        log.info("ID为{}的用户头像上传成功!", userId);
        return JsonRequest.success(true);
    }

    /**
     * 用户退出登录
     *
     * @param token 令牌
     * @return JSON
     */
    @Override
    @ExcludeMethods
    public JsonRequest<Boolean> logout(String token) {
        //获取token中的ID
        String id = TokenUtils.checkToken(token).get("user").toString();
        //删除Redis
        boolean b = redisClientsUtils.remove("login:" + id);
        if (!b) {
            log.error("ID为{}的用户账号退出失败!", id);
            return JsonRequest.error(RequestException.LOGOUT_ERROR);
        }
        return JsonRequest.success(true);
    }

    /**
     * 获得用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public User getUserInfo(String userId) {
        //构造条件
        User user = new User();
        user.setId(userId);
        //查询
        JsonRequest<List<User>> request = userService.lookUser(user);
        //判断是否出错
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            throw new ServiceException(RequestException.NOT_FOUND);
        }
        return request.getData().get(0);
    }

}
