package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Exam;
import com.chunfeng.dao.entity.Problem;
import com.chunfeng.dao.entity.ProblemExam;
import com.chunfeng.dao.mapper.ExamMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IExamService;
import com.chunfeng.service.IProblemExamService;
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
 * 试卷业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class ExamServiceImpl implements IExamService {

    /**
     * 试卷数据层
     */
    @Autowired
    private ExamMapper examMapper;

    /**
     * 关系数据层
     */
    @Autowired
    private IProblemExamService problemExamService;

    /**
     * 文件工具类
     */
    @Autowired
    private FileMangerUtils<Exam> fileMangerUtils;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IExamService examService;

    /**
     * 导入题库业务
     */
    @Autowired
    private IProblemService problemService;

    /**
     * 分类筛选试卷信息
     *
     * @param exam 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "exam_select", key = "#exam")
    public JsonRequest<List<Exam>> lookExam(Exam exam) {
        List<Exam> exams = examMapper.selectAllExam(exam);
        //判断是否成功
        if (exams.isEmpty()) {
            log.warn("未找到任何试卷信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //提取ID值
        List<String> ids = exams.stream()
                .map(Exam::getId)//获取ID值
                .collect(Collectors.toList());//转换为list集合
        //查询结果
        List<Exam> examList = ids.stream()
                .map(id -> fileMangerUtils.fileLook(id + ".txt"))//遍历获取结果
                .collect(Collectors.toList());//收集查询结果
        log.info("已找到{}条试卷信息", exams.size());
        return JsonRequest.success(examList);
    }

    /**
     * 查询所有试卷信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "exam_select")
    public JsonRequest<List<Exam>> lookAllExam() {
        return examService.lookExam(new Exam());
    }

    /**
     * 根据ID值批量查询试卷信息
     *
     * @param ids 试卷ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "exam_select", key = "#ids")
    public JsonRequest<List<Exam>> lookExamById(String[] ids) {
        List<Exam> exams = examMapper.selectAllExamById(ids);
        if (exams.size() != ids.length) {
            log.warn("待查询的试卷ID与数据库中的数量不符!数据库:{},实际:{}", exams.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条试卷数据!", exams.size());
        return JsonRequest.success(exams);
    }

    /**
     * 根据ID值查询试卷信息
     *
     * @param examId 试卷ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "exam_select", key = "#examId")
    public JsonRequest<Exam> lookOneExam(String examId) {
        //构造条件
        Exam exam = new Exam();
        exam.setId(examId);
        //调用本地方法使其缓存生效
        JsonRequest<List<Exam>> lookExam = examService.lookExam(exam);
        //判断试卷是否存在
        if (!lookExam.getSuccess()) {
            log.warn("{}", lookExam.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //获取单个试卷
        Exam exam1 = lookExam.getData().get(0);
        //构造条件
        ProblemExam problemExam = new ProblemExam();
        problemExam.setExamId(examId);
        //查询绑定的题库
        JsonRequest<List<ProblemExam>> lookProblemExam = problemExamService
                .lookProblemExam(problemExam);
        //获得题库ID
        String[] problemIds = lookProblemExam
                .getData()
                .stream()
                .map(ProblemExam::getProblemId)//获取题库ID
                .toArray(String[]::new);
        //最终获得题库列表
        JsonRequest<List<Problem>> request = problemService
                .lookProblemById(problemIds);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //存入试卷中
        exam1.setProblemList(request.getData());
        return JsonRequest.success(exam1);
    }

    /**
     * 新增一条试卷信息
     *
     * @param exam 试卷信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "exam_select", allEntries = true)
    public JsonRequest<Integer> addOneExam(Exam exam) {
        exam.setId(UIDCreateUtil.getUUId());
        //将数据写入文件
        String path = fileMangerUtils.fileWriter(exam.getId() + ".txt", exam);
        //日志信息
        exam.setCreateUser(SqlDateUtils.currentUserId);
        exam.setCreateTime(SqlDateUtils.date);
        //保存文件路径
        exam.setFilePath(path);
        Integer column = examMapper.insertExam(exam);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加试卷名为{}的数据失败!", exam.getName());
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加名为{}的试卷信息!", exam.getName());
        return JsonRequest.success(column);
    }

    /**
     * 修改一条试卷信息
     *
     * @param exam 试卷信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"exam_select"}, allEntries = true)
    public JsonRequest<Integer> updateOneExam(Exam exam) {
        JsonRequest<List<Exam>> request = examService.lookExamById(new String[]{exam.getId()});
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //日志信息
        exam.setUpdateUser(SqlDateUtils.currentUserId);
        exam.setUpdateTime(SqlDateUtils.date);
        //首先删除试卷绑定的所有题库
        JsonRequest<Integer> request1 = problemExamService.deleteProblemExamByExam(new String[]{exam.getId()});
        //判断是否成功
        if (!request1.getSuccess()) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        // 构造条件
        List<ProblemExam> problemExamList = exam.getProblemList()
                .stream()
                .map(v -> {
                    ProblemExam problemExam = new ProblemExam();
                    problemExam.setExamId(exam.getId());//试卷ID
                    problemExam.setProblemId(v.getId());//题库ID
                    return problemExam;
                })
                .collect(Collectors.toList());//转换为集合
        //再绑定关系
        JsonRequest<Integer> request2 = problemExamService.addProblemExam(problemExamList);
        if (!request2.getSuccess()) {
            log.error("添加关系失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //最后修改本体
        Integer column = examMapper.updateExamById(exam);
        if (column < 1) {
            log.error("修改ID为{}的试卷信息失败!", exam.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //修改文件内容
        fileMangerUtils.fileUpdate(exam.getId() + ".txt", exam);
        log.info("ID为{}的试卷信息修改成功!", exam.getId());
        return JsonRequest.success(column);
    }

    /**
     * 批量删除试卷信息
     *
     * @param ids 试卷ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"exam_select"}, allEntries = true)
    public JsonRequest<Integer> deleteExam(String[] ids) {
        JsonRequest<List<Exam>> request = examService.lookExamById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除关系数据
        problemExamService.deleteProblemExamByExam(ids);
        //删除数据库内容
        examMapper.deleteExamById(ids);
        //删除本体
        Integer column = examMapper.deleteExamById(ids);
        if (column < 1) {
            log.error("删除试卷失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除文件内容
        Arrays.stream(ids)
                .forEach(id -> fileMangerUtils.fileDelete(id + ".txt"));
        log.info("已删除{}条试卷信息!", ids.length);
        return JsonRequest.success(column);
    }
}
