package com.chunfeng.config;

import com.chunfeng.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger全局配置
 * <p>
 * 用于配置Swagger API自动生成工具的配置
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * swagger配置键
     */
    @Autowired
    private SwaggerProperties swaggerProperties;

    /**
     * API接口核心配置
     *
     * @return 接口对象
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnabled())//是否启用
                .groupName(swaggerProperties.getGroupName())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                //排除默认错误界面
                .paths(PathSelectors.regex("(?!/error.*).*"))
                .build();
    }

    /**
     * 摘要信息
     *
     * @return 初始化API
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title(swaggerProperties.getTitle())
                //内容，包含作者的信息
                .contact(new Contact(swaggerProperties.getAuthor(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                //描述信息
                .description(swaggerProperties.getDescription())
                //版本号
                .version(swaggerProperties.getVersion())
                .build();
    }
}