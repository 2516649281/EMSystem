package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Role;

import java.util.List;

/**
 * 角色数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/15
 */
public interface RoleMapper {
    /**
     * 条件查询角色信息
     *
     * @param role 条件
     * @return 角色列表
     */
    List<Role> selectAllRole(Role role);

    /**
     * 根据ID查询角色
     *
     * @param ids 角色ID
     * @return 角色列表
     */
    List<Role> selectAllRoleById(String[] ids);

    /**
     * 插入一条角色信息
     *
     * @param role 角色信息
     * @return 影响行数
     */
    Integer insertRole(Role role);

    /**
     * 修改一条角色信息
     *
     * @param role 角色信息
     * @return 影响行数
     */
    Integer updateRoleById(Role role);

    /**
     * 根据角色ID批量删除角色信息
     *
     * @param ids 选择的角色ID
     * @return 影响行数
     */
    Integer deleteRoleById(String[] ids);
}
