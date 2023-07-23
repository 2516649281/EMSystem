package com.chunfeng.dao.mapper;

import com.chunfeng.dao.entity.FeedBack;

import java.util.List;

/**
 * 反馈数据层接口
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/16
 */
public interface FeedBackMapper {

    /**
     * 条件查询反馈信息
     *
     * @param feedBack 条件
     * @return 反馈列表
     */
    List<FeedBack> selectAllFeedBack(FeedBack feedBack);

    /**
     * 根据ID查询反馈
     *
     * @param ids 反馈ID
     * @return 反馈列表
     */
    List<FeedBack> selectAllFeedBackById(String[] ids);

    /**
     * 插入一条反馈信息
     *
     * @param feedBack 反馈信息
     * @return 影响行数
     */
    Integer insertFeedBack(FeedBack feedBack);

    /**
     * 根据反馈ID批量删除反馈信息
     *
     * @param ids 选择的反馈ID
     * @return 影响行数
     */
    Integer deleteFeedBackById(String[] ids);
}
