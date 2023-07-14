package com.chunfeng.config;

import com.xfvape.uid.UidGenerator;
import com.xfvape.uid.impl.CachedUidGenerator;
import com.xfvape.uid.worker.DisposableWorkerIdAssigner;
import org.springframework.context.annotation.Bean;

/**
 * UID字符生成器配置
 *
 * @author by  Java技术栈
 * <p>
 * 2023/7/11
 */
public class UIDGeneratorConfig {
    /**
     * ID生成器
     *
     * @return 生成器1
     */
    @Bean
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        DisposableWorkerIdAssigner disposableWorkerIdAssigner = new DisposableWorkerIdAssigner();
        return disposableWorkerIdAssigner;
    }

    /**
     * 性能优化生成
     *
     * @param disposableWorkerIdAssigner 原始生成器
     * @return 生成器对象
     */
    @Bean
    public UidGenerator cachedUidGenerator(DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
        return cachedUidGenerator;
    }
}
