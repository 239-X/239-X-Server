package com.minimal.service.front;

import com.minimal.common.api.dto.InfoBarDto;

import java.util.List;

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
     * @return
     */
    List<InfoBarDto> detail();
}
