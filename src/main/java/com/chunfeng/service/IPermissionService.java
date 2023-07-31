package com.chunfeng.service;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 权限业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IPermissionService {

    /**
     * 分类筛选权限
     *
     * @param permission 条件
     * @return JSON
     */
    JsonRequest<List<Permission>> lookPermission(Permission permission);

    /**
     * 查询所有权限
     *
     * @return JSON
     */
    JsonRequest<List<Permission>> lookAllPermission();

    /**
     * 根据ID值批量查询权限信息
     *
     * @param ids 权限ID
     * @return JSON
     */
    JsonRequest<List<Permission>> lookPermissionById(String[] ids);

    /**
     * 添加一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    JsonRequest<Integer> addOnePermission(Permission permission);

    /**
     * 修改一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    JsonRequest<Integer> updateOnePermission(Permission permission);

    /**
     * 批量删除权限信息
     *
     * @param ids 权限信息
     * @return JSON
     */
    JsonRequest<Integer> deletePermission(String[] ids);
}
