package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/11
 */
@Data
@NoArgsConstructor
@ApiModel(value = "角色实体类", description = "用于存储角色信息")
public class Role implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 2474682772541255777L;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", allowEmptyValue = true)
    private String id;
    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String name;
    /**
     * 权限列表
     */
    @ApiModelProperty(value = "权限列表")
    private List<Permission> permissionList;
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
