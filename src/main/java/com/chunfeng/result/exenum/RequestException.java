package com.chunfeng.result.exenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 请求异常的枚举
 * <p>
 * 用于枚举业务层所有的异常(可扩展)，一个枚举包含两个部分，状态码+消息:
 * <br>
 * <ul>
 * <li>错误的唯一标识，可以是自定义的，也可以是WEB规定的，可参阅{@link org.springframework.http.HttpStatus}</li>
 * <li>消息用于向客户端或服务端描述问题的原因或具体情况，可根据实际业务需要进行设置</li>
 * </ul>
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RequestException {
    /**
     * 非法访问
     */
    UNAUTHORIZED(401, "非法访问!", TypeEnum.CLIENT.getDescription()),
    /**
     * 登录失败
     */
    LOGIN_ERROR(402, "登录失败,请检查用户名或密码是否正确!", TypeEnum.CLIENT.getDescription()),
    /**
     * 非法授权
     */
    FORBIDDEN(403, "非法授权!", TypeEnum.CLIENT.getDescription()),
    /**
     * 找不到资源
     */
    NOT_FOUND(404, "找不到资源!", TypeEnum.CLIENT.getDescription()),
    /**
     * 注册失败
     */
    REGISTER_ERROR(405, "注册失败,请检查数据是否符合规范!", TypeEnum.CLIENT.getDescription()),
    /**
     * 退出登录失败
     */
    LOGOUT_ERROR(406, "退出登录失败!", TypeEnum.SERVER.getDescription()),
    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(500, "未知错误,请联系网站管理员!", TypeEnum.UNKNOWN.getDescription()),
    /**
     * 查询数据时遇到问题
     */
    SELECT_ERROR(501, "数据查询失败!", TypeEnum.SERVER.getDescription()),
    /**
     * 添加数据时遇到问题
     */
    INSERT_ERROR(502, "数据添加失败!", TypeEnum.SERVER.getDescription()),
    /**
     * 修改数据时遇到问题
     */
    UPDATE_ERROR(503, "数据修改失败!", TypeEnum.SERVER.getDescription()),
    /**
     * 删除数据时遇到问题
     */
    DELETE_ERROR(504, "数据删除失败!", TypeEnum.SERVER.getDescription()),
    /**
     * 文件上传失败
     */
    FILE_ERROR(505, "文件上传失败!", TypeEnum.SERVER.getDescription()),
    /**
     * 文件超出范围
     */
    FILE_BEYOND_MAX_SIZE(506, "文件超出范围!", TypeEnum.CLIENT.getDescription()),
    /**
     * token异常
     */
    TOKEN_ERROR(507, "非法token", TypeEnum.CLIENT.getDescription());
    /**
     * 错误代码
     */
    private Integer status;
    /**
     * 消息
     */
    private String message;
    /**
     * 错误类型
     */
    private String type;
}
