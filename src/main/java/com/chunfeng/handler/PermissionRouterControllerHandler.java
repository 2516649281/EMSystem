package com.chunfeng.handler;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限路由控制(路由判断)
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
public class PermissionRouterControllerHandler implements AccessDecisionManager {
    /**
     * 判断是否有权访问
     *
     * @param authentication   权限对象
     * @param object           当前路由
     * @param configAttributes 权限配置
     * @throws AccessDeniedException               如果访问被拒绝(未拥有对应权限)
     * @throws InsufficientAuthenticationException 访问拒绝,为拥有充足的权限
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws org.springframework.security.access.AccessDeniedException, InsufficientAuthenticationException {

    }

    /**
     * Indicates whether this <code>AccessDecisionManager</code> is able to process
     * authorization requests presented with the passed <code>ConfigAttribute</code>.
     * <p>
     * This allows the <code>AbstractSecurityInterceptor</code> to check every
     * configuration attribute can be consumed by the configured
     * <code>AccessDecisionManager</code> and/or <code>RunAsManager</code> and/or
     * <code>AfterInvocationManager</code>.
     * </p>
     *
     * @param attribute a configuration attribute that has been configured against the
     *                  <code>AbstractSecurityInterceptor</code>
     * @return true if this <code>AccessDecisionManager</code> can support the passed
     * configuration attribute
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    /**
     * Indicates whether the <code>AccessDecisionManager</code> implementation is able to
     * provide access control decisions for the indicated secured object type.
     *
     * @param clazz the class that is being queried
     * @return <code>true</code> if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
