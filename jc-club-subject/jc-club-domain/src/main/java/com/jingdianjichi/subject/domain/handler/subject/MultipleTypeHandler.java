package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.DeletedFlagEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.domain.convert.MultipleSubjectConverter;
import com.jingdianjichi.subject.domain.convert.SubjectMultipleConverter;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMultiple;
import com.jingdianjichi.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.LinkedList;
import java.util.List;

/*
 * 多选的题目策略类
 * */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 多项题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = SubjectMultipleConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(Long.valueOf(subjectId));
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
