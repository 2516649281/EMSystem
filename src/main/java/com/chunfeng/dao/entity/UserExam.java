package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户-试卷关系实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/9/5
 */
@Data
@NoArgsConstructor
@ApiModel(value = "用户-试卷关系实体", description = "记录用户的考试记录")
public class UserExam implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = -5687448718878478668L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "关系ID")
    private String id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 试卷ID
     */
    @ApiModelProperty(value = "试卷ID")
    private String examId;
    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")
    private Float score;
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private String time;
    /**
     * 试卷信息
     */
    @ApiModelProperty(value = "试卷信息")
    private Exam exam;
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
