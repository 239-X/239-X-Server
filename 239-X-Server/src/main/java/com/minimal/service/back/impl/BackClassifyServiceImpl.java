package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.Category;
import com.minimal.entity.model.Config;
import com.minimal.mapper.back.BackCategoryMapper;
import com.minimal.mapper.back.BackConfigMapper;
import com.minimal.service.back.BackClassifyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后端分类服务实现类
 * @author linzhiqiang
 * @date 2021-05-05
 */
@Service
@Log4j2
public class BackClassifyServiceImpl implements BackClassifyService {
    @Autowired
    private BackCategoryMapper backCategoryMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(Category category) {
        category.setId(idGenerator.nextId() + "");
        int savaNumber = backCategoryMapper.insert(category);
        return savaNumber;
    }

    @Override
    public int update(Category category) {
        int updateNumber = backCategoryMapper.updateByPrimaryKey(category);
        return updateNumber;
    }

    @Override
    public int delete(String id) {
        int deleteNumber = backCategoryMapper.deleteByPrimaryKey(id);
        return deleteNumber;
    }

    @Override
    public Category detail(String id) {
        return backCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Category> select(CategoryDto categoryDto) {
        Category category = new Category();
        // 复制对象
        BeanUtils.copyProps(categoryDto, category);
        int pageNo = categoryDto.getPageNo();
        int pageSize = categoryDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Category> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backCategoryMapper.select(category);
                    }
                });
        return pageInfo;
    }
}