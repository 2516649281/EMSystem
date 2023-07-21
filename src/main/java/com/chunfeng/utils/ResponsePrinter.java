package com.chunfeng.utils;

import com.alibaba.fastjson.JSON;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Web页面渲染工具类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Slf4j
public class ResponsePrinter {

    /**
     * 打印信息到页面
     *
     * @param request  请求体
     * @param response 响应体
     * @param json     JSON响应体
     */
    public static void printer(HttpServletRequest request, HttpServletResponse response, JsonRequest<Void> json) {
        try {
            response.setStatus(json.getStatus());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSON.toJSONString(json));
        } catch (IOException e) {
            log.error("JSON渲染错误!");
            throw new ServiceException();
        }
    }
}
