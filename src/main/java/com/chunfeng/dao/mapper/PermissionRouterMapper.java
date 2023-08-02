package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.PermissionRouter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限-路由数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/16
 */
public interface PermissionRouterMapper {

    /**
     * 条件查询关系信息
     *
     * @param permissionRouter 条件
     * @return 关系列表
     */
    List<PermissionRouter> selectAllPermissionRouter(PermissionRouter permissionRouter);

    /**
     * 根据路由ID查询权限ID
     *
     * @param routerId 路由ID
     * @return 权限ID
     */
    List<String> selectAllPermissionRouterByRouterId(@Param("rids") String[] routerId);

    /**
     * 根据ID查询关系
     *
     * @param ids 关系ID
     * @return 关系列表
     */
    List<PermissionRouter> selectAllPermissionRouterById(String[] ids);

    /**
     * 按条件批量查询符合条件的个数
     *
     * @param permissionRouters 条件
     * @return 个数
     */
    Integer selectAllPermissionRouterCount(@Param("prt") List<PermissionRouter> permissionRouters);

    /**
     * 根据路由ID查询关系
     *
     * @param ids 路由ID
     * @return 关系
     */
    List<PermissionRouter> selectAllByPermissionRouterByRouter(@Param("ids") String[] ids);

    /**
     * 批量插入关系信息
     *
     * @param permissionRouters 关系信息
     * @return 影响行数
     */
    Integer insertPermissionRouter(@Param("prt") List<PermissionRouter> permissionRouters);

    /**
     * 根据关系ID批量修改关系数据
     *
     * @param permissionRouters 关系数据
     * @return 影响行数
     */
    Integer updatePermissionRouterById(@Param("prt") List<PermissionRouter> permissionRouters);

    /**
     * 根据关系ID批量解绑关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deletePermissionRouterById(String[] ids);

    /**
     * 根据权限ID解绑关系信息
     *
     * @param ids 权限ID
     * @return 影响行数
     */
    Integer deletePermissionRouterByPer(@Param("ids") String[] ids);

    /**
     * 根据权限ID解绑关系信息
     *
     * @param rid 路由ID
     * @return 影响行数
     */
    Integer deletePermissionRouterByRid(@Param("ids") String[] rid);
}
