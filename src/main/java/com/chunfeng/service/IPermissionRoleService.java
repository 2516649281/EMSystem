package com.chunfeng.service;

import com.chunfeng.dao.entity.PermissionRole;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 权限-角色关系业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IPermissionRoleService {

    /**
     * 分类筛选关系信息
     *
     * @param permissionRole 条件
     * @return JSON
     */
    JsonRequest<List<PermissionRole>> lookPermissionRole(PermissionRole permissionRole);

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    JsonRequest<List<PermissionRole>> lookAllPermissionRole();

    /**
     * 批量绑定关系信息
     *
     * @param permissionRoles 关系信息
     * @return JSON
     */
    JsonRequest<Integer> addPermissionRole(List<PermissionRole> permissionRoles);

    /**
     * 批量修改关系数据
     *
     * @param permissionRoles 关系信息
     * @return JSON
     */
    JsonRequest<Integer> updatePermissionRole(List<PermissionRole> permissionRoles);

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<Integer> deletePermissionRole(String[] ids);
}
