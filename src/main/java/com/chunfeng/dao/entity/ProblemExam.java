package com.chunfeng.dao.entity;

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
public class ProblemExam implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 5012848677067280893L;
    /**
     * 主键
     */
    private String id;
    /**
     * 试卷ID
     */
    private String examId;
    /**
     * 题库ID
     */
    private String permissionId;
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
