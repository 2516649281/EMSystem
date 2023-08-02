package com.chunfeng.config;

import com.chunfeng.result.JsonRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 全局事务处理
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/1
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy
@Slf4j
public class TransactionalConfig {

    /**
     * 事务管理器
     */
    @Autowired
    private DataSourceTransactionManager transactionManager;

    /**
     * 排除切入点
     */
    @Pointcut("!@annotation(com.chunfeng.note.ExcludeMethods)")
    private void excludeMethods() {
    }

    /**
     * 排除切入点
     */
    @Pointcut("!execution(* com.chunfeng.service.impl.*.look*(..)))")
    private void lookMethods() {
    }

    /**
     * 业务切入点
     */
    @Pointcut("execution(* com.chunfeng.service.impl.*.*(..))")
    private void serviceMethods() {
    }

    /**
     * 最终切入点
     */
    @Pointcut("serviceMethods()&&lookMethods()&&excludeMethods()")
    private void method() {
    }

    /**
     * 执行逻辑
     *
     * @param pjp 方法对象
     * @return 方法返回值
     */
    @Around("method()")
    private Object Transaction(ProceedingJoinPoint pjp) {
        //初始化事务处理
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        TransactionStatus status = transactionManager.getTransaction(definition);
        log.info("事务处理器初始化完成!");
        //执行逻辑
        Object proceed;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("方法发生异常,事务回滚触发!原因:{}", throwable.getLocalizedMessage());
            //回滚事务
            transactionManager.rollback(status);
            return null;
        }
        //获取返回值
        JsonRequest<?> result;
        //判断返回类型
        if (!(proceed instanceof JsonRequest)) {
            // 提交事务
            log.warn("方法返回值不是JSON类型,提交事务触发!");
            transactionManager.commit(status);
            return proceed;
        }
        //将返回值转换为实际的
        result = (JsonRequest<?>) proceed;
        //判断JSON数据是否错误
        if (!result.getSuccess()) {
            log.error("方法执行出错,事务回滚触发!");
            transactionManager.rollback(status);
            return proceed;
        }
        log.info("方法执行成功,提交事务触发!");
        // 提交事务
        transactionManager.commit(status);
        return proceed;
    }
}
