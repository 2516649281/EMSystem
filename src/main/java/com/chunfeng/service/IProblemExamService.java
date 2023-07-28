package com.chunfeng.service;

import com.chunfeng.dao.entity.ProblemExam;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 题库-试卷关系业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IProblemExamService {

    /**
     * 分类筛选关系信息
     *
     * @param problemExam 条件
     * @return JSON
     */
    JsonRequest<List<ProblemExam>> lookProblemExam(ProblemExam problemExam);

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    JsonRequest<List<ProblemExam>> lookAllProblemExam();

    /**
     * 批量绑定关系信息
     *
     * @param problemExams 关系信息
     * @return JSON
     */
    JsonRequest<Integer> addProblemExam(List<ProblemExam> problemExams);

    /**
     * 批量修改关系数据
     *
     * @param problemExams 关系信息
     * @return JSON
     */
    JsonRequest<Integer> updateProblemExam(List<ProblemExam> problemExams);

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<Integer> deleteProblemExam(String[] ids);
}
