package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/*
 * 题目领域服务
 * */
public interface SubjectInfoDomainService {

    /*
     * 新增题目
     * */
    void add(SubjectInfoBO subjectInfoBO);
}
