package com.chunfeng.result.exenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 错误类型枚举
 * <p>
 * 在实际开发过程性，一个异常分为客户端异常和服务端异常，如果加以区分，更有利于开发者判断
 *
 * @author by 春风能解释
 * <p>
 * 2023/10/11
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TypeEnum {
    /**
     * 未知
     */
    UNKNOWN(-1, "UNKNOWN"),
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 客户端
     */
    CLIENT(1, "CLIENT"),
    /**
     * 服务端
     */
    SERVER(2, "SERVER");
    /**
     * 指数
     */
    private Integer index;
    /**
     * 描述
     */
    private String description;
}
