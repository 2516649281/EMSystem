package com.chunfeng.result;

import com.chunfeng.result.exception.ServiceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * JSON返回类
 * <p>
 * 此类用于向前端返回统一的JSON格式，具体分以下几个部分:
 * <ul>
 *     <li>status:状态码，错误的唯一标识，可以是自定义的，也可以是WEB规定的，可参阅{@link org.springframework.http.HttpStatus}</li>
 *      <li>message:消息，向客户端或服务端描述错误的原因</li>
 *      <li>success:是否成功，描述一个请求是否成功，也用于客户端或服务端判断</li>
 *      <li>data:具体的数据，整个请求的核心，可存放任意类型的数据，需要配合泛型</li>
 * </ul>
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@Data
@NoArgsConstructor
@ApiModel(value = "统一响应类", description = "用于传给前端的统一JSON格式")
@Slf4j
public class JsonRequest<T> implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = -5408366163757792606L;
    /**
     * 错误代码
     */
    @ApiModelProperty(value = "错误代码", allowableValues = "range[100,511]")
    private Integer status = 200;
    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String message = "请求已成功!";
    /**
     * 是否正常
     */
    @ApiModelProperty(value = "是否正常")
    private Boolean success = true;
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据", allowEmptyValue = true)
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
        //如果是已定义的异常
        if (e instanceof ServiceException) {
            ServiceException exception = (ServiceException) e;
            return new JsonRequest<>(exception.getStatus(), e.getMessage(), false);
        }
        //其他的未知异常
        return new JsonRequest<>(500, e.getLocalizedMessage(), false);
    }
}
