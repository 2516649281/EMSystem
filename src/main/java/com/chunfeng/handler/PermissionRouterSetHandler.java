package com.chunfeng.handler;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.dao.entity.Router;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.service.IPermissionRouterService;
import com.chunfeng.service.IPermissionService;
import com.chunfeng.service.IRouterService;
import com.chunfeng.utils.ExcludeUrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * <p>
 * 此类是SpringSecurity动态授权的核心配置类，用户每发一次都会经过此拦截器，
 * 拦截器根据路由与数据库中进行比较，从而生成用户的权限对象
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
@Slf4j
public class PermissionRouterSetHandler implements FilterInvocationSecurityMetadataSource {
    /**
     * 导入权限业务
     */
    @Autowired
    private IPermissionService permissionService;

    /**
     * 导入关系业务
     */
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 导入路由业务
     */
    @Autowired
    private IRouterService routerService;

    /**
     * 路径判断工具类
     */
    @Autowired
    private ExcludeUrlUtils excludeUrlUtils;

    /**
     * 设置动态权限
     *
     * @param object 请求对象
     * @return 安全对象的属性, 没有则返回空
     * @throws IllegalArgumentException 验证失败则抛出此异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //创建配置对象
        Collection<ConfigAttribute> collection = new HashSet<>();
        //获取当前访问的URL
        FilterInvocation invocation = (FilterInvocation) object;
        //获取请求路径
        String url = invocation.getRequest().getRequestURI();
        log.info("当前请求路由:{}", url);
        //判断路径
        Integer index = excludeUrlUtils.isExcludeUrl(url);
        switch (index) {
            //静态资源
            case 1:
                collection.add(new SecurityConfig("STATIC"));
                return collection;
            //匿名路径
            case 2:
                collection.add(new SecurityConfig("EXCLUDE"));
                return collection;
        }
        //获取请求方式
        String method = invocation.getHttpRequest().getMethod();
        //查找后端的路由
        Router router = new Router();
        router.setValue(url);
        router.setType(0);
        router.setMethod(method);
        //通过业务层查询路由对应的ID
        JsonRequest<List<Router>> request = routerService.lookRouter(router);
        //判断权限是否查询完成
        if (!request.getSuccess()) {
            log.error("未找到任何路由!");
            throw new ServiceException(RequestException.NOT_FOUND);
        }
        // 拿到路由菜单
        List<Router> routers = request.getData();
        //获得ID值
        String[] routerIds = routers
                .stream()
                .map(Router::getId)//提取ID值
                .toArray(String[]::new);//转换为数组
        //获得权限ID
        JsonRequest<List<PermissionRouter>> request1 = permissionRouterService.lookPermissionRouterByRouter(routerIds);
        if (!request1.getSuccess()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.UNAUTHORIZED);
        }
        // 获取权限ID
        String[] ids = request1.getData()
                .stream()
                .map(PermissionRouter::getPermissionId)//从中获取权限ID
                .toArray(String[]::new);//转换为数组
        //最终获得权限菜单
        JsonRequest<List<Permission>> jsonRequest = permissionService
                .lookPermissionById(ids);
        if (!jsonRequest.getSuccess()) {
            log.error("未找到任何权限!");
            throw new ServiceException(RequestException.UNAUTHORIZED);
        }
        //加入配置菜单
        jsonRequest.getData()
                .forEach(v -> collection
                        .add(new SecurityConfig(v.getSign())));
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
