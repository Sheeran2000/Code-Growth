package com.jingdianjichi.subject.application.dto;

import com.jingdianjichi.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)DTO
 *
 * @author makejava
 * @since 2024-10-06 16:45:56
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
    private static final long serialVersionUID = 410303153841866320L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 分类id
     */
    private List<Integer> categoryIds;

    /**
     * 标签id
     */
    private List<Integer> labelIds;
    /**
     * 标签name
     */
    private List<String> labelName;


    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer isDeleted;

    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;

    private Long categoryId;

    private Long labelId;


}

