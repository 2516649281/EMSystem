package com.chunfeng.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户反馈实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
@NoArgsConstructor
public class FeedBack implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 2871184560686085653L;
    /**
     * 主键
     */
    private String id;
    /**
     * 反馈消息
     */
    private String message;
    /**
     * 创建时间
     */
    private String createTime;
}
