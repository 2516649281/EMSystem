package com.chunfeng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 静态配置
 * <p>
 * 用于配置静态映射
 *
 * @author 春风能解释
 * 2023/7/17
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    /**
     * 静态资源映射
     *
     * @param registry 静态资源配置对象
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        //添加API映射
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        super.addResourceHandlers(registry);
    }

}
