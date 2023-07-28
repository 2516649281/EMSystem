package com.chunfeng.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求异常的枚举
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@Getter
@AllArgsConstructor
public enum RequestException {
    /**
     * 非法访问
     */
    UNAUTHORIZED(401, "非法访问!"),
    /**
     * 登录失败
     */
    LOGIN_ERROR(402, "登录失败,请检查用户名或密码是否正确!"),
    /**
     * 非法授权
     */
    FORBIDDEN(403, "非法授权!"),
    /**
     * 找不到资源
     */
    NOT_FOUND(404, "找不到资源!"),
    /**
     * 注册失败
     */
    REGISTER_ERROR(405, "注册失败,请检查数据是否符合规范!"),
    /**
     * 退出登录失败
     */
    LOGOUT_ERROR(406, "退出登录失败!"),
    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(500, "系统错误,请联系网站管理员!"),
    /**
     * 查询数据时遇到问题
     */
    SELECT_ERROR(501, "数据查询失败!"),
    /**
     * 添加数据时遇到问题
     */
    INSERT_ERROR(502, "数据添加失败!"),
    /**
     * 修改数据时遇到问题
     */
    UPDATE_ERROR(503, "数据修改失败!"),
    /**
     * 删除数据时遇到问题
     */
    DELETE_ERROR(504, "数据删除失败!"),
    /**
     * 文件上传失败
     */
    FILE_ERROR(505, "文件上传失败!"),
    /**
     * 文件超出范围
     */
    FILE_BEYOND_MAX_SIZE(506, "文件超出范围!"),
    /**
     * token异常
     */
    TOKEN_ERROR(507, "非法token");
    /**
     * 错误代码
     */
    private Integer status;
    /**
     * 消息
     */
    private String message;
}
