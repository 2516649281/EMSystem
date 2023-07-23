package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/6/30
 */
public interface ExamMapper {
    /**
     * 条件查询所有试卷
     *
     * @param exam 条件
     * @return 符合条件的所有试卷信息
     */
    List<Exam> selectAllExam(Exam exam);

    /**
     * 根据ID查询试卷
     *
     * @param ids 试卷ID
     * @return 试卷列表
     */
    List<Exam> selectAllExamById(String[] ids);

    /**
     * 插入一条试卷数据
     *
     * @param exam 试卷信息
     * @return 影响行数
     */
    Integer insertExam(Exam exam);

    /**
     * 根据试卷ID修改一条试卷数据
     *
     * @param exam 试卷信息
     * @return 影响行数
     */
    Integer updateExamById(Exam exam);

    /**
     * 根据试卷ID批量删除试卷信息
     *
     * @param ids 选择的试卷ID
     * @return 影响行数
     */
    Integer deleteExamById(@Param("id") String[] ids);
}
