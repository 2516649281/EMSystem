package com.chunfeng.service;

import com.chunfeng.dao.entity.UserExam;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 用户-试卷关系业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IUserExamService {

    /**
     * 分类筛选关系信息
     *
     * @param userExam 条件
     * @return JSON
     */
    JsonRequest<List<UserExam>> lookUserExam(UserExam userExam);

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    JsonRequest<List<UserExam>> lookAllUserExam();

    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<List<UserExam>> lookUserExamById(String[] ids);

    /**
     * 批量绑定关系信息
     *
     * @param userExams 关系信息
     * @return JSON
     */
    JsonRequest<Integer> addUserExam(List<UserExam> userExams);

    /**
     * 批量修改关系数据
     *
     * @param userExams 关系信息
     * @return JSON
     */
    JsonRequest<Integer> updateUserExam(List<UserExam> userExams);

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    JsonRequest<Integer> deleteUserExam(String[] ids);


    /**
     * 根据试卷ID解绑关系
     *
     * @param ids 试卷ID
     * @return JSON
     */
    JsonRequest<Integer> deleteUserExamByExam(String[] ids);
}
