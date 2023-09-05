package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.UserProblem;
import com.chunfeng.dao.mapper.UserProblemMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.RequestException;
import com.chunfeng.service.IUserProblemService;
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
 * 用户-题目关系业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class UserProblemServiceImpl implements IUserProblemService {

    /**
     * 关系数据层
     */
    @Autowired
    private UserProblemMapper userProblemMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IUserProblemService userProblemService;

    /**
     * 分类筛选关系信息
     *
     * @param userProblem 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "userProblem_select", key = "#userProblem")
    public JsonRequest<List<UserProblem>> lookUserProblem(UserProblem userProblem) {
        List<UserProblem> userProblems = userProblemMapper.selectAllUserProblem(userProblem);
        //判断是否成功
        if (userProblems.isEmpty()) {
            log.warn("未找到任何关系信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已找到{}条关系信息", userProblems.size());
        return JsonRequest.success(userProblems);
    }

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "userProblem_select")
    public JsonRequest<List<UserProblem>> lookAllUserProblem() {
        return userProblemService.lookUserProblem(new UserProblem());
    }

    /**
     * 根据ID值批量查询关系信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "userProblem_select", key = "#ids")
    public JsonRequest<List<UserProblem>> lookUserProblemById(String[] ids) {
        List<UserProblem> userProblems = userProblemMapper.selectAllUserProblemById(ids);
        if (userProblems.size() != ids.length) {
            log.warn("待查询的关系ID与数据库中的数量不符!数据库:{},实际:{}", userProblems.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        log.info("已查询出{}条关系数据!", userProblems.size());
        return JsonRequest.success(userProblems);
    }

    /**
     * 批量绑定关系信息
     *
     * @param userProblems 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userProblem_select"}, allEntries = true)
    public JsonRequest<Integer> addUserProblem(List<UserProblem> userProblems) {
        //判断关系是否已经存在
        Integer column = userProblemMapper.selectAllUserProblemCount(userProblems);
        if (column > 0) {
            log.error("添加关系数据失败!原因:该关系已在数据库中找到");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        //添加日志信息
        List<UserProblem> userProblemList = userProblems.stream().peek(v -> {
            //日志信息
            v.setId(UIDCreateUtil.getUUId());
            v.setCreateUser(SqlDateUtils.currentUserId);
            v.setCreateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //添加
        column = userProblemMapper.insertUserProblem(userProblemList);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加关系数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加{}条关系信息!", userProblems.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量修改关系数据
     *
     * @param userProblems 关系信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userProblem_select"}, allEntries = true)
    public JsonRequest<Integer> updateUserProblem(List<UserProblem> userProblems) {
        //获取ID值
        String[] ids = userProblems.stream().map(UserProblem::getId).toArray(String[]::new);
        JsonRequest<List<UserProblem>> request = userProblemService.lookUserProblemById(ids);
        //判断是否成功
        if (!request.getSuccess()) {
            log.warn("{}", request.getMessage());
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        //加入日志
        List<UserProblem> userProblemList1 = userProblems.stream().peek(v -> {
            //日志信息
            v.setUpdateUser(SqlDateUtils.currentUserId);
            v.setUpdateTime(SqlDateUtils.date);
        }).collect(Collectors.toList());
        //修改
        Integer column = userProblemMapper.updateUserProblemById(userProblemList1);
        if (column < 1) {
            log.error("修改关系信息失败!");
            return JsonRequest.error(RequestException.UPDATE_ERROR);
        }
        log.info("已修改{}条关系信息!", userProblems.size());
        return JsonRequest.success(column);
    }

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userProblem_select"}, allEntries = true)
    public JsonRequest<Integer> deleteUserProblem(String[] ids) {
        JsonRequest<List<UserProblem>> request = userProblemService.lookUserProblemById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = userProblemMapper.deleteUserProblemById(ids);
        if (column < 1) {
            log.error("删除关系失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        log.info("已删除{}条关系信息!", ids.length);
        return JsonRequest.success(column);
    }

    /**
     * 根据题目ID解绑关系
     *
     * @param ids 题目ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"userProblem_select"}, allEntries = true)
    public JsonRequest<Integer> deleteUserProblemByExam(String[] ids) {
        Integer column = userProblemMapper.deleteUserProblemByExam(ids);
        //这里不做处理，因为存在没有关系的数据
        if (column < 1) {
            log.warn("没有任何关系信息!");
        } else {
            log.info("已删除{}条关系信息!", ids.length);
        }
        return JsonRequest.success(column);
    }
}
