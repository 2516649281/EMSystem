package com.chunfeng.result.exception;

import com.chunfeng.result.RequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 业务层异常超类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Setter
@Getter
@NoArgsConstructor
public class ServiceException extends RuntimeException {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 456372299927265329L;
    /**
     * 错误代码
     */
    private Integer status = 500;
    /**
     * 消息
     */
    private String message = "未知异常!";

    /**
     * 使用原有枚举类异常
     *
     * @param requestException 异常枚举
     */
    public ServiceException(RequestException requestException) {
        super(requestException.getMessage());
        this.message = requestException.getMessage();
        this.status = requestException.getStatus();
    }

    /**
     * 通用构造
     *
     * @param message 消息
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 通用构造
     *
     * @param message 消息
     * @param cause   原因
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 通用构造
     *
     * @param cause 原因
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * 通用构造
     *
     * @param message            消息
     * @param cause              原因
     * @param enableSuppression  启用抑制
     * @param writableStackTrace 可写堆栈跟踪
     */
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
