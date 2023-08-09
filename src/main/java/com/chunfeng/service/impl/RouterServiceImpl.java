package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.dao.entity.Router;
import com.chunfeng.dao.mapper.PermissionRouterMapper;
import com.chunfeng.dao.mapper.RouterMapper;
import com.chunfeng.properties.SystemProperties;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IPermissionRouterService;
import com.chunfeng.service.IPermissionService;
import com.chunfeng.service.IRouterService;
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
 * 路由业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class RouterServiceImpl implements IRouterService {

    /**
     * 路由数据层
     */
    @Autowired
    private RouterMapper routerMapper;

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
    private IRouterService routerService;

    /**
     * 导入系统配置
     */
    @Autowired
    private SystemProperties systemProperties;

    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 导入权限业务
     */
    @Autowired
    private IPermissionService permissionService;

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
    @Cacheable(value = "router_select")
    public JsonRequest<List<Router>> lookAllRouter() {
        return routerService.lookRouter(new Router());
    }

    /**
     * 根据ID值批量查询路由信息
     *
     * @param ids ID值
     * @return JSON
     */
    @Override
    @Cacheable(value = "router_select", key = "#ids")
    public JsonRequest<List<Router>> lookRouterById(String[] ids) {
        List<Router> routers = routerMapper.selectAllRouterById(ids);
        if (routers.size() != ids.length) {
            log.warn("待查询的路由ID与数据库中的数量不符!数据库:{},实际:{}", routers.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条路由数据!", routers.size());
        return JsonRequest.success(routers);
    }

    /**
     * 查询一条路由信息
     *
     * @param routerId 路由ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "router_select", key = "#routerId")
    public JsonRequest<Router> lookOneRouter(String routerId) {
        //构造条件
        Router router = new Router();
        router.setId(routerId);
        //查询一条路由信息
        JsonRequest<List<Router>> request = routerService.lookRouter(router);
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //获取单个路由
        Router router1 = request.getData().get(0);
        //构造条件
        PermissionRouter permissionRouter = new PermissionRouter();
        permissionRouter.setRouterId(routerId);
        //查询对应的关系
        JsonRequest<List<PermissionRouter>> jsonRequest = permissionRouterService.lookPermissionRouter(permissionRouter);
        if (!jsonRequest.getSuccess()) {
            log.warn("{}", jsonRequest.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //获得权限ID
        String[] ids = jsonRequest.getData().stream()
                .map(PermissionRouter::getPermissionId)
                .toArray(String[]::new);
        //最终获得权限列表
        JsonRequest<List<Permission>> request1 = permissionService.lookPermissionById(ids);
        //判断是否成功
        if (!request1.getSuccess()) {
            log.warn("{}", request1.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        router1.setPermissionList(request1.getData());
        return JsonRequest.success(router1);
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
        JsonRequest<List<Router>> request = routerService.lookRouterById(new String[]{router.getId()});
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //配置控制
        if (systemProperties.getIsOpenDefaultDataProtect()) {
            Router router1;
            //解决传回来的data不为集合类型
            if (request.getData() instanceof Router) {
                router1 = (Router) request.getData();
            }//获取路由
            else {
                router1 = request.getData().get(0);
            }
            //判断是否为默认路由
            if (router1.getIsDefault().equals(0)) {
                log.warn("ID为{}的权限为默认路由,不允许修改!", router.getId());
                return JsonRequest.error(RequestException.UPDATE_ERROR);
            }
        }
        //首先删除路由绑定的权限信息
        permissionRouterService.deletePermissionRouterByRouter(new String[]{router.getId()});
        //构造条件
        List<PermissionRouter> permissionRouterList = router
                .getPermissionList()//获取权限列表
                .stream()
                .map(v -> {
                    PermissionRouter permissionRouter = new PermissionRouter();
                    permissionRouter.setRouterId(router.getId());//路由ID
                    permissionRouter.setPermissionId(v.getId());//权限ID
                    return permissionRouter;
                })//重构
                .collect(Collectors.toList());//转换成集合
        //再绑定权限信息
        JsonRequest<Integer> request2 = permissionRouterService
                .addPermissionRouter(permissionRouterList);
        //判断是否成功
        if (!request2.getSuccess()) {
            log.error("添加条件失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        router.setUpdateUser(SqlDateUtils.currentUserId);
        router.setUpdateTime(SqlDateUtils.date);
        //最后修改本体
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
        //获取路由列表
        JsonRequest<List<Router>> request = routerService.lookRouterById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        // 配置控制
        if (systemProperties.getIsOpenDefaultDataProtect()) {
            //排除默认的路由
            ids = request.getData()
                    .stream()
                    .filter(v -> v.getIsDefault() != 0)//获取非默认路由
                    .map(Router::getId)//提取ID值
                    .toArray(String[]::new);
            //判断ID是否为空
            if (ids.length == 0) {
                log.error("删除路由失败!原因:不得删除默认路由!");
                return JsonRequest.error(RequestException.DELETE_ERROR);
            }
        }
        //删除原有绑定的关系
        permissionRouterMapper.deletePermissionRouterByRid(ids);
        //删除本体
        Integer column = routerMapper.deleteRouterById(ids);
        if (column < 1) {
            log.error("删除路由失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条路由信息!", ids.length);
        return JsonRequest.success(column);
    }
}
