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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 题库-试卷关系业务层实现
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
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IProblemExamService problemExamService;

    /**
     * 分类筛选关系信息
     *
     * @param problemExam 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "problemExam_select", key = "#problemExam")
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
    @Cacheable(value = "problemExam_select")
    public JsonRequest<List<ProblemExam>> lookAllProblemExam() {
        return problemExamService.lookProblemExam(new ProblemExam());
    }

    /**
     * 根据ID值批量查询角色信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "problemExam_select", key = "#ids")
    public JsonRequest<List<ProblemExam>> lookProblemExamById(String[] ids) {
        List<ProblemExam> problemExams = problemExamMapper.selectAllProblemExamById(ids);
        if (problemExams.size() != ids.length) {
            log.warn("待查询的角色ID与数据库中的数量不符!数据库:{},实际:{}", problemExams.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条角色数据!", problemExams.size());
        return JsonRequest.success(problemExams);
    }

    /**
     * 批量绑定关系信息
     *
     * @param problemExams 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "problemExam_select", allEntries = true)
    public JsonRequest<Integer> addProblemExam(List<ProblemExam> problemExams) {
        //判断关系是否已经存在
        Integer column = problemExamMapper.selectAllProblemExamCount(problemExams);
        if (column > 0) {
            log.error("添加关系数据失败!原因:该关系已在数据库中找到");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //添加日志信息
        List<ProblemExam> problemExamList = problemExams.stream().peek(v -> {
            //日志信息
            v.setId(UIDCreateUtil.getUUId());
            v.setCreateUser(SqlDateUtils.currentUserId);
            v.setCreateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //添加
        column = problemExamMapper.insertProblemExam(problemExamList);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加{}条关系信息!", problemExams.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量修改关系数据
     *
     * @param problemExams 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"problemExam_select"}, allEntries = true)
    public JsonRequest<Integer> updateProblemExam(List<ProblemExam> problemExams) {
        //获取ID值
        String[] ids = problemExams.stream().map(ProblemExam::getId).toArray(String[]::new);
        List<ProblemExam> problemExamList = problemExamMapper.selectAllProblemExamById(ids);
        //判断是否成功
        if (problemExamList.size() != problemExams.size()) {
            log.warn("数据库数据与待查找数据不一致!数据库:{},传入:{}", problemExamList.size(), problemExams.size());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //加入日志
        List<ProblemExam> problemExamList1 = problemExams.stream().peek(v -> {
            //日志信息
            v.setUpdateUser(SqlDateUtils.currentUserId);
            v.setUpdateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //修改
        Integer column = problemExamMapper.updateProblemExamById(problemExamList1);
        if (column < 1) {
            log.error("修改关系信息失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("已修改{}条关系信息!", problemExams.size());
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
