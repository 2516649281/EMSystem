package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Gradle;
import com.chunfeng.dao.mapper.GradleMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IGradleService;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 年级业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class GradleServiceImpl implements IGradleService {

    /**
     * 年级数据层
     */
    @Autowired
    private GradleMapper gradleMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IGradleService gradleService;

    /**
     * 分类筛选年级信息
     *
     * @param gradle 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "gradle_select", key = "#gradle")
    public JsonRequest<List<Gradle>> lookGradle(Gradle gradle) {
        List<Gradle> grades = gradleMapper.selectAllGradle(gradle);
        //判断是否成功
        if (grades.isEmpty()) {
            log.warn("未找到任何年级信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条年级信息", grades.size());
        return JsonRequest.success(grades);
    }

    /**
     * 查询所有年级信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "gradle_select")
    public JsonRequest<List<Gradle>> lookAllGradle() {
        return gradleService.lookGradle(new Gradle());
    }

    /**
     * 根据ID值批量查询年级信息
     *
     * @param ids 年级ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "gradle_select", key = "#ids")
    public JsonRequest<List<Gradle>> lookGradleById(String[] ids) {
        List<Gradle> grades = gradleMapper.selectAllGradleById(ids);
        if (grades.size() != ids.length) {
            log.warn("待查询的年级ID与数据库中的数量不符!数据库:{},实际:{}", grades.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条年级数据!", grades.size());
        return JsonRequest.success(grades);
    }


    /**
     * 新增一条年级信息
     *
     * @param gradle 年级信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "gradle_select", allEntries = true)
    public JsonRequest<Integer> addOneGradle(Gradle gradle) {
        //日志信息
        gradle.setId(UIDCreateUtil.getUUId());
        gradle.setCreateUser(SqlDateUtils.currentUserId);
        gradle.setCreateTime(SqlDateUtils.date);
        Integer column = gradleMapper.insertGradle(gradle);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加年级名为{}的数据失败!", gradle.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加名为{}的年级信息!", gradle.getName());
        return JsonRequest.success(column);
    }

    /**
     * 修改一条年级信息
     *
     * @param gradle 年级信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"gradle_select"}, allEntries = true)
    public JsonRequest<Integer> updateOneGradle(Gradle gradle) {
        JsonRequest<List<Gradle>> request = gradleService.lookGradleById(new String[]{gradle.getId()});
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        gradle.setUpdateUser(SqlDateUtils.currentUserId);
        gradle.setUpdateTime(SqlDateUtils.date);
        Integer column = gradleMapper.updateGradleById(gradle);
        if (column < 1) {
            log.error("修改ID为{}的年级信息失败!", gradle.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的年级信息修改成功!", gradle.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除年级信息
     *
     * @param ids 年级ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"gradle_select"}, allEntries = true)
    public JsonRequest<Integer> deleteGradle(String[] ids) {
        JsonRequest<List<Gradle>> request = gradleService.lookGradleById(ids);
        if (request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除
        Integer column = gradleMapper.deleteGradleById(ids);
        if (column < 1) {
            log.error("删除年级失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条年级信息!", ids.length);
        return JsonRequest.success(column);
    }
}
