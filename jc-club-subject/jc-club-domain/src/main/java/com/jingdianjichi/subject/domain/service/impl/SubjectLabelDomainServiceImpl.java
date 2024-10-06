package com.jingdianjichi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.common.enums.DeletedFlagEnum;
import com.jingdianjichi.subject.domain.convert.SubjectCategoryConverter;
import com.jingdianjichi.subject.domain.convert.SubjectLabelConverter;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianjichi.subject.domain.service.SubjectLabelDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import com.jingdianjichi.subject.infra.basic.service.SubjectLabelService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    /*
     * 新增标签
     * */
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {

        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }

        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertLabelBoToLabel(subjectLabelBO);

        subjectLabel.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());

        int row = subjectLabelService.insert(subjectLabel);

        return row > 0;
    }

    /**
     * 更新标签
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.update.bo:{}", JSON.toJSONString(subjectLabelBO));
        }

        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertLabelBoToLabel(subjectLabelBO);

        int row = subjectLabelService.update(subjectLabel);

        return row > 0;
    }

    /**
     * 删除标签
     */
    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.delete.bo:{}", JSON.toJSONString(subjectLabelBO));
        }

        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertLabelBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(DeletedFlagEnum.IS_DELETED.getCode());
        int row = subjectLabelService.update(subjectLabel);

        return row > 0;
    }

    /**
     * 查询分类下标签
     */
    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> boList = new ArrayList<>();
        subjectLabelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(label.getSortNum());

            boList.add(bo);
        });
        return boList;
    }
}
