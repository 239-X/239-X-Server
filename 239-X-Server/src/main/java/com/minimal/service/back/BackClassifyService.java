package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.entity.model.Category;
import com.minimal.entity.model.Config;

/**
 * 后端分类接口
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackClassifyService {
    /**
     * 保持对象
     * @param category
     * @return
     */
    int save(Category category);

    /**
     * 修改对象
     * @param categoryg
     * @return
     */
    int update(Category categoryg);

    /**
     * 删除对象
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取对象详情
     * @param id
     * @return
     */
    Category detail(String id);

    /**
     * 分页查询对象
     * @param categoryDto
     * @return
     */
    PageInfo<Category> select(CategoryDto categoryDto);
}
