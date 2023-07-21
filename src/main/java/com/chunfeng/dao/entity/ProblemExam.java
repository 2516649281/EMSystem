package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 题库-试卷绑定实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/16
 */
@Data
@NoArgsConstructor
@ApiModel(value = "题目-试卷关系实体", description = "联系题目和试卷的实体")
public class ProblemExam implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 5012848677067280893L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "关系ID")
    private String id;
    /**
     * 试卷ID
     */
    @ApiModelProperty(value = "试卷ID", allowEmptyValue = true)
    private String examId;
    /**
     * 题库ID
     */
    @ApiModelProperty(value = "题库ID", allowEmptyValue = true)
    private String permissionId;
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
