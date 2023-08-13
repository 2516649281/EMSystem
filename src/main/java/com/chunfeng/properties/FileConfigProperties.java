package com.chunfeng.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件配置
 * <p>
 * 用于统计文件上传的数据，统一管理文件配置
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Component
@ConfigurationProperties(prefix = "file.config")
@Data
@NoArgsConstructor
public class FileConfigProperties {

    /**
     * 文件输出路径
     */
    private String url;

    /**
     * 最大文件大小
     */
    private Long fileMaxSize;
    /**
     * 默认头像文件
     */
    private String defaultFile = "default.png";
}
