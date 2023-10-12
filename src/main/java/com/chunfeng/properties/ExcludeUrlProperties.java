package com.chunfeng.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 拦截器配置键
 * <p>
 * 此配置用于存放需要匿名通过拦截的路径，例如静态资源和登录注册路由
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@ConfigurationProperties(prefix = "handle.config")
@Component
@Data
@NoArgsConstructor
public class ExcludeUrlProperties {
    /**
     * 排除路径
     */
    private String[] excludeUrl;
    /**
     * 静态资源
     */
    private String[] staticUrl;
}
