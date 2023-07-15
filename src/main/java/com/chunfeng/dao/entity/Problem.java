package com.chunfeng.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 题库实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
@NoArgsConstructor
public class Problem implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 题干
     */
    private String main;
    /**
     * 选项
     */
    private String options;
    /**
     * 类型(0客观题,1主观题)
     */
    private Integer type;
    /**
     * 答案
     */
    private String answer;
    /**
     * 解析
     */
    private String parse;
    /**
     * 得分
     */
    private Float score;
    /**
     * 科目
     */
    private Long subject;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
