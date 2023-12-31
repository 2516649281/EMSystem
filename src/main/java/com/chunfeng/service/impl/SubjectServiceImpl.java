package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Subject;
import com.chunfeng.dao.mapper.SubjectMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.service.ISubjectService;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 科目业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class SubjectServiceImpl implements ISubjectService {

    /**
     * 科目数据层
     */
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private ISubjectService subjectService;

    /**
     * 分类筛选科目信息
     *
     * @param subject 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "subject_select", key = "#subject")
    public JsonRequest<List<Subject>> lookSubject(Subject subject) {
        List<Subject> subjects = subjectMapper.selectAllSubject(subject);
        //判断是否成功
        if (subjects.isEmpty()) {
            log.warn("未找到任何科目信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条科目信息", subjects.size());
        return JsonRequest.success(subjects);
    }

    /**
     * 查询所有科目信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "subject_select")
    public JsonRequest<List<Subject>> lookAllSubject() {
        return subjectService.lookSubject(new Subject());
    }

    /**
     * 根据ID值查询科目信息
     *
     * @param ids 科目ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "subject_select", key = "#ids")
    public JsonRequest<List<Subject>> lookSubjectById(String[] ids) {
        List<Subject> subjects = subjectMapper.selectAllSubjectById(ids);
        if (subjects.size() != ids.length) {
            log.warn("待查询的科目ID与数据库中的数量不符!数据库:{},实际:{}", subjects.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条科目数据!", subjects.size());
        return JsonRequest.success(subjects);
    }

    /**
     * 新增一条科目信息
     *
     * @param subject 科目信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "subject_select", allEntries = true)
    public JsonRequest<Integer> addOneSubject(Subject subject) {
        //日志信息
        subject.setId(UIDCreateUtil.getUUId());
        subject.setCreateUser(SqlDateUtils.currentUserId);
        subject.setCreateTime(SqlDateUtils.date);
        Integer column = subjectMapper.insertSubject(subject);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加科目名为{}的数据失败!", subject.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加名为{}的科目信息!", subject.getName());
        return JsonRequest.success(column);
    }

    /**
     * 修改一条科目信息
     *
     * @param subject 科目信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"subject_select"}, allEntries = true)
    public JsonRequest<Integer> updateOneSubject(Subject subject) {
        JsonRequest<List<Subject>> request = subjectService.lookSubjectById(new String[]{subject.getId()});
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        subject.setUpdateUser(SqlDateUtils.currentUserId);
        subject.setUpdateTime(SqlDateUtils.date);
        Integer column = subjectMapper.updateSubjectById(subject);
        if (column < 1) {
            log.error("修改ID为{}的科目信息失败!", subject.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的科目信息修改成功!", subject.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除科目信息
     *
     * @param ids 科目ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"subject_select"}, allEntries = true)
    public JsonRequest<Integer> deleteSubject(String[] ids) {
        JsonRequest<List<Subject>> request = subjectService.lookSubjectById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = subjectMapper.deleteSubjectById(ids);
        if (column < 1) {
            log.error("删除科目失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条科目信息!", ids.length);
        return JsonRequest.success(column);
    }
}
