package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "题目实体类", description = "用于存储题目数据的实体")
public class Problem implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = -4908814879428583435L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "题目ID")
    private String id;
    /**
     * 题干
     */
    @ApiModelProperty(value = "题干")
    private String main;
    /**
     * 选项
     */
    @ApiModelProperty(value = "选项")
    private String options;
    /**
     * 类型(0客观题,1主观题)
     */
    @ApiModelProperty(value = "类型", allowableValues = "0,1")
    private Integer type;
    /**
     * 答案
     */
    @ApiModelProperty(value = "答案")
    private String answer;
    /**
     * 解析
     */
    @ApiModelProperty(value = "解析")
    private String parse;
    /**
     * 得分
     */
    @ApiModelProperty(value = "得分", allowEmptyValue = true)
    private Float score;
    /**
     * 科目
     */
    @ApiModelProperty(value = "科目")
    private String subjectId;
    /**
     * 存放路径
     */
    @ApiModelProperty(value = "存放路径", hidden = true)
    private String filePath;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createUser;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;
}
