package com.chunfeng.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS跨域配置
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
public class CorsConfig implements WebMvcConfigurer {
    /**
     * 跨域配置
     *
     * @param registry 跨域配置类
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许的路径
                .addMapping("/**")
                //允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                //允许的请求头
                .allowedHeaders("*")
                //允许的请求源
                .allowedOrigins("*");
    }
}
