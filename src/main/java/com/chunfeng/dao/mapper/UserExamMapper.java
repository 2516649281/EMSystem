package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.UserExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-试卷数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/9/5
 */
public interface UserExamMapper {
    /**
     * 条件查询关系信息
     *
     * @param userExam 条件
     * @return 关系列表
     */
    List<UserExam> selectAllUserExam(UserExam userExam);

    /**
     * 根据ID查询关系
     *
     * @param ids 关系ID
     * @return 关系列表
     */
    List<UserExam> selectAllUserExamById(@Param("ids") String[] ids);

    /**
     * 按条件批量查询符合条件的个数
     *
     * @param userExams 条件
     * @return 个数
     */
    Integer selectAllUserExamCount(@Param("ues") List<UserExam> userExams);

    /**
     * 批量插入关系信息
     *
     * @param userExams 关系信息
     * @return 影响行数
     */
    Integer insertUserExam(@Param("ues") List<UserExam> userExams);

    /**
     * 根据关系ID批量修改关系数据
     *
     * @param userExams 关系数据
     * @return 影响行数
     */
    Integer updateUserExamById(@Param("ues") List<UserExam> userExams);

    /**
     * 根据关系ID批量解绑关系信息
     *
     * @param ids 选择的关系ID
     * @return 影响行数
     */
    Integer deleteUserExamById(@Param("ids") String[] ids);

    /**
     * 根据题库ID批量解绑关系信息
     *
     * @param ids 选择的题库ID
     * @return 影响行数
     */
    Integer deleteUserExamByPro(@Param("ids") String[] ids);

    /**
     * 根据试卷ID批量解绑关系信息
     *
     * @param ids 选择的试卷ID
     * @return 影响行数
     */
    Integer deleteUserExamByExam(@Param("ids") String[] ids);
}
