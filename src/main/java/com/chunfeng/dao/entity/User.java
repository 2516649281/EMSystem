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
@ApiModel(value = "用户实体", description = "用于存储用户的信息")
public class User implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = -1122718285158189698L;
    /**
     * 编号
     */
    @ApiModelProperty(value = "用户ID", allowEmptyValue = true)
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", allowEmptyValue = true)
    private String nickName;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", allowEmptyValue = true)
    private String avatar;
    /**
     * 性别(0女,1男)
     */
    @ApiModelProperty(value = "性别(0女,1男)", allowableValues = "0,1", allowEmptyValue = true)
    private Integer sex;
    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱", allowEmptyValue = true)
    private String email;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", allowEmptyValue = true)
    private String phone;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", allowEmptyValue = true)
    private String roleId;
    /**
     * 状态(0正常,1失效)
     */
    @ApiModelProperty(value = "状态(0正常,1失效)", allowableValues = "0,1", allowEmptyValue = true)
    private Integer status;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createUser;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;
}
