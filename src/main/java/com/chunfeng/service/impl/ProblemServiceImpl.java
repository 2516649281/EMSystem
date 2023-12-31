package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Problem;
import com.chunfeng.dao.mapper.ProblemExamMapper;
import com.chunfeng.dao.mapper.ProblemMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.service.IProblemService;
import com.chunfeng.utils.FileMangerUtils;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题库业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class ProblemServiceImpl implements IProblemService {

    /**
     * 题库数据层
     */
    @Autowired
    private ProblemMapper problemMapper;

    /**
     * 关系数据层
     */
    @Autowired
    private ProblemExamMapper problemExamMapper;

    /**
     * 文件工具类
     */
    @Autowired
    private FileMangerUtils<Problem> fileMangerUtils;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IProblemService problemService;

    /**
     * 分类筛选题库信息
     *
     * @param problem 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "problem_select", key = "#problem")
    public JsonRequest<List<Problem>> lookProblem(Problem problem) {
        List<Problem> problems = problemMapper.selectAllProblem(problem);
        //判断是否成功
        if (problems.isEmpty()) {
            log.warn("未找到任何题目信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //提取ID值
        List<String> ids = problems.stream()
                .map(Problem::getId)//获取ID值
                .collect(Collectors.toList());//转换为list集合
        //查询结果
        List<Problem> problemList = ids.stream()
                .map(id -> fileMangerUtils.fileLook("problem_" + id + ".txt"))//遍历获取结果
                .collect(Collectors.toList());//收集查询结果
        log.info("已找到{}条题目信息", problems.size());
        return JsonRequest.success(problemList);
    }

    /**
     * 查询所有题库信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "problem_select")
    public JsonRequest<List<Problem>> lookAllProblem() {
        return problemService.lookProblem(new Problem());
    }


    /**
     * 根据ID值批量查询题库信息
     *
     * @param ids 题库ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "problem_select", key = "#ids")
    public JsonRequest<List<Problem>> lookProblemById(String[] ids) {
        //查询数据库
        List<Problem> problems = problemMapper.selectAllProblemById(ids);
        if (problems.size() != ids.length) {
            log.warn("待查询的题库ID与数据库中的数量不符!数据库:{},实际:{}", problems.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //提取ID值
        List<String> ids1 = problems.stream()
                .map(Problem::getId)//获取ID值
                .collect(Collectors.toList());//转换为list集合
        //查询结果
        List<Problem> problemList = ids1.stream()
                .map(id -> fileMangerUtils.fileLook("problem_" + id + ".txt"))//遍历获取结果
                .collect(Collectors.toList());//收集查询结果
        log.info("已查询出{}条题库数据!", problems.size());
        return JsonRequest.success(problemList);
    }

    /**
     * 新增一条题库信息
     *
     * @param problem 题库信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "problem_select", allEntries = true)
    public JsonRequest<Integer> addOneProblem(Problem problem) {
        problem.setId(UIDCreateUtil.getUUId());
        //将数据写入文件
        String path = fileMangerUtils.fileWriter("problem_" + problem.getId() + ".txt", problem);
        //日志信息
        problem.setCreateUser(SqlDateUtils.currentUserId);
        problem.setCreateTime(SqlDateUtils.date);
        //保存文件路径
        problem.setFilePath(path);
        Integer column = problemMapper.insertProblem(problem);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加题库数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加一条题库信息!");
        return JsonRequest.success(column);
    }

    /**
     * 修改一条题库信息
     *
     * @param problem 题库信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"problem_select", "userProblem_select"}, allEntries = true)
    public JsonRequest<Integer> updateOneProblem(Problem problem) {
        JsonRequest<List<Problem>> request = problemService.lookProblemById(new String[]{problem.getId()});
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        problem.setUpdateUser(SqlDateUtils.currentUserId);
        problem.setUpdateTime(SqlDateUtils.date);
        Integer column = problemMapper.updateProblemById(problem);
        if (column < 1) {
            log.error("修改ID为{}的题库信息失败!", problem.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //修改文件内容
        fileMangerUtils.fileUpdate("problem_" + problem.getId() + ".txt", problem);
        log.info("ID为{}的题库信息修改成功!", problem.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除题库信息
     *
     * @param ids 题库ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"problem_select", "userProblem_select"}, allEntries = true)
    public JsonRequest<Integer> deleteProblem(String[] ids) {
        JsonRequest<List<Problem>> request = problemService.lookProblemById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除关系
        problemExamMapper.deleteProblemExamByPro(ids);
        //删除数据库内容
        Integer column = problemMapper.deleteProblemById(ids);
        if (column < 1) {
            log.error("删除题库失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除文件内容
        Arrays.stream(ids)
                .forEach(id -> fileMangerUtils.fileDelete("problem_" + id + ".txt"));
        log.info("已删除{}条题库信息!", ids.length);
        return JsonRequest.success(column);
    }
}
