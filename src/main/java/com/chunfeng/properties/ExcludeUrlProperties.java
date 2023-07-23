package com.chunfeng.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 拦截器配置键
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
}
