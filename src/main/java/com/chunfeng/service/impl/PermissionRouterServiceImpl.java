package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.dao.mapper.PermissionRouterMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.service.IPermissionRouterService;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限-路由关系业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class PermissionRouterServiceImpl implements IPermissionRouterService {

    /**
     * 关系数据层
     */
    @Autowired
    private PermissionRouterMapper permissionRouterMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 分类筛选关系信息
     *
     * @param permissionRouter 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRouter_select", key = "#permissionRouter")
    public JsonRequest<List<PermissionRouter>> lookPermissionRouter(PermissionRouter permissionRouter) {
        List<PermissionRouter> permissionRouters = permissionRouterMapper.selectAllPermissionRouter(permissionRouter);
        //判断是否成功
        if (permissionRouters.isEmpty()) {
            log.warn("未找到任何关系信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条关系信息", permissionRouters.size());
        return JsonRequest.success(permissionRouters);
    }

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRouter_select")
    public JsonRequest<List<PermissionRouter>> lookAllPermissionRouter() {
        return permissionRouterService.lookPermissionRouter(new PermissionRouter());
    }


    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRouter_select", key = "#ids")
    public JsonRequest<List<PermissionRouter>> lookPermissionRouterById(String[] ids) {
        List<PermissionRouter> permissionRouters = permissionRouterMapper.selectAllPermissionRouterById(ids);
        if (permissionRouters.size() != ids.length) {
            log.warn("待查询的关系ID与数据库中的数量不符!数据库:{},实际:{}", permissionRouters.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条关系数据!", permissionRouters.size());
        return JsonRequest.success(permissionRouters);
    }

    /**
     * 根据路由ID批量查询关系信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRouter_select", key = "#ids")
    public JsonRequest<List<PermissionRouter>> lookPermissionRouterByRouter(String[] ids) {
        List<PermissionRouter> permissionRouters = permissionRouterMapper.selectAllByPermissionRouterByRouter(ids);
        if (permissionRouters == null || permissionRouters.isEmpty()) {
            log.warn("未找到任何关系信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条关系信息!", permissionRouters.size());
        return JsonRequest.success(permissionRouters);
    }

    /**
     * 批量绑定关系信息
     *
     * @param permissionRouters 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRouter_select", "router_select"}, allEntries = true)
    public JsonRequest<Integer> addPermissionRouter(List<PermissionRouter> permissionRouters) {
        //判断关系是否已经存在
        Integer column = permissionRouterMapper.selectAllPermissionRouterCount(permissionRouters);
        if (column > 0) {
            log.error("添加关系数据失败!原因:该关系已在数据库中找到");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //日志信息注入
        List<PermissionRouter> permissionRouterList = permissionRouters
                .stream()
                .peek(v -> {
                    v.setId(UIDCreateUtil.getUUId());
                    v.setCreateUser(SqlDateUtils.currentUserId);
                    v.setCreateTime(SqlDateUtils.date);
                }).collect(Collectors.toList());
        column = permissionRouterMapper.insertPermissionRouter(permissionRouterList);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加{}条关系信息!", permissionRouters.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量修改关系数据
     *
     * @param permissionRouters 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRouter_select", "router_select"}, allEntries = true)
    public JsonRequest<Integer> updatePermissionRouter(List<PermissionRouter> permissionRouters) {
        //提取ID值
        String[] ids = permissionRouters.stream()
                .map(PermissionRouter::getId)
                .toArray(String[]::new);
        JsonRequest<List<PermissionRouter>> request = permissionRouterService.lookPermissionRouterById(ids);
        //判断关系数据是否一致
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //封装日志
        List<PermissionRouter> permissionRouterList = permissionRouters.stream().peek(v -> {
            //日志信息
            v.setUpdateUser(SqlDateUtils.currentUserId);
            v.setUpdateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //修改
        Integer column = permissionRouterMapper.updatePermissionRouterById(permissionRouterList);
        if (column < 1) {
            log.error("修改关系信息失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("已修改{}条关系信息!", permissionRouterList.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRouter_select", "router_select"}, allEntries = true)
    public JsonRequest<Integer> deletePermissionRouter(String[] ids) {
        JsonRequest<List<PermissionRouter>> request = permissionRouterService.lookPermissionRouterById(ids);
        //判断是否成功
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = permissionRouterMapper.deletePermissionRouterById(ids);
        if (column < 1) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条关系信息!", ids.length);
        return JsonRequest.success(column);
    }

    /**
     * 通过路由ID解绑关系信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRouter_select", "router_select"}, allEntries = true)
    public JsonRequest<Integer> deletePermissionRouterByRouter(String[] ids) {
        Integer column = permissionRouterMapper.deletePermissionRouterByRid(ids);
        //这里不做处理，因为存在没有关系的数据
        if (column < 1) {
            log.warn("没有任何关系信息!");
        } else {
            log.info("已删除{}条关系信息!", ids.length);
        }
        return JsonRequest.success(column);
    }
}
