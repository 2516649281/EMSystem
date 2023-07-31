package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.PermissionRole;
import com.chunfeng.dao.mapper.PermissionRoleMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IPermissionRoleService;
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
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IPermissionRoleService permissionRoleService;

    /**
     * 分类筛选关系信息
     *
     * @param permissionRole 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRole_select", key = "#permissionRole")
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
    @Cacheable(value = "permissionRole_select")
    public JsonRequest<List<PermissionRole>> lookAllPermissionRole() {
        return permissionRoleService.lookPermissionRole(new PermissionRole());
    }

    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "permissionRole_select", key = "#ids")
    public JsonRequest<List<PermissionRole>> lookPermissionRoleById(String[] ids) {
        List<PermissionRole> permissionRoles = permissionRoleMapper.selectAllPermissionRoleById(ids);
        if (permissionRoles.size() != ids.length) {
            log.warn("待查询的关系ID与数据库中的数量不符!数据库:{},实际:{}", permissionRoles.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条关系数据!", permissionRoles.size());
        return JsonRequest.success(permissionRoles);
    }

    /**
     * 批量绑定关系信息
     *
     * @param permissionRoles 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRole_select", "role_select"}, allEntries = true)
    public JsonRequest<Integer> addPermissionRole(List<PermissionRole> permissionRoles) {
        //判断关系是否已经存在
        Integer column = permissionRoleMapper.selectAllPermissionRoleCount(permissionRoles);
        if (column > 0) {
            log.error("添加关系数据失败!原因:该关系已在数据库中找到");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //日志信息注入
        List<PermissionRole> permissionRoleList = permissionRoles
                .stream()
                .peek(v -> {
                    v.setId(UIDCreateUtil.getUUId());
                    v.setCreateUser(SqlDateUtils.currentUserId);
                    v.setCreateTime(SqlDateUtils.date);
                }).collect(Collectors.toList());
        column = permissionRoleMapper.insertPermissionRole(permissionRoleList);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加{}条关系信息!", permissionRoles.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量修改关系数据
     *
     * @param permissionRoles 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRole_select", "role_select"}, allEntries = true)
    public JsonRequest<Integer> updatePermissionRole(List<PermissionRole> permissionRoles) {
        //判断关系是否存在
        Integer column = permissionRoleMapper.selectAllPermissionRoleCount(permissionRoles);
        if (column < permissionRoles.size()) {
            log.error("修改关系数据失败!原因:待修改的关系数据与数据库不符!数据库:{},实际:{}", column, permissionRoles.size());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //提取ID值
        String[] ids = permissionRoles.stream()
                .map(PermissionRole::getId)
                .toArray(String[]::new);
        List<PermissionRole> permissionRoles1 = permissionRoleMapper.selectAllPermissionRoleById(ids);
        //判断关系数据是否一致
        if (permissionRoles1.size() != permissionRoles.size()) {
            log.warn("数据库中未找到任何信息!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //封装日志
        List<PermissionRole> permissionRoleList = permissionRoles.stream().peek(v -> {
            //日志信息
            v.setUpdateUser(SqlDateUtils.currentUserId);
            v.setUpdateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //修改
        column = permissionRoleMapper.updatePermissionRoleById(permissionRoleList);
        if (column < 1) {
            log.error("修改关系信息失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("已修改{}条关系信息!", permissionRoleList.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"permissionRole_select", "role_select"}, allEntries = true)
    public JsonRequest<Integer> deletePermissionRole(String[] ids) {
        //判断关系是否存在
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
