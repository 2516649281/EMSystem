package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户-题目关系实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/9/5
 */
@Data
@NoArgsConstructor
@ApiModel(value = "用户-题目关系实体", description = "记录用户的做题记录")
public class UserProblem implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 66814594925300787L;
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
     * 题库ID
     */
    @ApiModelProperty(value = "题库ID")
    private String problemId;
    /**
     * 状态(0正确,1错误)
     */
    @ApiModelProperty(value = "是否正确", allowableValues = "0,1")
    private Integer status;
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
