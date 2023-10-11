package com.chunfeng.handler;

import com.chunfeng.dao.security.UserDetail;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限路由控制(路由判断)
 * <p>
 * 此类是SpringSecurity动态授权的核心逻辑处理类，需要通过{@link com.chunfeng.handler.PermissionRouterSetHandler}
 * 进行配置后，通过返回的配置对象与用于当前用户拥有的权限进行比较，从而判断用户是否有权
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
@Slf4j
public class PermissionRouterControllerHandler implements AccessDecisionManager {

    /**
     * 判断是否有权访问
     *
     * @param authentication   权限对象
     * @param object           当前路由
     * @param configAttributes 已配置的权限配置
     * @throws InsufficientAuthenticationException 访问拒绝,未拥有充足的权限
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws org.springframework.security.access.AccessDeniedException, InsufficientAuthenticationException {
        Object principal = authentication.getPrincipal();
        // 如果获取不到
        if (!(principal instanceof UserDetail)) {
            log.warn("用户未登录!");
            throw new ServiceException(RequestException.FORBIDDEN);
        }
        // 获取当前用户拥有的权限
        List<String> permission = ((UserDetail) principal).getPermission();
        //求交集
        List<ConfigAttribute> list = configAttributes.stream()
                .filter(v -> permission.contains(v.getAttribute()))//获取相同内容
                .collect(Collectors.toList());//收集并转换为集合
        // 交集为空,则非法授权
        if (list.isEmpty()) {
            log.error("非法授权!");
            throw new ServiceException(RequestException.UNAUTHORIZED);
        }
        log.info("通过授权!");
    }

    /**
     * 是否能处理ConfigAttribute
     *
     * @param attribute 权限配置对象
     * @return 如果能处理则返回true
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 是否提供投票机制
     *
     * @param clazz 当前访问的对象
     * @return 如果可以实现则返回true
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
