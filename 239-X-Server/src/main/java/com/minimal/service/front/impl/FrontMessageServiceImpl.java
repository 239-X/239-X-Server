package com.minimal.service.front.impl;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.InfoBarDto;
import com.minimal.entity.model.InfoBar;
import com.minimal.mapper.back.BackInfoBarDetailMapper;
import com.minimal.mapper.back.BackInfoBarMapper;
import com.minimal.service.front.FrontMessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public class FrontMessageServiceImpl implements FrontMessageService{
    @Autowired
    private BackInfoBarMapper backInfoBarMapper;

    @Autowired
    private BackInfoBarDetailMapper backInfoBarDetailMapper;

    @Override
    public void detail(long id) {

    }
}
