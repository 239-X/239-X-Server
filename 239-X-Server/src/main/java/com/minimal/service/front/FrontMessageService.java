package com.minimal.service.front;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.InfoBarDto;
import com.minimal.entity.model.InfoBar;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface FrontMessageService {
    /**
     * 获取对象详情
     *
     * @param id
     * @return
     */
    void detail(long id);
}
