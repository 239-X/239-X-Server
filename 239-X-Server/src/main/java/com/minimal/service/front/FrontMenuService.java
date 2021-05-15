package com.minimal.service.front;

import com.minimal.entity.model.Menu;

import java.util.List;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface FrontMenuService {
    /**
     * 获取对象详情
     *
     * @return
     */
    List<Menu> detail();
}
