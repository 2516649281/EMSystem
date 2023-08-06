package com.chunfeng.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 年级实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/8/6
 */
@Data
@NoArgsConstructor
@ApiModel(value = "年级实体类", description = "用于存储年级数据的实体")
public class Gradle implements Serializable {
    /**
     * 序列化字段
     */
    @ApiModelProperty(value = "序列化字段", hidden = true)
    private static final long serialVersionUID = -3676131659474593648L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "年级ID")
    private String id;
    /**
     * 年级名
     */
    @ApiModelProperty(value = "年级名")
    private String name;
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
