package com.chunfeng.note;

import java.lang.annotation.*;

/**
 * 扩展方法
 * <p>
 * 标有此注解的方法将加入AOP切面
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/2
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OtherMethods {
}
