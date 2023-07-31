package com.chunfeng.service;

import com.chunfeng.dao.entity.Role;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 角色业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IRoleService {

    /**
     * 分类筛选角色信息
     *
     * @param role 条件
     * @return JSON
     */
    JsonRequest<List<Role>> lookRole(Role role);

    /**
     * 查询所有角色信息
     *
     * @return JSON
     */
    JsonRequest<List<Role>> lookAllRole();

    /**
     * 根据ID值批量查询角色信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    JsonRequest<List<Role>> lookRoleById(String[] ids);

    /**
     * 根据ID值查询角色信息
     *
     * @param roleId 角色ID
     * @return JSON
     */
    JsonRequest<Role> lookOneRole(String roleId);

    /**
     * 新增一条角色信息
     *
     * @param role 角色信息
     * @return JSON
     */
    JsonRequest<Integer> addOneRole(Role role);

    /**
     * 修改一条角色信息
     *
     * @param role 角色信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneRole(Role role);

    /**
     * 批量删除角色信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    JsonRequest<Integer> deleteRole(String[] ids);
}
