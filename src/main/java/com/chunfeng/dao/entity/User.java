package com.chunfeng.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别性别(0女,1男)
     */
    private Integer sex;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 角色
     */
    private Long role;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 状态(0正常,1失效)
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
