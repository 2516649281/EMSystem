package com.chunfeng.config;

import com.chunfeng.handler.AccessDeniedException;
import com.chunfeng.handler.AuthenticationException;
import com.chunfeng.handler.TokenFilter;
import com.chunfeng.properties.ExcludeUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity全局配置
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
     * 授权过滤器
     */
    @Autowired
    private AccessDeniedException accessDeniedException;

    /**
     * 认证过滤器
     */
    @Autowired
    private AuthenticationException authenticationException;

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
                .authorizeRequests().antMatchers(excludeUrlProperties.getExcludeUrl()).permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //拦截器配置
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedException)//授权异常处理
                .authenticationEntryPoint(authenticationException);//认证异常处理
        http.cors();//允许跨域
        log.info("SpringSecurity-http核心已成功配置!");
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
