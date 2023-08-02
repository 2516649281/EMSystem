package com.chunfeng.note;

import java.lang.annotation.*;

/**
 * 事务排除方法
 * <p>
 * 凡是在业务方法上标有此注解的，都不会执行事务功能
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ExcludeMethods {

}
