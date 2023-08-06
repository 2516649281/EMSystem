package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Gradle;

import java.util.List;

/**
 * 年级数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/6
 */
public interface GradleMapper {
    /**
     * 条件查询年级信息
     *
     * @param gradle 条件
     * @return 年级列表
     */
    List<Gradle> selectAllGradle(Gradle gradle);

    /**
     * 根据ID查询年级
     *
     * @param ids 年级ID
     * @return 年级列表
     */
    List<Gradle> selectAllGradleById(String[] ids);

    /**
     * 插入一条年级信息
     *
     * @param gradle 年级信息
     * @return 影响行数
     */
    Integer insertGradle(Gradle gradle);

    /**
     * 修改一条年级信息
     *
     * @param gradle 年级信息
     * @return 影响行数
     */
    Integer updateGradleById(Gradle gradle);

    /**
     * 根据年级ID批量删除年级信息
     *
     * @param ids 选择的年级ID
     * @return 影响行数
     */
    Integer deleteGradleById(String[] ids);
}
