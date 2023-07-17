package com.chunfeng.utils;

import com.chunfeng.properties.FileConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * IO流写入和读取工具类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Component
public class IOWRUtils {

    /**
     * 文件配置
     */
    @Autowired
    private FileConfigProperties fileConfigProperties;

    /**
     * 写入文件流
     *
     * @param name    文件名
     * @param context 文件内容
     * @return 是否成功
     */
    public boolean insertFile(String name, String context, String id) {
        File file = new File(fileConfigProperties.getUrl() + name);
        return false;
    }

}
