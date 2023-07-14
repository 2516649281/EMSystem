package com.chunfeng.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * <h1>密码加密/解密工具类</h1>
 * 为了保护数据库用户的信息,将密码进行加密,即使数据库被攻破,用户存储的密码也不会在第一时间被攻破
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/11
 */
@Component
@Slf4j
public class PasswordEncoderUtils {

    /**
     * 加密密码
     *
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public String encode(String rawPassword) {
        log.info("密码加密生效!");
        return getPassword(rawPassword, null);
    }

    /**
     * 校验密码
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 加密后的密码
     * @return 返回true则密码校验成功
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        log.warn("密码已解密完成!");
        return check(rawPassword, encodedPassword);
    }

    /**
     * MD5加密密码
     *
     * @param password 原始密码
     * @param salt     盐值(当验证时启用)
     * @return 加密后的新密码
     */
    public String getPassword(String password, String salt) {
        if (salt == null) {
            salt = UUID.randomUUID().toString().toUpperCase();//随机生成盐值
            log.info("已创建新的盐值{}", salt);
        }
        log.info("正在对{}进行加密!", password);
        //加密100次
        for (int i = 0; i < 100; i++) {
            //MD5加密算法
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes(StandardCharsets.UTF_8)).toUpperCase();//重组
        }
        log.info("已完成对密码{}的加密!", password);
        return salt + "$" + password;
    }

    /**
     * 密码验证
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 数据库密码
     * @return 密码是否一致
     */
    public Boolean check(String rawPassword, String encodedPassword) {
        //获取盐值
        String salt = encodedPassword.split("\\$")[0];
        String password = getPassword(rawPassword, salt);
        log.info("原始密码:{};数据库密码:{}", password, encodedPassword);
        return encodedPassword.equals(password);
    }
}
