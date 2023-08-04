package com.chunfeng.service;

import com.chunfeng.dao.entity.FeedBack;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 反馈业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
public interface IFeedBackService {

    /**
     * 分类筛选反馈信息
     *
     * @param feedBack 条件
     * @return JSON
     */
    JsonRequest<List<FeedBack>> lookFeedBack(FeedBack feedBack);

    /**
     * 查询所有反馈信息
     *
     * @return JSON
     */
    JsonRequest<List<FeedBack>> lookAllFeedBack();

    /**
     * 根据ID值批量查询反馈信息
     *
     * @param ids 反馈ID
     * @return JSON
     */
    JsonRequest<List<FeedBack>> lookFeedBackById(String[] ids);

    /**
     * 新增一条反馈信息
     *
     * @param feedBack 反馈信息
     * @return JSON
     */
    JsonRequest<Integer> addOneFeedBack(FeedBack feedBack);


    /**
     * 批量删除反馈信息
     *
     * @param ids 反馈ID
     * @return JSON
     */
    JsonRequest<Integer> deleteFeedBack(String[] ids);
}
