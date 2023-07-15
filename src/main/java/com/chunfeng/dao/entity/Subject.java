package com.chunfeng.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 科目实体
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/14
 */
@Data
@NoArgsConstructor
public class Subject implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 科目名
     */
    private String name;
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
