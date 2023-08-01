package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.ProblemExam;
import org.apache.ibatis.annotations.Param;

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
     * 按条件批量查询符合条件的个数
     *
     * @param problemExams 条件
     * @return 个数
     */
    Integer selectAllProblemExamCount(@Param("pes") List<ProblemExam> problemExams);

    /**
     * 批量插入关系信息
     *
     * @param problemExams 关系信息
     * @return 影响行数
     */
    Integer insertProblemExam(@Param("pes") List<ProblemExam> problemExams);

    /**
     * 根据关系ID批量修改关系数据
     *
     * @param problemExams 关系数据
     * @return 影响行数
     */
    Integer updateProblemExamById(@Param("pes") List<ProblemExam> problemExams);

    /**
     * 根据关系ID批量解绑关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deleteProblemExamById(@Param("ids") String[] ids);

    /**
     * 根据题库ID批量解绑关系信息
     *
     * @param ids 选择的题库ID
     * @return 影响行数
     */
    Integer deleteProblemExamByPro(@Param("ids") String[] ids);

    /**
     * 根据试卷ID批量解绑关系信息
     *
     * @param ids 选择的试卷ID
     * @return 影响行数
     */
    Integer deleteProblemExamByExam(@Param("ids") String[] ids);
}
