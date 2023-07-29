package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
@NoArgsConstructor
@ApiModel(value = "权限实体类", description = "用于存储权限的实体")
public class Permission implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 5222123986100551153L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "权限ID")
    private String id;
    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名")
    private String name;
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识", required = true)
    private String sign;
    /**
     * 是否为默认
     */
    @ApiModelProperty(value = "是否为默认", required = true)
    private Integer isDefault;
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
