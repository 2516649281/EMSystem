package com.chunfeng.dao.entity;

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
public class Permission implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = -1843362880334381973L;
    /**
     * 主键
     */
    private String id;
    /**
     * 权限名
     */
    private String name;
    /**
     * 权限标识
     */
    private String sign;
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
