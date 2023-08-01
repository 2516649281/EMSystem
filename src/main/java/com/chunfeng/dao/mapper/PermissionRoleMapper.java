package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.PermissionRole;
import org.apache.ibatis.annotations.Param;

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
     * 按条件批量查询符合条件的个数
     *
     * @param permissionRoles 条件
     * @return 个数
     */
    Integer selectAllPermissionRoleCount(@Param("prs") List<PermissionRole> permissionRoles);

    /**
     * 根据ID查询关系
     *
     * @param ids 关系ID
     * @return 关系列表
     */
    List<PermissionRole> selectAllPermissionRoleById(String[] ids);

    /**
     * 批量插入关系信息
     *
     * @param permissionRoles 关系信息
     * @return 影响行数
     */
    Integer insertPermissionRole(@Param("prs") List<PermissionRole> permissionRoles);

    /**
     * 根据关系ID批量修改关系数据
     *
     * @param permissionRoles 关系数据
     * @return 影响行数
     */
    Integer updatePermissionRoleById(@Param("prs") List<PermissionRole> permissionRoles);

    /**
     * 根据关系ID批量解绑关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deletePermissionRoleById(String[] ids);

    /**
     * 根据权限ID解绑关系信息
     *
     * @param ids 权限ID
     * @return 影响行数
     */
    Integer deletePermissionRoleByPer(@Param("ids") String[] ids);

    /**
     * 根据角色ID解绑关系信息
     *
     * @param ids 权限ID
     * @return 影响行数
     */
    Integer deletePermissionRoleByRole(@Param("ids") String[] ids);
}
