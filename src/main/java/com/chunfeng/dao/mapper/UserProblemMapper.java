package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.UserProblem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-题目数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/9/5
 */
public interface UserProblemMapper {
    /**
     * 条件查询关系信息
     *
     * @param userProblem 条件
     * @return 关系列表
     */
    List<UserProblem> selectAllUserProblem(UserProblem userProblem);

    /**
     * 根据ID查询关系
     *
     * @param ids 关系ID
     * @return 关系列表
     */
    List<UserProblem> selectAllUserProblemById(@Param("ids") String[] ids);

    /**
     * 按条件批量查询符合条件的个数
     *
     * @param userProblems 条件
     * @return 个数
     */
    Integer selectAllUserProblemCount(@Param("ups") List<UserProblem> userProblems);

    /**
     * 批量插入关系信息
     *
     * @param userProblems 关系信息
     * @return 影响行数
     */
    Integer insertUserProblem(@Param("ups") List<UserProblem> userProblems);

    /**
     * 根据关系ID批量修改关系数据
     *
     * @param userProblems 关系数据
     * @return 影响行数
     */
    Integer updateUserProblemById(@Param("ups") List<UserProblem> userProblems);

    /**
     * 根据关系ID批量解绑关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deleteUserProblemById(@Param("ids") String[] ids);

    /**
     * 根据题库ID批量解绑关系信息
     *
     * @param ids 选择的题库ID
     * @return 影响行数
     */
    Integer deleteUserProblemByPro(@Param("ids") String[] ids);

    /**
     * 根据试卷ID批量解绑关系信息
     *
     * @param ids 选择的试卷ID
     * @return 影响行数
     */
    Integer deleteUserProblemByExam(@Param("ids") String[] ids);
}
