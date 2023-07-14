package com.chunfeng.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色实体类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/11
 */
@Data
@NoArgsConstructor
public class Role implements Serializable {
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色权限
     */
    private String auth;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * 更新人
     */
    private Long updateUser;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
