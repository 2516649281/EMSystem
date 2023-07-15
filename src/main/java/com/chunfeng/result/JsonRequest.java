package com.chunfeng.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSON返回类
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@Data
@NoArgsConstructor
public class JsonRequest<T> {
    /**
     * 错误代码
     */
    private Integer status = 200;
    /**
     * 消息
     */
    private String message = "请求已成功!";
    /**
     * 是否正常
     */
    private Boolean success = true;
    /**
     * 数据
     */
    private T data = null;

    /**
     * 构造方法1
     *
     * @param data 数据
     */
    public JsonRequest(T data) {
        this.data = data;
    }

    /**
     * 构造方法2
     *
     * @param status  状态
     * @param message 消息
     * @param success 是否正常
     */
    public JsonRequest(Integer status, String message, Boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    /**
     * 成功请求
     *
     * @param data 数据
     * @param <T>  类型
     * @return JSON
     */
    public static <T> JsonRequest<T> success(T data) {
        return new JsonRequest<>(data);
    }

    /**
     * 错误请求
     *
     * @param e   异常
     * @param <T> 类型
     * @return JSON
     */
    public static <T> JsonRequest<T> error(RequestException e) {
        return new JsonRequest<>(e.getStatus(), e.getMessage(), false);
    }


    /**
     * 全局异常处理
     *
     * @param <T> 类型
     * @param e   异常
     * @return JSON
     */
    public static <T> JsonRequest<T> error(Exception e) {
        return new JsonRequest<>(500, e.getLocalizedMessage(), false);
    }
}
