package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianjichi.subject.application.convert.SubjectAnswerDTOConverter;
import com.jingdianjichi.subject.application.convert.SubjectInfoDTOConverter;
import com.jingdianjichi.subject.application.convert.SubjectLabelDTOConverter;
import com.jingdianjichi.subject.application.dto.SubjectInfoDTO;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.entity.Result;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.domain.service.SubjectInfoDomainService;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
 * 题目Controller
 * */

@RestController()
@Slf4j
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;


    /**
     * 新增题目
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank
                    (subjectInfoDTO.getSubjectName()), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryIds(), "题目分类ID不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelIds(), "题目标签ID不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertSubjectInfoDtoToBO(subjectInfoDTO);

            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerDTOConverter.INSTANCE.convertDtoListToBO
                    (subjectInfoDTO.getOptionList());

            subjectInfoBO.setOptionList(subjectAnswerBOList);

            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectLabelController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增题目失败");
        }
    }


    /**
     * 查询题目列表
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "题目分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "题目标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertSubjectInfoDtoToBO(subjectInfoDTO);

            PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(boPageResult);
        } catch (Exception e) {
            log.error("SubjectLabelController.getSubjectPage.error:{}", e.getMessage(), e);
            return Result.fail("查询题目列表失败");
        }
    }

    /**
     * 查询题目详情
     */
    @PostMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.querySubjectInfo.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");


            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertSubjectInfoDtoToBO(subjectInfoDTO);

            subjectInfoBO = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);

            SubjectInfoDTO dto = SubjectInfoDTOConverter.INSTANCE.convertBoToDto(subjectInfoBO);


            return Result.ok(dto);
        } catch (Exception e) {
            log.error("SubjectLabelController.querySubjectInfo.error:{}", e.getMessage(), e);
            return Result.fail("查询题目详情失败");
        }
    }
}
