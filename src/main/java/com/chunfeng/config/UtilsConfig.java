package com.chunfeng.config;

import com.chunfeng.utils.FileMangerUtils;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 工具类的配置类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/21
 */
@Configuration
// 开启缓存
@EnableCaching
public class UtilsConfig {
    /**
     * 通用文件处理
     *
     * @return FileMangerUtils-?
     */
    @Bean
    public FileMangerUtils<?> getFileUtils() {
        return new FileMangerUtils<>();
    }
}
