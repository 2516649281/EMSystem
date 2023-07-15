package com.chunfeng.handler;

import com.alibaba.fastjson.JSON;
import com.chunfeng.dao.entity.User;
import com.chunfeng.result.RequestException;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.utils.RedisClientsUtils;
import com.chunfeng.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * token拦截器
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Component
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    /**
     * Redis工具类
     */
    @Autowired
    private RedisClientsUtils redisClientsUtils;

    /**
     * 拦截器排除路径
     */
    private String[] excludedUri = {
            "/user/login",
            "/user/register"
    };


    /**
     * token拦截器
     *
     * @param request     请求头
     * @param response    响应体
     * @param filterChain 拦截器链
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            log.warn("token为空!");
            filterChain.doFilter(request, response);
        }
        //获取token中的ID
        String id = TokenUtils.checkToken(token).getSubject();
        String user = redisClientsUtils.get("login:" + id);
        //转换为对象
        User user1 = JSON.parseObject(user, User.class);
        //验证不通过或者redis数据库中不存在此数据
        if (!StringUtils.hasText(user) || id.equals(user1.getId().toString())) {
            log.error("token验证失败!{}-{}", id, user);
            throw new ServiceException(RequestException.FORBIDDEN);
        }
        log.info("ID为{}用户通过验证!", id);
        filterChain.doFilter(request, response);
    }

    /**
     * 配置排除路径
     *
     * @param request 请求
     * @return 返回true以排除请求
     * @throws ServletException servlet异常
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        //遍历并判断是否包含排除路径
        return Stream.of(excludedUri).anyMatch(x -> pathMatcher.match(x, uri));
    }
}
