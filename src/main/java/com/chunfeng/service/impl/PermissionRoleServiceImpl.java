package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.PermissionRole;
import com.chunfeng.dao.mapper.PermissionRoleMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IPermissionRoleService;
import com.chunfeng.utils.SqlDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限-角色关系业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
@Transactional
public class PermissionRoleServiceImpl implements IPermissionRoleService {

    /**
     * 关系数据层
     */
    @Autowired
    private PermissionRoleMapper permissionRoleMapper;

    /**
     * 分类筛选关系信息
     *
     * @param permissionRole 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRole_select", key = "#permissionRole.hashCode()")
    public JsonRequest<List<PermissionRole>> lookPermissionRole(PermissionRole permissionRole) {
        List<PermissionRole> permissionRoles = permissionRoleMapper.selectAllPermissionRole(permissionRole);
        //判断是否成功
        if (permissionRoles.isEmpty()) {
            log.warn("未找到任何关系信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条关系信息", permissionRoles.size());
        return JsonRequest.success(permissionRoles);
    }

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    @Override
    public JsonRequest<List<PermissionRole>> lookAllPermissionRole() {
        return lookPermissionRole(new PermissionRole());
    }

    /**
     * 新增一条关系信息
     *
     * @param permissionRole 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "permissionRole_select", allEntries = true)
    public JsonRequest<Integer> addOnePermissionRole(PermissionRole permissionRole) {
        //日志信息
        permissionRole.setCreateUser(SqlDateUtils.currentUserId);
        permissionRole.setCreateTime(SqlDateUtils.date);
        Integer column = permissionRoleMapper.insertPermissionRole(permissionRole);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加一条关系信息!");
        return JsonRequest.success(column);
    }

    /**
     * 修改一条关系信息
     *
     * @param permissionRole 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRole_select"}, allEntries = true)
    public JsonRequest<Integer> updateOnePermissionRole(PermissionRole permissionRole) {
        List<PermissionRole> permissionRoles = permissionRoleMapper.selectAllPermissionRoleById(new String[]{permissionRole.getId()});
        //判断是否成功
        if (permissionRoles.isEmpty()) {
            log.warn("数据库中不存在ID为{}的关系信息!", permissionRole.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        permissionRole.setUpdateUser(SqlDateUtils.currentUserId);
        permissionRole.setUpdateTime(SqlDateUtils.date);
        Integer column = permissionRoleMapper.updatePermissionRoleById(permissionRole);
        if (column < 1) {
            log.error("修改ID为{}的关系信息失败!", permissionRole.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的关系信息修改成功!", permissionRole.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRole_select"}, allEntries = true)
    public JsonRequest<Integer> deletePermissionRole(String[] ids) {
        List<PermissionRole> permissionRoles = permissionRoleMapper.selectAllPermissionRoleById(ids);
        if (permissionRoles.size() != ids.length) {
            log.error("删除关系信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", permissionRoles.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = permissionRoleMapper.deletePermissionRoleById(ids);
        if (column < 1) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条关系信息!", ids.length);
        return JsonRequest.success(column);
    }
}
