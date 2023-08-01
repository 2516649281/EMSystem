package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.PermissionRole;
import com.chunfeng.dao.entity.Role;
import com.chunfeng.dao.mapper.PermissionRoleMapper;
import com.chunfeng.dao.mapper.RoleMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IPermissionRoleService;
import com.chunfeng.service.IPermissionService;
import com.chunfeng.service.IRoleService;
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
 * 角色业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {

    /**
     * 角色数据层
     */
    @Autowired
    private RoleMapper roleMapper;

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
    private IRoleService roleService;

    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRoleService permissionRoleService;

    /**
     * 导入权限业务
     */
    @Autowired
    private IPermissionService permissionService;

    /**
     * 分类筛选角色信息
     *
     * @param role 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "role_select", key = "#role")
    public JsonRequest<List<Role>> lookRole(Role role) {
        List<Role> roles = roleMapper.selectAllRole(role);
        //判断是否成功
        if (roles.isEmpty()) {
            log.warn("未找到任何角色信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条角色信息", roles.size());
        return JsonRequest.success(roles);
    }

    /**
     * 查询所有角色信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "role_select")
    public JsonRequest<List<Role>> lookAllRole() {
        return roleService.lookRole(new Role());
    }

    /**
     * 根据ID值批量查询角色信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "role_select", key = "#ids")
    public JsonRequest<List<Role>> lookRoleById(String[] ids) {
        List<Role> roles = roleMapper.selectAllRoleById(ids);
        if (roles.size() != ids.length) {
            log.warn("待查询的角色ID与数据库中的数量不符!数据库:{},实际:{}", roles.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条角色数据!", roles.size());
        return JsonRequest.success(roles);
    }

    /**
     * 根据ID值查询角色信息
     *
     * @param roleId 角色ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "role_select", key = "#roleId")
    public JsonRequest<Role> lookOneRole(String roleId) {
        //构造条件
        Role role = new Role();
        role.setId(roleId);
        //调用本地方法使其缓存生效
        JsonRequest<List<Role>> lookRole = roleService.lookRole(role);
        //判断角色是否存在
        if (!lookRole.getSuccess()) {
            log.warn("{}", lookRole.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //获取单个权限
        Role role1 = lookRole.getData().get(0);
        //构造条件
        PermissionRole permissionRole = new PermissionRole();
        permissionRole.setRoleId(roleId);
        //查询关系绑定的权限
        JsonRequest<List<PermissionRole>> lookPermissionRole = permissionRoleService.lookPermissionRole(permissionRole);
        //获得权限ID
        String[] permissionIds =
                lookPermissionRole.getData().
                        stream()
                        .map(PermissionRole::getPermissionId)
                        .toArray(String[]::new);
        // 最终获得权限列表
        JsonRequest<List<Permission>> request = permissionService.lookPermissionById(permissionIds);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //存入角色中
        role1.setPermissionList(request.getData());
        return JsonRequest.success(role1);
    }


    /**
     * 新增一条角色信息
     *
     * @param role 角色信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "role_select", allEntries = true)
    public JsonRequest<Integer> addOneRole(Role role) {
        //日志信息
        role.setId(UIDCreateUtil.getUUId());
        role.setCreateUser(SqlDateUtils.currentUserId);
        role.setCreateTime(SqlDateUtils.date);
        Integer column = roleMapper.insertRole(role);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加角色名为{}的数据失败!", role.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加名为{}的角色信息!", role.getName());
        return JsonRequest.success(column);
    }

    /**
     * 修改一条角色信息
     *
     * @param role 角色信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"role_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOneRole(Role role) {
        List<Role> roles = roleMapper.selectAllRoleById(new String[]{role.getId()});
        //判断是否成功
        if (roles.isEmpty()) {
            log.warn("数据库中不存在ID为{}的角色信息!", role.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        role.setUpdateUser(SqlDateUtils.currentUserId);
        role.setUpdateTime(SqlDateUtils.date);
        Integer column = roleMapper.updateRoleById(role);
        if (column < 1) {
            log.error("修改ID为{}的角色信息失败!", role.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的角色信息修改成功!", role.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除角色信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"role_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deleteRole(String[] ids) {
        List<Role> roles = roleMapper.selectAllRoleById(ids);
        if (roles.size() != ids.length) {
            log.error("删除角色信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", roles.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除关系
        Integer column = permissionRoleMapper.deletePermissionRoleByRole(ids);
        if (column < 1) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除本体
        column = roleMapper.deleteRoleById(ids);
        if (column < 1) {
            log.error("删除角色失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条角色信息!", ids.length);
        return JsonRequest.success(column);
    }
}
