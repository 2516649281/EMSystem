package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户实体")
public class User implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty("用户ID")
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;
    /**
     * 性别(0女,1男)
     */
    @ApiModelProperty("性别(0女,1男)")
    private Integer sex;
    /**
     * 电子邮箱
     */
    @ApiModelProperty("电子邮箱")
    private String email;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;
    /**
     * 角色
     */
    @ApiModelProperty("角色")
    private String role;
    /**
     * 状态(0正常,1失效)
     */
    @ApiModelProperty("状态(0正常,1失效)")
    private Integer status;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createUser;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updateUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private String updateTime;
}
