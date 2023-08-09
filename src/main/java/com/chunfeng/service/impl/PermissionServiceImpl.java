package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.mapper.PermissionMapper;
import com.chunfeng.dao.mapper.PermissionRoleMapper;
import com.chunfeng.dao.mapper.PermissionRouterMapper;
import com.chunfeng.properties.SystemProperties;
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
     * 导入系统配置
     */
    @Autowired
    private SystemProperties systemProperties;

    /**
     * 导入关系业务
     */
    @Autowired
    private PermissionRoleMapper permissionRoleMapper;

    /**
     * 导入关系业务
     */
    @Autowired
    private PermissionRouterMapper permissionRouterMapper;

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
     * 根据ID值批量查询权限信息
     *
     * @param ids 权限ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "permission_select", key = "#ids")
    public JsonRequest<List<Permission>> lookPermissionById(String[] ids) {
        List<Permission> permissions = permissionMapper.selectAllPermissionById(ids);
        if (permissions.isEmpty()) {
            log.warn("未找到任何权限信息");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条权限数据!", permissions.size());
        return JsonRequest.success(permissions);
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
    @CacheEvict(value = {"permission_select", "role_select", "router_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> updateOnePermission(Permission permission) {
        JsonRequest<List<Permission>> request = permissionService.lookPermissionById(new String[]{permission.getId()});
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //配置控制
        if (systemProperties.getIsOpenDefaultDataProtect()) {
            Permission permission1;
            //解决传回来的data不为集合类型
            if (request.getData() instanceof Permission) {
                permission1 = (Permission) request.getData();
            }//获取路由
            else {
                permission1 = request.getData().get(0);
            }
            //判断是否为默认权限
            if (permission1.getIsDefault().equals(0)) {
                log.warn("ID为{}的权限为默认权限,不得修改标识符!", permission1.getId());
                return JsonRequest.error(RequestException.UPDATE_ERROR);
            }
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
    @CacheEvict(value = {"permission_select", "role_select", "router_select", "security_userDetail"}, allEntries = true)
    public JsonRequest<Integer> deletePermission(String[] ids) {
        //获取权限列表
        JsonRequest<List<Permission>> request = permissionService.lookPermissionById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //配置控制
        if (systemProperties.getIsOpenDefaultDataProtect()) {
            //排除默认的权限
            ids = request.getData()
                    .stream()
                    .filter(v -> v.getIsDefault() != 0)//获取非默认权限
                    .map(Permission::getId)//提取ID值
                    .toArray(String[]::new);
            //判断ID是否为空
            if (ids.length == 0) {
                log.error("删除权限失败!原因:不得删除默认权限!");
                return JsonRequest.error(RequestException.DELETE_ERROR);
            }
        }
        //删除权限-角色关系
        permissionRoleMapper.deletePermissionRoleByPer(ids);
        //删除权限-路由关系
        permissionRouterMapper.deletePermissionRouterByPer(ids);
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
