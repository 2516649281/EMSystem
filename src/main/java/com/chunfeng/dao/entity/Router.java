package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 路由实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/30
 */
@Data
@NoArgsConstructor
@ApiModel(value = "路由实体", description = "用于存储请求路由")
public class Router implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = 7154360014276182302L;
    /**
     * 路由ID
     */
    @ApiModelProperty(value = "路由ID", allowEmptyValue = true)
    private String id;
    /**
     * 路由名
     */
    @ApiModelProperty(value = "路由名")
    private String name;
    /**
     * 路由值
     */
    @ApiModelProperty(value = "路由值")
    private String value;
    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式", allowEmptyValue = true)
    private String method;
    /**
     * 是否默认(0默认,1自定义)
     */
    @ApiModelProperty(value = "是否默认(0默认,1自定义)", allowEmptyValue = true)
    private Integer isDefault;
    /**
     * 路由类型(0后端,1前端)
     */
    @ApiModelProperty(value = "路由类型(0后端,1前端)", allowableValues = "0,1", allowEmptyValue = true)
    private Integer type;
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
