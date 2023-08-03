package com.chunfeng.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用配置
 * <p>
 * 此配置用于规避用户在操作权限和路由时，将后端代码已配置的默认数据删除，造成后端安全框架混乱的情况
 * <p>
 * 该情况仅出现在[动态权限]还未完成的情况,若已完成,可忽略
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/3
 */
@Component
@ConfigurationProperties("system.config")
@Data
@NoArgsConstructor
public class SystemProperties {
    /**
     * 是否开启默认数据保护机制
     */
    private Boolean isOpenDefaultDataProtect = true;
}
