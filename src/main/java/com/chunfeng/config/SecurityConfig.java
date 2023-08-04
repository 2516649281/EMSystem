package com.chunfeng.config;

import com.chunfeng.handler.PermissionRouterControllerHandler;
import com.chunfeng.handler.PermissionRouterSetHandler;
import com.chunfeng.handler.TokenFilter;
import com.chunfeng.properties.ExcludeUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity全局配置
 * <p>
 * SpringSecurity框架的核心配置类，用于配置SpringSecurity基本的权限、拦截处理、授权管理等配置
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * token拦截器
     */
    @Autowired
    private TokenFilter tokenFilter;

    /**
     * 排除路径
     */
    @Autowired
    private ExcludeUrlProperties excludeUrlProperties;

    /**
     * 控制类
     */
    @Autowired
    private PermissionRouterControllerHandler permissionRouterControllerHandler;

    /**
     * 配置类
     */
    @Autowired
    private PermissionRouterSetHandler permissionRouterSetHandler;

    /**
     * http核心配置
     *
     * @param http http对象
     * @throws Exception 如果出现错误
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 允许匿名访问
                .authorizeRequests()
                //动态权限设置
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(permissionRouterControllerHandler);
                        o.setSecurityMetadataSource(permissionRouterSetHandler);
                        return o;
                    }
                })
                // 其他请求必须授权
                .anyRequest().authenticated();
        //拦截器配置
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors();//允许跨域
        log.info("SpringSecurity-http核心已成功配置!");
    }

    /**
     * SpringSecurity Web配置
     *
     * @param web web配置对象
     */
    @Override
    public void configure(WebSecurity web) {
        web
                //全局排除路径
                .ignoring()
                .antMatchers(excludeUrlProperties.getExcludeUrl());
    }

    /**
     * 验证管理配置
     *
     * @return 身份验证对象
     * @throws Exception 如果出现异常
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("SpringSecurity-验证管理已成功配置!");
        return super.authenticationManagerBean();
    }

    /**
     * 密码加密器(用于注册加密)
     *
     * @return 密码加密器
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.info("SpringSecurity-密码加密已成功配置!");
        return new BCryptPasswordEncoder();
    }
}
