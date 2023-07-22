package com.chunfeng;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 主启动类
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.chunfeng.dao.mapper")
public class EmSystemApplication {
    /**
     * 项目启动入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmSystemApplication.class, args);
        log.info("项目启动完成!主启动类:{}", context.getApplicationName());
    }
}
