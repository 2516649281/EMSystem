package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.ProblemExam;
import com.chunfeng.dao.mapper.ProblemExamMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IProblemExamService;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限-角色关系业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
@Transactional
public class ProblemExamServiceImpl implements IProblemExamService {

    /**
     * 关系数据层
     */
    @Autowired
    private ProblemExamMapper problemExamMapper;

    /**
     * 分类筛选关系信息
     *
     * @param problemExam 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "problemExam_select", key = "#problemExam.hashCode()")
    public JsonRequest<List<ProblemExam>> lookProblemExam(ProblemExam problemExam) {
        List<ProblemExam> problemExams = problemExamMapper.selectAllProblemExam(problemExam);
        //判断是否成功
        if (problemExams.isEmpty()) {
            log.warn("未找到任何关系信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条关系信息", problemExams.size());
        return JsonRequest.success(problemExams);
    }

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    @Override
    public JsonRequest<List<ProblemExam>> lookAllProblemExam() {
        return lookProblemExam(new ProblemExam());
    }

    /**
     * 绑定一条关系信息
     *
     * @param problemExam 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "problemExam_select", allEntries = true)
    public JsonRequest<Integer> addOneProblemExam(ProblemExam problemExam) {
        //日志信息
        problemExam.setId(UIDCreateUtil.getUUId());
        problemExam.setCreateUser(SqlDateUtils.currentUserId);
        problemExam.setCreateTime(SqlDateUtils.date);
        Integer column = problemExamMapper.insertProblemExam(problemExam);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加一条关系信息!");
        return JsonRequest.success(column);
    }

    /**
     * 修改一条关系信息
     *
     * @param problemExam 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"problemExam_select"}, allEntries = true)
    public JsonRequest<Integer> updateOneProblemExam(ProblemExam problemExam) {
        List<ProblemExam> problemExams = problemExamMapper.selectAllProblemExamById(new String[]{problemExam.getId()});
        //判断是否成功
        if (problemExams.isEmpty()) {
            log.warn("数据库中不存在ID为{}的关系信息!", problemExam.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        problemExam.setUpdateUser(SqlDateUtils.currentUserId);
        problemExam.setUpdateTime(SqlDateUtils.date);
        Integer column = problemExamMapper.updateProblemExamById(problemExam);
        if (column < 1) {
            log.error("修改ID为{}的关系信息失败!", problemExam.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("ID为{}的关系信息修改成功!", problemExam.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"problemExam_select"}, allEntries = true)
    public JsonRequest<Integer> deleteProblemExam(String[] ids) {
        List<ProblemExam> problemExams = problemExamMapper.selectAllProblemExamById(ids);
        if (problemExams.size() != ids.length) {
            log.error("删除关系信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", problemExams.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = problemExamMapper.deleteProblemExamById(ids);
        if (column < 1) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条关系信息!", ids.length);
        return JsonRequest.success(column);
    }
}
