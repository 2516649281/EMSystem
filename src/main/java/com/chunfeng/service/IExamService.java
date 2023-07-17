package com.chunfeng.service;

import com.chunfeng.dao.entity.Exam;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 试卷业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IExamService {

    /**
     * 分类筛选试卷信息
     *
     * @param exam 条件
     * @return JSON
     */
    JsonRequest<List<Exam>> lookExam(Exam exam);

    /**
     * 查询所有试卷信息
     *
     * @return JSON
     */
    JsonRequest<List<Exam>> lookAllExam();

    /**
     * 新增一条试卷信息
     *
     * @param exam 试卷信息
     * @return JSON
     */
    JsonRequest<Integer> addOneExam(Exam exam);

    /**
     * 修改一条试卷信息
     *
     * @param exam 试卷信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneExam(Exam exam);

    /**
     * 批量删除试卷信息
     *
     * @param ids 试卷ID
     * @return JSON
     */
    JsonRequest<Integer> deleteExam(String[] ids);
}
