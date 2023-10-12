package com.chunfeng.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chunfeng.properties.FileConfigProperties;
import com.chunfeng.result.exception.ServiceException;
import com.chunfeng.result.exenum.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 全局文件操作类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/20
 */
@Slf4j
public class FileMangerUtils<T> {

    /**
     * 文件配置
     */
    @Autowired
    private FileConfigProperties fileConfigProperties;

    /**
     * 写入文件操作
     *
     * @param fileName 文件名
     * @param obj      待序列化的对象
     * @return 文件路径
     */
    public String fileWriter(String fileName, T obj) {
        ObjectOutputStream ois;
        try {
            //初始化序列化对象
            ois = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileConfigProperties.getUrl() + fileName)));
            //写入文件
            ois.writeObject(obj);
        } catch (Exception e) {
            log.error("写入文件{}失败!", fileName);
            throw new ServiceException(RequestException.FILE_ERROR);
        }
        //释放资源
        close(null, ois);
        log.info("已创建文件{}", fileName);
        return fileConfigProperties.getUrl() + fileName;
    }

    /**
     * 文件修改操作
     *
     * @param fileName 文件名
     * @param obj      待序列化的对象
     */
    public void fileUpdate(String fileName, T obj) {
        File file = new File(fileConfigProperties.getUrl() + fileName);
        //如果文件不存在
        if (!file.exists()) {
            log.warn("文件{}不存在!", fileName);
            throw new ServiceException(RequestException.FILE_ERROR);
        }
        String path = fileWriter(fileName, obj);
        log.info("文件{}修改完成!", path);
    }

    /**
     * 文件查看操作
     *
     * @param fileName 待查看的文件名
     * @return T 返回反序后的对象
     */
    public T fileLook(String fileName) {
        //读文件
        ObjectInputStream ois;
        T obj;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileConfigProperties.getUrl() + fileName)));
            //转换为指定对象
            Object object = ois.readObject();
            String json = JSON.toJSONString(object);
            obj = JSON.parseObject(json, new TypeReference<T>() {
            });
        } catch (Exception exception) {
            log.error("文件{}读取失败!", fileName);
            throw new ServiceException(RequestException.FILE_ERROR);
        }
        log.info("文件{}已成功读取!", fileName);
        //释放资源
        close(ois, null);
        return obj;
    }

    /**
     * 删除文件
     *
     * @param fileName 待删除的文件名
     */
    public void fileDelete(String fileName) {
        File file = new File(fileConfigProperties.getUrl() + fileName);
        if (file.exists()) {
            log.info("文件{}已被删除!", fileName);
            file.delete();
            return;
        }
        log.warn("文件{}不存在!", fileName);
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @return 文件响应流
     */
    public ResponseEntity<Resource> avatarDownload(String fileName) {
        ResourceLoader loader = new DefaultResourceLoader();
        if (fileName.equals("0")) {
            log.warn("该用户未设置头像,输出默认头像");
            return ResponseEntity.ok(loader.getResource("file:" + fileConfigProperties.getUrl() + fileConfigProperties.getDefaultFile()));
        }
        File file = new File(fileConfigProperties.getUrl() + fileName);
        //文件不存在
        if (!file.exists()) {
            log.warn("找不到图片{}", fileName);
            return ResponseEntity.notFound().build();
        }
        log.info("已找到{}", fileName);
        //获取当前文件
        return ResponseEntity.ok(loader.getResource("file:" + fileConfigProperties.getUrl() + fileName));
    }

    /**
     * 文件上传
     *
     * @param file     待上传文件
     * @param fileName 文件名
     * @return 是否成功
     */
    public Boolean avatarUpload(MultipartFile file, String fileName) {
        File old = new File(fileConfigProperties.getUrl() + fileName);
        //判断源文件是否存在
        if (old.exists()) {
            //删除文件避免重复
            fileDelete(fileName);
        }
        if (file.isEmpty()) {
            log.error("待上传的文件为空!");
            throw new ServiceException(RequestException.FILE_ERROR);
        }
        long size = file.getSize();
        // 判断文件大小
        if (size > fileConfigProperties.getFileMaxSize() * 1024 * 1024) {
            log.warn("文件大小超出限制!实际大小:{}", size);
            throw new ServiceException(RequestException.FILE_BEYOND_MAX_SIZE);
        }
        //上传文件
        try {
            file.transferTo(new File(fileConfigProperties.getUrl() + fileName));
        } catch (IOException ioException) {
            log.error("头像上传失败!");
            throw new ServiceException(RequestException.FILE_ERROR);
        }
        log.info("文件{}上传成功!", fileName);
        return true;
    }

    /**
     * 释放资源
     *
     * @param ois 输入流对象
     * @param ops 输出流对象
     */
    public void close(InputStream ois, OutputStream ops) {
        try {
            //释放资源
            if (ois != null) {
                log.info("已释放InputStream");
                ois.close();
            }
            if (ops != null) {
                log.info("已释放OutputStream");
                ops.close();
            }
        } catch (IOException ioException) {
            log.error("释放资源失败!");
            throw new ServiceException(RequestException.FILE_ERROR);
        }
    }
}
