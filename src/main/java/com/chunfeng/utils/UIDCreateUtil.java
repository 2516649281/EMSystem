package com.chunfeng.utils;

import java.util.UUID;

/**
 * 用户ID生成器
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
public class UIDCreateUtil {

    /**
     * 生成ID
     *
     * @return 唯一ID
     */
    public static String getUUId() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return 唯一ID
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUId();
        }
        return ss;
    }
}
