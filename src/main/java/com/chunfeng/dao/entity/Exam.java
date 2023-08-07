package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷实体类
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
@NoArgsConstructor
@ApiModel(value = "试卷实体类", description = "用于存储试卷数据的实体")
public class Exam implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = -3426363852575271447L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "试卷ID", allowEmptyValue = true)
    private String id;
    /**
     * 试卷名
     */
    @ApiModelProperty(value = "试卷名称")
    private String name;
    /**
     * 考试时间
     */
    @ApiModelProperty(value = "考试时间", allowEmptyValue = true)
    private String time;
    /**
     * 总分
     */
    @ApiModelProperty(value = "总分", allowEmptyValue = true)
    private Float score;
    /**
     * 合格分数
     */
    @ApiModelProperty(value = "合格分数", allowEmptyValue = true)
    private Float pass;
    /**
     * 存放路径
     */
    @ApiModelProperty(value = "存放路径", hidden = true)
    private String filePath;
    /**
     * 题库列表
     */
    @ApiModelProperty(value = "题库列表", allowEmptyValue = true)
    private List<Problem> problemList;
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
