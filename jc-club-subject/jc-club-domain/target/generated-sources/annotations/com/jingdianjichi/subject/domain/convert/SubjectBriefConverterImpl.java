package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-07T17:17:20+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class SubjectBriefConverterImpl implements SubjectBriefConverter {

    @Override
    public SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBO.getId() );
        subjectBrief.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );
        subjectBrief.setCreatedBy( subjectInfoBO.getCreatedBy() );
        subjectBrief.setCreatedTime( subjectInfoBO.getCreatedTime() );
        subjectBrief.setUpdateBy( subjectInfoBO.getUpdateBy() );
        subjectBrief.setUpdateTime( subjectInfoBO.getUpdateTime() );
        subjectBrief.setIsDeleted( subjectInfoBO.getIsDeleted() );

        return subjectBrief;
    }
}
