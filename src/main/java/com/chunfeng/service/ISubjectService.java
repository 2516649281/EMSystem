package com.chunfeng.service;

import com.chunfeng.dao.entity.Subject;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 科目业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface ISubjectService {

    /**
     * 分类筛选科目信息
     *
     * @param subject 条件
     * @return JSON
     */
    JsonRequest<List<Subject>> lookSubject(Subject subject);

    /**
     * 查询所有科目信息
     *
     * @return JSON
     */
    JsonRequest<List<Subject>> lookAllSubject();

    /**
     * 根据ID值查询科目信息
     *
     * @param ids 科目ID
     * @return JSON
     */
    JsonRequest<List<Subject>> lookSubjectById(String[] ids);

    /**
     * 新增一条科目信息
     *
     * @param subject 科目信息
     * @return JSON
     */
    JsonRequest<Integer> addOneSubject(Subject subject);

    /**
     * 修改一条科目信息
     *
     * @param subject 科目信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneSubject(Subject subject);

    /**
     * 批量删除科目信息
     *
     * @param ids 科目ID
     * @return JSON
     */
    JsonRequest<Integer> deleteSubject(String[] ids);
}
