package com.jingdianjichi.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目答案DTO
 *
 * @author makejava
 * @since 2024-10-06 16:45:56
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = 410303153841866320L;
    /**
     * 答案选项标示
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;

}

