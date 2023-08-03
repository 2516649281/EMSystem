package com.chunfeng.handler;

import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.utils.ResponsePrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权拦截器
 * <p>
 * SpringSecurity的自定义组件，用于向前端返回统一的有关授权异常的JSON格式
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Component
@Slf4j
public class AccessDeniedException implements AccessDeniedHandler {
    /**
     * 用于过滤授权类异常处理
     *
     * @param request               请求头
     * @param response              响应体
     * @param accessDeniedException 授权异常
     * @throws IOException      如果出现IO类异常(比如序列化)
     * @throws ServletException 如果出现Servlet类异常
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        log.warn("非法授权已触发!原因:{}", accessDeniedException.getLocalizedMessage());
        ResponsePrinter.printer(request, response, JsonRequest.error(RequestException.FORBIDDEN));
    }
}
