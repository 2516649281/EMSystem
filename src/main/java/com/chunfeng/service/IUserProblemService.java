package com.chunfeng.service;

import com.chunfeng.dao.entity.UserProblem;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 用户-题目关系业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IUserProblemService {

    /**
     * 分类筛选关系信息
     *
     * @param userProblem 条件
     * @return JSON
     */
    JsonRequest<List<UserProblem>> lookUserProblem(UserProblem userProblem);

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    JsonRequest<List<UserProblem>> lookAllUserProblem();

    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<List<UserProblem>> lookUserProblemById(String[] ids);

    /**
     * 批量绑定关系信息
     *
     * @param userProblems 关系信息
     * @return JSON
     */
    JsonRequest<Integer> addUserProblem(List<UserProblem> userProblems);

    /**
     * 批量修改关系数据
     *
     * @param userProblems 关系信息
     * @return JSON
     */
    JsonRequest<Integer> updateUserProblem(List<UserProblem> userProblems);

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<Integer> deleteUserProblem(String[] ids);


    /**
     * 根据题目ID解绑关系
     *
     * @param ids 题目ID
     * @return JSON
     */
    JsonRequest<Integer> deleteUserProblemByExam(String[] ids);
}
