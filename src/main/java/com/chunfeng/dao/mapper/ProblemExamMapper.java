package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.ProblemExam;

import java.util.List;

/**
 * 题库-试卷数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/16
 */
public interface ProblemExamMapper {

    /**
     * 条件查询关系信息
     *
     * @param problemExam 条件
     * @return 关系列表
     */
    List<ProblemExam> selectAllProblemExam(ProblemExam problemExam);

    /**
     * 根据ID查询关系
     *
     * @param ids 关系ID
     * @return 关系列表
     */
    List<ProblemExam> selectAllProblemExamById(String[] ids);

    /**
     * 插入一条关系信息
     *
     * @param problemExam 关系信息
     * @return 影响行数
     */
    Integer insertProblemExam(ProblemExam problemExam);

    /**
     * 根据关系ID修改一条关系数据
     *
     * @param problemExam 关系数据
     * @return 影响行数
     */
    Integer updateProblemExamById(ProblemExam problemExam);

    /**
     * 根据关系ID批量删除关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deleteProblemExamById(String[] ids);
}
