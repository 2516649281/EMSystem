package com.chunfeng.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 全局异常处理器
 * <p>
 * 此类用于处理大部分异常信息的响应,经过了这个类的处理,返回的格式将统一变为
 * <p>{</p>
 * <p>"status": 200,</p>
 * <p>"message": "请求已成功!",</p>
 * <p>"success": true,</p>
 * <p>"type": "SUCCESS",</p>
 * <p>"data": "",</p>
 * <p>"date": 1697117527763</p>
 * <p>}</p>
 * 此响应类可参照{@link com.chunfeng.result.JsonRequest}的字段
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/4
 */
@Component
@Slf4j
public class ExceptionHandler extends DefaultErrorAttributes {

    /**
     * 异常信息
     */
    private Exception exception = new ServiceException(RequestException.UNKNOWN_EXCEPTION);

    /**
     * 自定义错误返回
     *
     * @param webRequest 当前请求
     * @param options    错误上下文
     * @return JSON
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        //初始化异常
        super.getErrorAttributes(webRequest, options);
        //获取对应的JSON字符串
        JsonRequest<Void> request = JsonRequest.error(exception);
        //先转换为JSON
        String jsonString = JSON.toJSONString(request);
        log.warn("全局异常处理类已捕获到异常:{}", request.getMessage());
        //再转换为Map对象
        return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 获取异常信息
     *
     * @param webRequest 当前请求
     * @return 异常
     */
    @Override
    public Throwable getError(WebRequest webRequest) {
        //获取异常信息
        Throwable error = super.getError(webRequest);
        if (error instanceof Exception) {
            this.exception = (Exception) error;
        }
        return error;
    }

}
