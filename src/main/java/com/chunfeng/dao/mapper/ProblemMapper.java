package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Problem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
public interface ProblemMapper {
    /**
     * 条件查询所有题目
     *
     * @param problem 条件
     * @return 符合条件的所有题目信息
     */
    List<Problem> selectAllProblem(Problem problem);


    /**
     * 根据ID查询题目
     *
     * @param ids 题目ID
     * @return 题目列表
     */
    List<Problem> selectAllProblemById(String[] ids);

    /**
     * 插入一条题目数据
     *
     * @param problem 题目信息
     * @return 影响行数
     */
    Integer insertProblem(Problem problem);

    /**
     * 根据题目ID修改一条题目数据
     *
     * @param problem 题目信息
     * @return 影响行数
     */
    Integer updateProblemById(Problem problem);

    /**
     * 根据题目ID批量删除题目信息
     *
     * @param ids 选择的题目ID
     * @return 影响行数
     */
    Integer deleteProblemById(@Param("id") String[] ids);
}
