package com.chunfeng.service;

import com.chunfeng.dao.entity.Problem;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 题库业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IProblemService {

    /**
     * 分类筛选题库信息
     *
     * @param problem 条件
     * @return JSON
     */
    JsonRequest<List<Problem>> lookProblem(Problem problem);

    /**
     * 查询所有题库信息
     *
     * @return JSON
     */
    JsonRequest<List<Problem>> lookAllProblem();


    /**
     * 根据ID值批量查询题库信息
     *
     * @param ids 题库ID
     * @return JSON
     */
    JsonRequest<List<Problem>> lookProblemById(String[] ids);

    /**
     * 新增一条题库信息
     *
     * @param problem 题库信息
     * @return JSON
     */
    JsonRequest<Integer> addOneProblem(Problem problem);

    /**
     * 修改一条题库信息
     *
     * @param problem 题库信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneProblem(Problem problem);

    /**
     * 批量删除题库信息
     *
     * @param ids 题库ID
     * @return JSON
     */
    JsonRequest<Integer> deleteProblem(String[] ids);
}
