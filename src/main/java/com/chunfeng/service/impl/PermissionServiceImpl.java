package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.dao.entity.Router;
import com.chunfeng.dao.mapper.PermissionMapper;
import com.chunfeng.dao.mapper.RouterMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IPermissionRouterService;
import com.chunfeng.service.IPermissionService;
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
import java.util.stream.Collectors;

/**
 * 权限业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    /**
     * 权限数据层
     */
    @Autowired
    private PermissionMapper permissionMapper;

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
    private IPermissionService permissionService;

    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 分类筛选权限信息
     *
     * @param permission 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "permission_select", key = "#permission")
    public JsonRequest<List<Permission>> lookPermission(Permission permission) {
        List<Permission> permissions = permissionMapper.selectAllPermission(permission);
        //判断是否成功
        if (permissions.isEmpty()) {
            log.warn("未找到任何权限信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条权限信息", permissions.size());
        return JsonRequest.success(permissions);
    }

    /**
     * 查询所有权限信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "permission_select")
    public JsonRequest<List<Permission>> lookAllPermission() {
        return permissionService.lookPermission(new Permission());
    }

    /**
     * 根据ID值查询权限信息
     *
     * @param permissionId 权限ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "permission_select", key = "#permissionId")
    public JsonRequest<Permission> lookPermissionById(String permissionId) {
        //构造条件
        Permission permission = new Permission();
        permission.setId(permissionId);
        //调用本地方法使其缓存生效
        JsonRequest<List<Permission>> lookPermission = permissionService.lookPermission(permission);
        //获得指定ID的权限信息
        Permission permission1 = lookPermission.getData().get(0);
        //构造条件
        PermissionRouter permissionRouter = new PermissionRouter();
        permissionRouter.setPermissionId(permission1.getId());
        //查询路由
        JsonRequest<List<PermissionRouter>> lookPermissionRouter = permissionRouterService.lookPermissionRouter(permissionRouter);
        //获得权限ID
        String[] routerId =
                lookPermissionRouter.getData().
                        stream()
                        .map(PermissionRouter::getRouterId)
                        .toArray(String[]::new);
        // 最终获得路由列表
        List<Router> routers = routerMapper.selectAllRouterById(routerId);
        if (routers.isEmpty()) {
            log.error("未查询到任何路由列表!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //存入权限中
        permission1.setRouterList(routers);
        return JsonRequest.success(permission1);
    }


    /**
     * 新增一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "permission_select", allEntries = true)
    public JsonRequest<Integer> addOnePermission(Permission permission) {
        //获取标识符
        String sign = permission.getSign();
        // 构造条件
        Permission permission1 = new Permission();
        permission1.setSign(sign);
        JsonRequest<List<Permission>> request = permissionService.lookPermission(permission1);
        //如果存在该标识符
        if (request.getSuccess()) {
            log.error("添加权限名为{}的数据失败!原因:标识符已存在!", permission.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //日志信息
        permission.setId(UIDCreateUtil.getUUId());
        permission.setCreateUser(SqlDateUtils.currentUserId);
        permission.setCreateTime(SqlDateUtils.date);
        Integer column = permissionMapper.insertPermission(permission);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加权限名为{}的数据失败!", permission.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加名为{}的权限信息!", permission.getName());
        return JsonRequest.success(column);
    }

    /**
     * 修改一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permission_select", "permission_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOnePermission(Permission permission) {
        List<Permission> permissions = permissionMapper.selectAllPermissionById(new String[]{permission.getId()});
        //判断是否成功
        if (permissions.isEmpty()) {
            log.warn("数据库中不存在ID为{}的权限信息!", permission.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //获取权限
        Permission permission1 = permissions.get(0);
        //判断是否为默认权限
        if (permission1.getIsDefault().equals(0)) {
            log.warn("ID为{}的权限为默认权限,不得修改标识符!", permission1.getId());
            //不允许修改标识符
            permission.setSign(permission1.getSign());
            //不允许修改状态
            permission.setIsDefault(permission1.getIsDefault());
        }
        //日志信息
        permission.setUpdateUser(SqlDateUtils.currentUserId);
        permission.setUpdateTime(SqlDateUtils.date);
        Integer column = permissionMapper.updatePermissionById(permission);
        if (column < 1) {
            log.error("修改ID为{}的权限信息失败!", permission.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的权限信息修改成功!", permission.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除权限信息
     *
     * @param ids 权限ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permission_select", "permission_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deletePermission(String[] ids) {
        List<Permission> permissions = permissionMapper.selectAllPermissionById(ids);
        //替换ID
        ids = permissions
                .stream()
                .filter(v -> !v.getIsDefault().equals(0))//剔除默认元素
                .map(Permission::getId)//取出可删除的ID
                .toArray(String[]::new);//转换成String数组
        //替换原集合
        permissions = permissions
                .stream()
                .filter(v -> !v.getIsDefault().equals(0))
                .collect(Collectors.toList());
        //剔除默认权限
        if (permissions.size() != ids.length) {
            log.error("删除权限信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", permissions.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //判断ID是否为空
        if (ids.length == 0) {
            log.error("删除权限失败!原因:未选择任何权限");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //正式删除
        Integer column = permissionMapper.deletePermissionById(ids);
        if (column < 1) {
            log.error("删除权限失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条权限信息!", ids.length);
        return JsonRequest.success(column);
    }
}
