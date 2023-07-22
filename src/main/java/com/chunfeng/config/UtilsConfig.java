package com.chunfeng.config;

import com.chunfeng.dao.entity.Exam;
import com.chunfeng.dao.entity.Problem;
import com.chunfeng.utils.FileMangerUtils;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * 针对题库的bean
     *
     * @return FileMangerUtils-Problem
     */
    @Bean
    public FileMangerUtils<Problem> getFileProblemUtils() {
        return new FileMangerUtils<>();
    }

    /**
     * 针对试卷的bean
     *
     * @return FileMangerUtils-Exam
     */
    @Bean
    public FileMangerUtils<Exam> getFileExamUtils() {
        return new FileMangerUtils<>();
    }
}
