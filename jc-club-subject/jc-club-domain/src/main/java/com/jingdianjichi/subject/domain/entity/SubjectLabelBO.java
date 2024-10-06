package com.jingdianjichi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)BO
 *
 * @author makejava
 * @since 2024-10-06 14:10:38
 */
@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = 684062329794856434L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;

    private Long categoryId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer isDeleted;


}

