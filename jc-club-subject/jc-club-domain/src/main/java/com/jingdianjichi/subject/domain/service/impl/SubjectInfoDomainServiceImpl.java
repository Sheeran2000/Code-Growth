package com.jingdianjichi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.DeletedFlagEnum;
import com.jingdianjichi.subject.domain.convert.SubjectCategoryConverter;
import com.jingdianjichi.subject.domain.convert.SubjectInfoConverter;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.domain.handler.subject.SubjectTypeHandler;
import com.jingdianjichi.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianjichi.subject.domain.service.SubjectInfoDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectLabelService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;


    // 新增题目分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {

        if (log.isInfoEnabled()) {
            log.info("SubjectController.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        // 假设我们都写在主流程里面
        // 判断type, 单选的调用单选的service ,多选的调用多选的service
        // 一大堆的if
        // 使用工厂＋策略的模式
        // 一个工厂包含4中类型  根据传入的type自动映射选择处理
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertInfoBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        List<Integer> labelList = subjectInfoBO.getLabelIds();
        List<Integer> categoryList = subjectInfoBO.getCategoryIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryList.forEach(categoryId -> {
            labelList.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);

    }

    /*
     * 分页查询
     * */
    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertInfoBoToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo,
                subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertInfoListToInfoBoList(subjectInfoList);
        pageResult.setRecords(subjectInfoBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    /**
     * 查询题目详情
     */
    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());

        SubjectOptionBO optionBO = handler.query(subjectInfo.getId().intValue());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);

        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = subjectLabelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNameList);
        return bo;
    }
}
