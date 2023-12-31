package com.chunfeng.service.impl;

import com.chunfeng.dao.entity.FeedBack;
import com.chunfeng.dao.mapper.FeedBackMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.exenum.RequestException;
import com.chunfeng.service.IFeedBackService;
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
 * 反馈业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@Service
@Slf4j
public class FeedBackServiceImpl implements IFeedBackService {

    /**
     * 反馈数据层
     */
    @Autowired
    private FeedBackMapper feedBackMapper;

    /**
     * 解决Spring缓存内部调用失效
     */
    @Lazy
    @Autowired
    private IFeedBackService feedBackService;
    /**
     * 文件工具类
     */
    @Autowired
    private FileMangerUtils<FeedBack> fileMangerUtils;

    /**
     * 分类筛选反馈信息
     *
     * @param feedBack 条件
     * @return JSON
     */
    @Override
    @Cacheable(value = "feedBack_select", key = "#feedBack")
    public JsonRequest<List<FeedBack>> lookFeedBack(FeedBack feedBack) {
        List<FeedBack> feedBacks = feedBackMapper.selectAllFeedBack(feedBack);
        //判断是否成功
        if (feedBacks.isEmpty()) {
            log.warn("未找到任何反馈信息!");
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //提取ID值
        List<String> ids = feedBacks.stream()
                .map(FeedBack::getId)//获取ID值
                .collect(Collectors.toList());//转换为list集合
        //查询结果
        List<FeedBack> feedBackList = ids.stream()
                .map(id -> fileMangerUtils.fileLook("feedBack_" + id + ".txt"))//遍历获取结果
                .collect(Collectors.toList());//收集查询结果
        log.info("已找到{}条反馈信息", feedBacks.size());
        return JsonRequest.success(feedBackList);
    }

    /**
     * 查询所有反馈信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "feedBack_select")
    public JsonRequest<List<FeedBack>> lookAllFeedBack() {
        return feedBackService.lookFeedBack(new FeedBack());
    }

    /**
     * 根据ID值批量查询反馈信息
     *
     * @param ids 反馈ID
     * @return JSON
     */
    @Override
    @Cacheable(value = "feedBack_select", key = "#ids")
    public JsonRequest<List<FeedBack>> lookFeedBackById(String[] ids) {
        List<FeedBack> feedBacks = feedBackMapper.selectAllFeedBackById(ids);
        if (feedBacks.size() != ids.length) {
            log.warn("待查询的反馈ID与数据库中的数量不符!数据库:{},实际:{}", feedBacks.size(), ids.length);
            return JsonRequest.error(RequestException.NOT_FOUND);
        }
        //提取ID值
        List<String> ids1 = feedBacks.stream()
                .map(FeedBack::getId)//获取ID值
                .collect(Collectors.toList());//转换为list集合
        //查询结果
        List<FeedBack> feedBackList = ids1.stream()
                .map(id -> fileMangerUtils.fileLook("feedBack_" + id + ".txt"))//遍历获取结果
                .collect(Collectors.toList());//收集查询结果
        log.info("已查询出{}条反馈数据!", feedBacks.size());
        return JsonRequest.success(feedBackList);
    }

    /**
     * 新增一条反馈信息
     *
     * @param feedBack 反馈信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "feedBack_select", allEntries = true)
    public JsonRequest<Integer> addOneFeedBack(FeedBack feedBack) {
        //日志信息
        feedBack.setId(UIDCreateUtil.getUUId());
        feedBack.setCreateTime(SqlDateUtils.date);
        //将数据写入文件
        String path = fileMangerUtils.fileWriter("feedBack_" + feedBack.getId() + ".txt", feedBack);
        //存入路径
        feedBack.setFilePath(path);
        Integer column = feedBackMapper.insertFeedBack(feedBack);
        //判断添加是否成功
        if (column < 1) {
            log.error("添加反馈数据失败!");
            return JsonRequest.error(RequestException.INSERT_ERROR);
        }
        log.info("已向数据库添加一条反馈信息!");
        return JsonRequest.success(column);
    }

    /**
     * 批量删除反馈信息
     *
     * @param ids 反馈ID
     * @return JSON
     */
    @Override
    @CacheEvict(value = {"feedBack_select"}, allEntries = true)
    public JsonRequest<Integer> deleteFeedBack(String[] ids) {
        JsonRequest<List<FeedBack>> request = feedBackService.lookFeedBackById(ids);
        if (!request.getSuccess()) {
            log.error("{}", request.getMessage());
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        Integer column = feedBackMapper.deleteFeedBackById(ids);
        if (column < 1) {
            log.error("删除反馈失败!");
            return JsonRequest.error(RequestException.DELETE_ERROR);
        }
        //删除文件内容
        Arrays.stream(ids)
                .forEach(id -> fileMangerUtils.fileDelete("feedBack_" + id + ".txt"));
        log.info("已删除{}条反馈信息!", ids.length);
        return JsonRequest.success(column);
    }
}
