package com.chunfeng.utils;

import com.chunfeng.properties.ExcludeUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * 排除路径判断
 *
 * @author by 春风能解释
 * <p>
 * 2023/10/12
 */
@Component
@Slf4j
public class ExcludeUrlUtils {

    /**
     * 排除路径
     */
    @Autowired
    private ExcludeUrlProperties excludeUrlProperties;

    /**
     * 判断原路径是否在已定义的排除路径之中
     *
     * @param origin 源路径
     * @return 静态与匿名路径表示法如下:
     * <ul>
     *     <li>0为不包含于任何</li>
     *     <li>1为包含于匿名路径</li>
     *     <li>2为包含于静态路径</li>
     * </ul>
     * 以上均可扩充
     */
    public Integer isExcludeUrl(String origin) {
        //通配符匹配
        AntPathMatcher pathMatcher = new AntPathMatcher();
        //匿名路径
        for (String excludeV : excludeUrlProperties.getExcludeUrl()) {
            if (pathMatcher.match(excludeV, origin)) {
                log.info("路径{}为匿名路径", origin);
                return 1;
            }
        }
        //静态资源排除
        for (String staticV : excludeUrlProperties.getStaticUrl()) {
            if (pathMatcher.match(staticV, origin)) {
                log.info("路径{}为静态路径", origin);
                return 2;
            }
        }
        return 0;
    }
}
