package com.chunfeng.handler;

import com.alibaba.fastjson.JSON;
import com.chunfeng.dao.security.UserDetail;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.utils.ExcludeUrlUtils;
import com.chunfeng.utils.RedisClientsUtils;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token拦截器
 * <p>
 * SpringSecurity拦截器链的扩展，用于对token进行判断和检验，从而完成前后端分离的登录认证
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
     * 路径判断工具
     */
    @Autowired
    private ExcludeUrlUtils excludeUrlUtils;


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
            return;
        }
        //获取token中的ID
        String id = TokenUtils.checkToken(token, Long.TYPE).toString();
        //获取Redis的ID
        String user = redisClientsUtils.get("login:" + id);
        //转换为对象
        UserDetail user1 = JSON.parseObject(user, UserDetail.class);
        //验证不通过或者redis数据库中不存在此数据
        if (ObjectUtils.isEmpty(user1)//检测权限对象是否为空
                || !(StringUtils.hasText(user)//检测redis内的用户ID是否为空
                || id.equals(user1.getUser().getId())))//检测token是否合法
        {
            log.error("token验证失败!{}-{}", id, user);
            throw new ServiceException(RequestException.FORBIDDEN);
        }
        //存入ID
        SqlDateUtils.currentUserId = id;
        log.info("ID为{}用户通过验证!", id);
        //存入
        UsernamePasswordAuthenticationToken ut = new UsernamePasswordAuthenticationToken(
                user1, null, user1.getAuthorities());
        //将权限信息放入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(ut);
        filterChain.doFilter(request, response);
    }

    /**
     * 拦截器排除路径配置
     *
     * @param request 当前的HTTP请求对象
     * @return 如果放行则返回true, 拦截返回false
     * @throws ServletException 如果发生异常
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        //排除路径
        Integer index = excludeUrlUtils.isExcludeUrl(uri);
        if (index != 0) {
            log.info("排除路径放行!");
            return true;
        }
        return false;
    }
}
