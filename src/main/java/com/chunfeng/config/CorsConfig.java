package com.chunfeng.config;

import com.chunfeng.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS跨域配置
 * <p>
 * 用于配置浏览器的跨域保护机制
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Component
@Slf4j
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 导入系统配置
     */
    @Autowired
    private SystemProperties systemProperties;

    /**
     * 跨域配置
     *
     * @param registry 跨域配置类
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        Boolean config = systemProperties.getIsOpenCorsConfig();
        if (config) {
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
        log.info("跨域处理器初始化完成!状态:{}", config ? "开启" : "关闭");
    }
}
