package com.chunfeng.handler;

import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.utils.ResponsePrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Component
@Slf4j
public class AuthenticationException implements AuthenticationEntryPoint {
    /**
     * 非法认证异常处理
     *
     * @param request       请求头
     * @param response      响应体
     * @param authException 非法认证类异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        log.warn("非法认证已触发!");
        ResponsePrinter.printer(request, response, JsonRequest.error(RequestException.UNAUTHORIZED));
    }
}
