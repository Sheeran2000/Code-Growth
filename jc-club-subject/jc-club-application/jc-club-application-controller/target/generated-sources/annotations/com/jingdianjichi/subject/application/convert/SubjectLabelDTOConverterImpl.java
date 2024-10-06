package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.application.dto.SubjectLabelDTO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-06T15:41:29+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class SubjectLabelDTOConverterImpl implements SubjectLabelDTOConverter {

    @Override
    public SubjectLabelBO convertLabelDtoToLabelBO(SubjectLabelDTO subjectLabelDTO) {
        if ( subjectLabelDTO == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( subjectLabelDTO.getId() );
        subjectLabelBO.setLabelName( subjectLabelDTO.getLabelName() );
        subjectLabelBO.setSortNum( subjectLabelDTO.getSortNum() );
        if ( subjectLabelDTO.getCategoryId() != null ) {
            subjectLabelBO.setCategoryId( Long.parseLong( subjectLabelDTO.getCategoryId() ) );
        }
        subjectLabelBO.setCreatedBy( subjectLabelDTO.getCreatedBy() );
        subjectLabelBO.setCreatedTime( subjectLabelDTO.getCreatedTime() );
        subjectLabelBO.setUpdateBy( subjectLabelDTO.getUpdateBy() );
        subjectLabelBO.setUpdateTime( subjectLabelDTO.getUpdateTime() );
        subjectLabelBO.setIsDeleted( subjectLabelDTO.getIsDeleted() );

        return subjectLabelBO;
    }

    @Override
    public List<SubjectLabelDTO> convertLabelBoListToDtoList(List<SubjectLabelBO> boList) {
        if ( boList == null ) {
            return null;
        }

        List<SubjectLabelDTO> list = new ArrayList<SubjectLabelDTO>( boList.size() );
        for ( SubjectLabelBO subjectLabelBO : boList ) {
            list.add( subjectLabelBOToSubjectLabelDTO( subjectLabelBO ) );
        }

        return list;
    }

    protected SubjectLabelDTO subjectLabelBOToSubjectLabelDTO(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabelDTO subjectLabelDTO = new SubjectLabelDTO();

        subjectLabelDTO.setId( subjectLabelBO.getId() );
        subjectLabelDTO.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabelDTO.setSortNum( subjectLabelBO.getSortNum() );
        if ( subjectLabelBO.getCategoryId() != null ) {
            subjectLabelDTO.setCategoryId( String.valueOf( subjectLabelBO.getCategoryId() ) );
        }
        subjectLabelDTO.setCreatedBy( subjectLabelBO.getCreatedBy() );
        subjectLabelDTO.setCreatedTime( subjectLabelBO.getCreatedTime() );
        subjectLabelDTO.setUpdateBy( subjectLabelBO.getUpdateBy() );
        subjectLabelDTO.setUpdateTime( subjectLabelBO.getUpdateTime() );
        subjectLabelDTO.setIsDeleted( subjectLabelBO.getIsDeleted() );

        return subjectLabelDTO;
    }
}
