package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/*
* 分类标签领域服务
* */

public interface SubjectCategoryDomainService {

    /**
     * 新增分类
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /*
    * 更新分类
    * */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /*
    * 删除分类
    * */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
