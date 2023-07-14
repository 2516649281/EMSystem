package com.chunfeng.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * <h1>token生成/解析工具类</h1>
 * token令牌是前后端分离开发最重要的元素,所谓的Token,其实就是服务端生成的一串加密字符串、以作客户端进行请求的一个“令牌”,当用户第一次使用账号密码成功进行登录后,服务器便生成一个Token及Token失效时间并将此返回给客户端,若成功登陆,以后客户端只需在有效时间内带上这个Token前来请求数据即可,无需再次带上用户名和密码,拿实际过程举例,当你下载QQ或微信后第一次用账号和密码成功登录后,Token就为我们免去了每次打开应用都要输入账号跟密码的过程.
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
     * @param t 任意类型
     * @return token
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
     * @return object
     */
    public static Object checkToken(String token) {
        if (token.equals("")) {
            log.error("token为空!");
            throw new RuntimeException("Token为空!");
        }
        Jws<Claims> claimsJws;
        //redis客户端
        RedisClientsUtils redisClientsUtils = new RedisClientsUtils();
        try {
            claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
            //如果解析结果为空
            if (claimsJws == null) {
                redisClientsUtils.remove(token);
                log.error("token解析失败!已删除key-{}", token);
                throw new RuntimeException("解析失败!");
            }
        }//如果发生异常
        catch (Exception e) {
            redisClientsUtils.remove(token);
            log.error("token解析失败!已删除key-{}", token);
            throw new RuntimeException("解析失败!");
        }
        return claimsJws.getBody().get("user");
    }
}
