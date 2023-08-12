package com.chunfeng.service;

import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 权限-路由关系业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IPermissionRouterService {

    /**
     * 分类筛选关系信息
     *
     * @param permissionRouter 条件
     * @return JSON
     */
    JsonRequest<List<PermissionRouter>> lookPermissionRouter(PermissionRouter permissionRouter);

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    JsonRequest<List<PermissionRouter>> lookAllPermissionRouter();

    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<List<PermissionRouter>> lookPermissionRouterById(String[] ids);

    /**
     * 根据路由ID批量查询关系信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    JsonRequest<List<PermissionRouter>> lookPermissionRouterByRouter(String[] ids);

    /**
     * 批量绑定关系信息
     *
     * @param permissionRouters 关系信息
     * @return JSON
     */
    JsonRequest<Integer> addPermissionRouter(List<PermissionRouter> permissionRouters);

    /**
     * 批量修改关系数据
     *
     * @param permissionRouters 关系信息
     * @return JSON
     */
    JsonRequest<Integer> updatePermissionRouter(List<PermissionRouter> permissionRouters);

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<Integer> deletePermissionRouter(String[] ids);

    /**
     * 通过路由ID解绑关系信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    JsonRequest<Integer> deletePermissionRouterByRouter(String[] ids);
}
