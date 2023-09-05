package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.UserExam;
import com.chunfeng.dao.mapper.UserExamMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IUserExamService;
import com.chunfeng.utils.SqlDateUtils;
import com.chunfeng.utils.UIDCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户-试卷关系业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class UserExamServiceImpl implements IUserExamService {

    /**
     * 关系数据层
     */
    @Autowired
    private UserExamMapper userExamMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IUserExamService userExamService;

    /**
     * 分类筛选关系信息
     *
     * @param userExam 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "userExam_select", key = "#userExam")
    public JsonRequest<List<UserExam>> lookUserExam(UserExam userExam) {
        List<UserExam> userExams = userExamMapper.selectAllUserExam(userExam);
        //判断是否成功
        if (userExams.isEmpty()) {
            log.warn("未找到任何关系信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条关系信息", userExams.size());
        return JsonRequest.success(userExams);
    }

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "userExam_select")
    public JsonRequest<List<UserExam>> lookAllUserExam() {
        return userExamService.lookUserExam(new UserExam());
    }

    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "userExam_select", key = "#ids")
    public JsonRequest<List<UserExam>> lookUserExamById(String[] ids) {
        List<UserExam> userExams = userExamMapper.selectAllUserExamById(ids);
        if (userExams.size() != ids.length) {
            log.warn("待查询的关系ID与数据库中的数量不符!数据库:{},实际:{}", userExams.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条关系数据!", userExams.size());
        return JsonRequest.success(userExams);
    }

    /**
     * 批量绑定关系信息
     *
     * @param userExams 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userExam_select"}, allEntries = true)
    public JsonRequest<Integer> addUserExam(List<UserExam> userExams) {
        //判断关系是否已经存在
        Integer column = userExamMapper.selectAllUserExamCount(userExams);
        if (column > 0) {
            log.error("添加关系数据失败!原因:该关系已在数据库中找到");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //添加日志信息
        List<UserExam> userExamList = userExams.stream().peek(v -> {
            //日志信息
            v.setId(UIDCreateUtil.getUUId());
            v.setCreateUser(SqlDateUtils.currentUserId);
            v.setCreateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //添加
        column = userExamMapper.insertUserExam(userExamList);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加{}条关系信息!", userExams.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量修改关系数据
     *
     * @param userExams 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userExam_select"}, allEntries = true)
    public JsonRequest<Integer> updateUserExam(List<UserExam> userExams) {
        //获取ID值
        String[] ids = userExams.stream().map(UserExam::getId).toArray(String[]::new);
        JsonRequest<List<UserExam>> request = userExamService.lookUserExamById(ids);
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //加入日志
        List<UserExam> userExamList1 = userExams.stream().peek(v -> {
            //日志信息
            v.setUpdateUser(SqlDateUtils.currentUserId);
            v.setUpdateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //修改
        Integer column = userExamMapper.updateUserExamById(userExamList1);
        if (column < 1) {
            log.error("修改关系信息失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("已修改{}条关系信息!", userExams.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userExam_select"}, allEntries = true)
    public JsonRequest<Integer> deleteUserExam(String[] ids) {
        JsonRequest<List<UserExam>> request = userExamService.lookUserExamById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = userExamMapper.deleteUserExamById(ids);
        if (column < 1) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条关系信息!", ids.length);
        return JsonRequest.success(column);
    }

    /**
     * 根据试卷ID解绑关系
     *
     * @param ids 试卷ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userExam_select"}, allEntries = true)
    public JsonRequest<Integer> deleteUserExamByExam(String[] ids) {
        Integer column = userExamMapper.deleteUserExamByExam(ids);
        //这里不做处理，因为存在没有关系的数据
        if (column < 1) {
            log.warn("没有任何关系信息!");
        } else {
            log.info("已删除{}条关系信息!", ids.length);
        }
        return JsonRequest.success(column);
    }
}
