// package com.chunfeng.handler;
//
// import com.chunfeng.dao.entity.Permission;
// import com.chunfeng.result.JsonRequest;
// import com.chunfeng.result.RequestException;
// import com.chunfeng.result.exception.ServiceException;
// import com.chunfeng.service.IPermissionService;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.ConfigAttribute;
// import org.springframework.security.access.SecurityConfig;
// import org.springframework.security.web.FilterInvocation;
// import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
// import org.springframework.stereotype.Component;
//
// import java.util.Collection;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
//
// /**
//  * 动态权限配置(路由设置)
//  *
//  * @author by 春风能解释
//  * <p>
//  * 2023/7/30
//  */
// @Component
// @Slf4j
// public class PermissionRouterSetHandler implements FilterInvocationSecurityMetadataSource {
//     /**
//      * 获得权限菜单
//      */
//     @Autowired
//     private IPermissionService permissionService;
//
//     /**
//      * 设置动态权限
//      *
//      * @param object 请求对象
//      * @return 安全对象的属性, 没有则返回空
//      * @throws IllegalArgumentException 验证失败则抛出此异常
//      */
//     @Override
//     public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//         //获取当前访问的URL
//         String url = ((FilterInvocation) object).getRequestUrl();
//         log.info("当前请求路由:{}", url);
//         Permission permission = new Permission();
//         permission.setServerRouter(url);
//         //通过业务层查询权限
//         JsonRequest<List<Permission>> request = permissionService.lookPermission(permission);
//         //判断权限是否查询完成
//         if (!request.getSuccess()) {
//             log.error("未找到任何权限!");
//             throw new ServiceException(RequestException.NOT_FOUND);
//         }
//         //创建配置集合
//         Set<ConfigAttribute> configAttributes = new HashSet<>();
//         // 拿到权限菜单
//         List<Permission> permissions = request.getData();
//         //包装权限
//         permissions.forEach(v -> configAttributes.add(new SecurityConfig(v.getSign())));
//         return configAttributes;
//     }
//
//     /**
//      * 返回实现类定义的所有 ConfigAttributes
//      *
//      * @return ConfigAttribute
//      */
//     @Override
//     public Collection<ConfigAttribute> getAllConfigAttributes() {
//         return null;
//     }
//
//     /**
//      * 是否能够提供 ConfigAttributes
//      *
//      * @param clazz 正在查询的类
//      * @return 如果可以提供，则返回 true
//      */
//     @Override
//     public boolean supports(Class<?> clazz) {
//         return FilterInvocation.class.isAssignableFrom(clazz);
//     }
// }
