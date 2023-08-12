package com.chunfeng.service;

import com.chunfeng.dao.entity.Gradle;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 年级业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IGradleService {

    /**
     * 分类筛选年级信息
     *
     * @param gradle 条件
     * @return JSON
     */
    JsonRequest<List<Gradle>> lookGradle(Gradle gradle);

    /**
     * 查询所有年级信息
     *
     * @return JSON
     */
    JsonRequest<List<Gradle>> lookAllGradle();

    /**
     * 根据ID值批量查询年级信息
     *
     * @param ids 年级ID
     * @return JSON
     */
    JsonRequest<List<Gradle>> lookGradleById(String[] ids);

    /**
     * 新增一条年级信息
     *
     * @param gradle 年级信息
     * @return JSON
     */
    JsonRequest<Integer> addOneGradle(Gradle gradle);

    /**
     * 修改一条年级信息
     *
     * @param gradle 年级信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneGradle(Gradle gradle);

    /**
     * 批量删除年级信息
     *
     * @param ids 年级ID
     * @return JSON
     */
    JsonRequest<Integer> deleteGradle(String[] ids);
}
