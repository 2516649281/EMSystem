package com.chunfeng.service;

import com.chunfeng.dao.entity.Router;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 路由业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IRouterService {

    /**
     * 分类筛选路由信息
     *
     * @param router 条件
     * @return JSON
     */
    JsonRequest<List<Router>> lookRouter(Router router);

    /**
     * 查询所有路由信息
     *
     * @return JSON
     */
    JsonRequest<List<Router>> lookAllRouter();

    /**
     * 新增一条路由信息
     *
     * @param router 路由信息
     * @return JSON
     */
    JsonRequest<Integer> addOneRouter(Router router);

    /**
     * 修改一条路由信息
     *
     * @param router 路由信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneRouter(Router router);

    /**
     * 批量删除路由信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    JsonRequest<Integer> deleteRouter(String[] ids);
}
