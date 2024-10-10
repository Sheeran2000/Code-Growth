package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.application.dto.SubjectInfoDTO;
import com.jingdianjichi.subject.application.dto.SubjectLabelDTO;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
 * 题目DTO的转换
 * */
@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertSubjectInfoDtoToBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBoToDto(SubjectInfoBO subjectInfoBO);





}
