package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.mapper.PermissionMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
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
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IPermissionService permissionService;

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
     * 新增一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "permission_select", allEntries = true)
    public JsonRequest<Integer> addOnePermission(Permission permission) {
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
    @CacheEvict(value = {"permission_select", "role_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOnePermission(Permission permission) {
        List<Permission> permissions = permissionMapper.selectAllPermissionById(new String[]{permission.getId()});
        //判断是否成功
        if (permissions.isEmpty()) {
            log.warn("数据库中不存在ID为{}的权限信息!", permission.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
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
    @CacheEvict(value = {"permission_select", "role_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deletePermission(String[] ids) {
        List<Permission> permissions = permissionMapper.selectAllPermissionById(ids);
        if (permissions.size() != ids.length) {
            log.error("删除权限信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", permissions.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = permissionMapper.deletePermissionById(ids);
        if (column < 1) {
            log.error("删除权限失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条权限信息!", ids.length);
        return JsonRequest.success(column);
    }
}
