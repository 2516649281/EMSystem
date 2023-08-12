package com.chunfeng.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger核心配置键
 * <p>
 * 用于存放Swagger的配置，仅在开发环境下使用，生产环境下务必将[是否启用]改为false
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/21
 */
@Component
@ConfigurationProperties("springfox.swagger2")
@Data
@NoArgsConstructor
public class SwaggerProperties {
    /**
     * 是否启用
     */
    private Boolean enabled = false;
    /**
     * 组名称
     */
    private String groupName = "";
    /**
     * API标题
     */
    private String title = "";
    /**
     * 作者
     */
    private String author = "";
    /**
     * 个人url
     */
    private String url = "";
    /**
     * 电子邮件
     */
    private String email = "";
    /**
     * swagger描述
     */
    private String description = "";
    /**
     * 版本描述
     */
    private String version = "1.0";
}
