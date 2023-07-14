package com.chunfeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库自动填充时间工具类
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
public class SqlDateUtils {
    /**
     * 数据库时间
     */
    public static String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    /**
     * 当前用户ID
     */
    public static Integer currentUserId;
}
