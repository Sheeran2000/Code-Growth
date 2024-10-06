package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.application.dto.SubjectCategoryDTO;
import com.jingdianjichi.subject.application.dto.SubjectLabelDTO;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
 * 标签DTO的转换
 * */
@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertLabelDtoToLabelBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertLabelBoListToDtoList(List<SubjectLabelBO> boList);


}
