package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户反馈实体", description = "用于存储用户反馈信息")
public class FeedBack implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 250484899477231777L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "用户反馈ID", allowEmptyValue = true)
    private String id;
    /**
     * 反馈消息
     */
    @ApiModelProperty(value = "用户反馈消息", required = true)
    private String message;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径", hidden = true)
    private String filePath;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
}
