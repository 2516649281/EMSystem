package com.chunfeng.utils;

import com.alibaba.fastjson.JSON;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * token生成/解析工具类
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/11
 */
@Slf4j
public class TokenUtils {
    /**
     * 过期时间
     * 设置15min过期
     */
    private static final long time = 1000 * 60 * 60 * 2;

    /**
     * 密钥
     */
    private static final String signature = "chunfeng@2516649281$";

    /**
     * 创建token的方法
     *
     * @param t   对象
     * @param <T> 类型
     * @return token字符串
     */
    public static <T> String createToken(T t) {
        String token = Jwts.builder()
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("user", t)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        log.info("token创建成功");
        return token;
    }

    /**
     * 校验token,布尔类型
     *
     * @param token token
     * @param type  待转换的类型
     * @param <T>   任意类型
     * @return object
     */
    public static <T> T checkToken(String token, Class<T> type) {
        if (token == null || token.isEmpty()) {
            log.error("token为空!");
            throw new ServiceException(RequestException.TOKEN_ERROR);
        }
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
            //如果解析结果为空
            if (claimsJws == null) {
                log.error("token解析失败!已删除key-{}", token);
                throw new ServiceException(RequestException.TOKEN_ERROR);
            }
        }//如果发生异常
        catch (Exception e) {
            log.error("token解析失败!已删除key-{}", token);
            throw new ServiceException(RequestException.TOKEN_ERROR);
        }
        Object object = claimsJws.getBody().get("user");
        String jsonString = JSON.toJSONString(object);
        return JSON.parseObject(jsonString, type);
    }
}
