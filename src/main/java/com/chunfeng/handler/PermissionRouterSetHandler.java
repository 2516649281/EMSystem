package com.chunfeng.handler;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.Router;
import com.chunfeng.dao.mapper.PermissionMapper;
import com.chunfeng.dao.mapper.PermissionRouterMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.service.IRouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 动态权限配置(路由设置)
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
@Slf4j
public class PermissionRouterSetHandler implements FilterInvocationSecurityMetadataSource {
    /**
     * 导入权限数据层
     */
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 导入关系数据层
     */
    @Autowired
    private PermissionRouterMapper permissionRouterMapper;

    /**
     * 导入路由业务
     */
    @Autowired
    private IRouterService routerService;

    /**
     * 设置动态权限
     *
     * @param object 请求对象
     * @return 安全对象的属性, 没有则返回空
     * @throws IllegalArgumentException 验证失败则抛出此异常
     */
    @Override
    @Cacheable(value = "permission_select", key = "object")
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取当前访问的URL
        FilterInvocation invocation = (FilterInvocation) object;
        //获取请求方式
        String method = invocation.getHttpRequest().getMethod();
        String url = invocation.getRequestUrl();
        //加工url，去除参数
        url = url.substring(0, url.lastIndexOf("?"));
        log.info("当前请求路由:{}", url);
        //查找后端的路由
        Router router = new Router();
        router.setValue(url);
        router.setType(0);
        router.setMethod(method);
        //通过业务层查询路由对应的ID
        JsonRequest<List<Router>> request = routerService.lookRouter(router);
        //判断权限是否查询完成
        if (!request.getSuccess()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.NOT_FOUND);
        }
        // 拿到权限菜单
        List<Router> routers = request.getData();
        //获得ID值
        String[] routerIds = routers
                .stream()
                .map(Router::getId)//提取ID值
                .toArray(String[]::new);//转换为数组
        //获得权限ID
        List<String> per = permissionRouterMapper.selectAllPermissionRouterByRouterId(routerIds);
        if (per == null || per.isEmpty()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.NOT_FOUND);
        }
        String[] ids = per
                .toArray(new String[0]);
        //最终获得权限菜单
        List<Permission> permissions = permissionMapper
                .selectAllPermissionById(ids);
        //创建配置对象
        Collection<ConfigAttribute> collection = new HashSet<>();
        //加入配置菜单
        permissions.forEach(v -> {
            collection.add(new SecurityConfig(v.getSign()));
        });
        log.info("动态权限配置完成!");
        return collection;
    }

    /**
     * 返回实现类定义的所有 ConfigAttributes
     *
     * @return ConfigAttribute
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 是否能够提供 ConfigAttributes
     *
     * @param clazz 正在查询的类
     * @return 如果可以提供，则返回 true
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
