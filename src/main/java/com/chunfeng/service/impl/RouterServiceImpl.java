package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Router;
import com.chunfeng.dao.mapper.PermissionMapper;
import com.chunfeng.dao.mapper.RouterMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IPermissionRouterService;
import com.chunfeng.service.IRouterService;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 路由业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
@Transactional
public class RouterServiceImpl implements IRouterService {

    /**
     * 路由数据层
     */
    @Autowired
    private RouterMapper routerMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IRouterService routerService;

    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 导入路由业务
     */
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 分类筛选路由信息
     *
     * @param router 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "router_select", key = "#router")
    public JsonRequest<List<Router>> lookRouter(Router router) {
        List<Router> routers = routerMapper.selectAllRouter(router);
        //判断是否成功
        if (routers.isEmpty()) {
            log.warn("未找到任何路由信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条路由信息", routers.size());
        return JsonRequest.success(routers);
    }

    /**
     * 查询所有路由信息
     *
     * @return JSON
     */
    @Override
    public JsonRequest<List<Router>> lookAllRouter() {
        return routerService.lookRouter(new Router());
    }

    /**
     * 新增一条路由信息
     *
     * @param router 路由信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "router_select", allEntries = true)
    public JsonRequest<Integer> addOneRouter(Router router) {
        //日志信息
        router.setId(UIDCreateUtil.getUUId());
        router.setCreateUser(SqlDateUtils.currentUserId);
        router.setCreateTime(SqlDateUtils.date);
        Integer column = routerMapper.insertRouter(router);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加路由名为{}的数据失败!", router.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加名为{}的路由信息!", router.getName());
        return JsonRequest.success(column);
    }

    /**
     * 修改一条路由信息
     *
     * @param router 路由信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"router_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOneRouter(Router router) {
        List<Router> routers = routerMapper.selectAllRouterById(new String[]{router.getId()});
        //判断是否成功
        if (routers.isEmpty()) {
            log.warn("数据库中不存在ID为{}的路由信息!", router.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        router.setUpdateUser(SqlDateUtils.currentUserId);
        router.setUpdateTime(SqlDateUtils.date);
        Integer column = routerMapper.updateRouterById(router);
        if (column < 1) {
            log.error("修改ID为{}的路由信息失败!", router.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的路由信息修改成功!", router.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除路由信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"router_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deleteRouter(String[] ids) {
        List<Router> routers = routerMapper.selectAllRouterById(ids);
        if (routers.size() != ids.length) {
            log.error("删除路由信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", routers.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = routerMapper.deleteRouterById(ids);
        if (column < 1) {
            log.error("删除路由失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条路由信息!", ids.length);
        return JsonRequest.success(column);
    }
}
