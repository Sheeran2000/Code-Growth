package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
import com.jingdianjichi.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * 标签BO的转换
 * */
@Mapper
public interface SubjectBriefConverter {

    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);

    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);
}
