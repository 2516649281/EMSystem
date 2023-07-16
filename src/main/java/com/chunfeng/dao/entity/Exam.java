package com.chunfeng.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 试卷实体类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
@NoArgsConstructor
public class Exam implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 试卷名
     */
    private String name;
    /**
     * 考试时间
     */
    private String time;
    /**
     * 总分
     */
    private Float score;
    /**
     * 合格分数
     */
    private Float pass;
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
