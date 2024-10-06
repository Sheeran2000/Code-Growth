package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;

public interface SubjectTypeHandler {

    /*
    枚举身份的识别
    */
    SubjectInfoTypeEnum getHandlerType();

    /*
    * 实际题目的插入
    * */
    void add(SubjectInfoBO subjectInfoBO);
}
