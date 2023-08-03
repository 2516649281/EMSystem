package com.chunfeng.utils;

import com.chunfeng.result.RequestException;
import com.chunfeng.result.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Component
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
     * @return object
     */
    public static Claims checkToken(String token) {
        if (token == null || token.equals("")) {
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
        return claimsJws.getBody();
    }
}
