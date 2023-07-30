package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Router;

import java.util.List;

/**
 * 路由数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
public interface RouterMapper {
    /**
     * 条件查询路由信息
     *
     * @param router 条件
     * @return 路由列表
     */
    List<Router> selectAllRouter(Router router);

    /**
     * 根据ID查询路由
     *
     * @param ids 路由ID
     * @return 路由列表
     */
    List<Router> selectAllRouterById(String[] ids);

    /**
     * 插入一条路由信息
     *
     * @param router 路由信息
     * @return 影响行数
     */
    Integer insertRouter(Router router);

    /**
     * 修改一条路由信息
     *
     * @param router 路由信息
     * @return 影响行数
     */
    Integer updateRouterById(Router router);

    /**
     * 根据路由ID批量删除路由信息
     *
     * @param ids 选择的路由ID
     * @return 影响行数
     */
    Integer deleteRouterById(String[] ids);
}
