package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
 * 标签BO的转换
 * */
@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertInfoBoToInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertInfoListToInfoBoList(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);
}
