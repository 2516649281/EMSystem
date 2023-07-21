package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.Exam;
import com.chunfeng.dao.mapper.ExamMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IExamService;
import com.chunfeng.utils.FileMangerUtils;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class ExamServiceImpl implements IExamService {

    /**
     * 试卷数据层
     */
    @Autowired
    private ExamMapper examMapper;

    /**
     * 文件工具类
     */
    @Autowired
    private FileMangerUtils<Exam> fileMangerUtils;

    /**
     * 分类筛选试卷信息
     *
     * @param exam 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "exam_select", key = "#exam.hashCode()")
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
    public JsonRequest<List<Exam>> lookAllExam() {
        return lookExam(new Exam());
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
        List<Exam> exams = examMapper.selectAllExamById(new String[]{exam.getId()});
        //判断是否成功
        if (exams.isEmpty()) {
            log.warn("数据库中不存在ID为{}的试卷信息!", exam.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //修改文件内容
        fileMangerUtils.fileUpdate(exam.getId(), exam);
        //日志信息
        exam.setUpdateUser(SqlDateUtils.currentUserId);
        exam.setUpdateTime(SqlDateUtils.date);
        Integer column = examMapper.updateExamById(exam);
        if (column < 1) {
            log.error("修改ID为{}的试卷信息失败!", exam.getId());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
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
        List<Exam> exams = examMapper.selectAllExamById(ids);
        if (exams.size() != ids.length) {
            log.error("删除试卷信息时,数据库的数据与实际待删除数据不一致!数据库:{},实际:{}", exams.size(), ids.length);
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除文件内容
        Arrays.stream(ids)
                .forEach(id -> fileMangerUtils.fileDelete(id + ".txt"));
        //删除数据库内容
        Integer column = examMapper.deleteExamById(ids);
        if (column < 1) {
            log.error("删除试卷失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条试卷信息!", ids.length);
        return JsonRequest.success(column);
    }
}
