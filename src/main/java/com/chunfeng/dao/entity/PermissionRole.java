package com.chunfeng.dao.entity;

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
public class PermissionRole implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 8396482295376909486L;
    /**
     * 主键
     */
    private String id;
    /**
     * 权限ID
     */
    private String permissionId;
    /**
     * 角色ID
     */
    private String roleId;
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
