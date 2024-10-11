package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectJudge;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:14:09+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class SubjectJudgeConverterImpl implements SubjectJudgeConverter {

    @Override
    public SubjectJudge convertBoToEntity(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectJudge subjectJudge = new SubjectJudge();

        subjectJudge.setId( subjectInfoBO.getId() );
        subjectJudge.setCreatedBy( subjectInfoBO.getCreatedBy() );
        subjectJudge.setCreatedTime( subjectInfoBO.getCreatedTime() );
        subjectJudge.setUpdateBy( subjectInfoBO.getUpdateBy() );
        subjectJudge.setUpdateTime( subjectInfoBO.getUpdateTime() );
        subjectJudge.setIsDeleted( subjectInfoBO.getIsDeleted() );

        return subjectJudge;
    }
}
