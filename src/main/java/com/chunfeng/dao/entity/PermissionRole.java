package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限-角色绑定实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/16
 */
@Data
@NoArgsConstructor
@ApiModel(value = "权限-角色绑定实体", description = "用于联系权限和角色实体")
public class PermissionRole implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 2551094863220159988L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "关系ID", allowEmptyValue = true)
    private String id;
    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    private String permissionId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;
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
