package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
public interface PermissionMapper {
    /**
     * 条件查询所有权限
     *
     * @param permission 条件
     * @return 符合条件的所有权限信息
     */
    List<Permission> selectAllPermission(Permission permission);

    /**
     * 根据ID查询权限
     *
     * @param ids 权限ID
     * @return 权限列表
     */
    List<Permission> selectAllPermissionById(@Param("ids") String[] ids);

    /**
     * 插入一条权限数据
     *
     * @param permission 权限信息
     * @return 影响行数
     */
    Integer insertPermission(Permission permission);

    /**
     * 根据权限ID修改一条权限数据
     *
     * @param permission 权限信息
     * @return 影响行数
     */
    Integer updatePermissionById(Permission permission);

    /**
     * 根据权限ID批量删除权限信息
     *
     * @param ids 选择的权限ID
     * @return 影响行数
     */
    Integer deletePermissionById(@Param("ids") String[] ids);
}
