package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.api.dto.MenuDto;
import com.minimal.entity.model.Menu;
import com.minimal.entity.model.Menu;

/**
 * 后端分类接口
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackMenuService {
    /**
     * 保持对象
     * @param menu
     * @return
     */
    int save(Menu menu);

    /**
     * 修改对象
     * @param menu
     * @return
     */
    int update(Menu menu);

    /**
     * 删除对象
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 获取对象详情
     * @param id
     * @return
     */
    Menu detail(String id);

    /**
     * 分页查询对象
     * @param menuDto
     * @return
     */
    PageInfo<Menu> select(MenuDto menuDto);
}
