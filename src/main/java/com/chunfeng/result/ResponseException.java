package com.chunfeng.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常处理
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@RestController
@Slf4j
public class ResponseException {

    /**
     * 异常处理入口
     *
     * @param e 关键异常
     * @return JSON
     */
    @ExceptionHandler
    public JsonRequest<Void> setException(Exception e) {
        log.warn("异常{}已被捕获和处理!", e.getLocalizedMessage());
        return new JsonRequest<>(500, e.getLocalizedMessage(), false);
    }
}
