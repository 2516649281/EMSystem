package com.chunfeng.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Redis执行工具类
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/11
 */
@Component
@Slf4j
public class RedisClientsUtils {

    /**
     * Redis客户端
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 存值
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        log.info("已将{}-{}存入redis", key, value);
    }

    /**
     * 获取值
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(value)) {
            log.warn("未获取到key为{}的值!", key);
            value = "";
        }
        log.info("已获取到{}-{}的值", key, value);
        return value.toString();
    }

    /**
     * 删除键
     *
     * @param key 键
     * @return 是否成功
     */
    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }
}
