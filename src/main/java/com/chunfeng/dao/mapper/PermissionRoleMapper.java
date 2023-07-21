package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.PermissionRole;

import java.util.List;

/**
 * 权限-角色数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/16
 */
public interface PermissionRoleMapper {

    /**
     * 条件查询关系信息
     *
     * @param permissionRole 条件
     * @return 关系列表
     */
    List<PermissionRole> selectAllPermissionRole(PermissionRole permissionRole);

    /**
     * 根据ID查询关系
     *
     * @param ids 关系ID
     * @return 关系列表
     */
    List<PermissionRole> selectAllPermissionRoleById(String[] ids);

    /**
     * 插入一条关系信息
     *
     * @param permissionRole 关系信息
     * @return 影响行数
     */
    Integer insertPermissionRole(PermissionRole permissionRole);

    /**
     * 根据关系ID修改一条关系数据
     *
     * @param permissionRole 关系数据
     * @return 影响行数
     */
    Integer updatePermissionRoleById(PermissionRole permissionRole);

    /**
     * 根据关系ID批量解绑关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deletePermissionRoleById(String[] ids);
}
