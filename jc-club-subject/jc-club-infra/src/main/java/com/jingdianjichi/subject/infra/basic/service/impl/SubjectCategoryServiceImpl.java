package com.jingdianjichi.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingdianjichi.subject.common.enums.DeletedFlagEnum;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.mapper.SubjectCategoryDao;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-10-02 10:17:21
 */
@Service("subjectCategoryService")
@Slf4j
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.SubjectCategory:{}", JSON.toJSONString(subjectCategory));
        }
        this.subjectCategoryDao.insert(subjectCategory);
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.update(subjectCategory);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectCategory> queryCategory(SubjectCategory subjectCategory) {
        subjectCategory.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        return subjectCategoryDao.queryCategory(subjectCategory);
    }
}
