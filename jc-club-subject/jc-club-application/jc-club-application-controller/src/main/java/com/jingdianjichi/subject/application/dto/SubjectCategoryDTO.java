package com.jingdianjichi.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)DTO
 *
 * @author makejava
 * @since 2024-10-02 10:17:20
 */
@Data
public class SubjectCategoryDTO implements Serializable {
    private static final long serialVersionUID = 533849145450906962L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;



}
