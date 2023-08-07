package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 科目数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/15
 */
public interface SubjectMapper {

    /**
     * 条件查询科目信息
     *
     * @param subject 条件
     * @return 科目列表
     */
    List<Subject> selectAllSubject(Subject subject);

    /**
     * 根据ID查询科目
     *
     * @param ids 科目ID
     * @return 科目列表
     */
    List<Subject> selectAllSubjectById(@Param("ids") String[] ids);

    /**
     * 插入一条科目信息
     *
     * @param subject 科目信息
     * @return 影响行数
     */
    Integer insertSubject(Subject subject);

    /**
     * 修改一条科目信息
     *
     * @param subject 科目信息
     * @return 影响行数
     */
    Integer updateSubjectById(Subject subject);

    /**
     * 根据科目ID批量删除科目信息
     *
     * @param ids 选择的科目ID
     * @return 影响行数
     */
    Integer deleteSubjectById(@Param("ids") String[] ids);
}
