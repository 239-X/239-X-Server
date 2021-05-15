package com.minimal.service.front.impl;

import com.minimal.common.api.dto.InfoBarDto;
import com.minimal.entity.model.InfoBar;
import com.minimal.entity.model.InfoBarDetail;
import com.minimal.mapper.back.BackInfoBarDetailMapper;
import com.minimal.mapper.back.BackInfoBarMapper;
import com.minimal.service.front.FrontMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public class FrontMessageServiceImpl implements FrontMessageService {
    @Autowired
    private BackInfoBarMapper backInfoBarMapper;

    @Autowired
    private BackInfoBarDetailMapper backInfoBarDetailMapper;

    @Override
    public List<InfoBarDto> detail() {
        tk.mybatis.mapper.entity.Example infoBarExample = new tk.mybatis.mapper.entity.Example(InfoBar.class);
        infoBarExample.createCriteria().andEqualTo("is_launch", true);
        infoBarExample.setOrderByClause("priority DESC,create_time DESC");
        List<InfoBar> infoBarList = backInfoBarMapper.selectByExample(infoBarExample);
        List<InfoBarDto> infoBarDtoList = new ArrayList<>();
        for (InfoBar infoBar : infoBarList) {
            InfoBarDto infoBarDto = new InfoBarDto();
            tk.mybatis.mapper.entity.Example infoBarDetailExample = new tk.mybatis.mapper.entity.Example(InfoBarDetail.class);
            infoBarDetailExample.createCriteria().andEqualTo("infobar_id", infoBar.getId());
            List<InfoBarDetail> infoBarDetailList = backInfoBarDetailMapper.selectByExample(infoBarDetailExample);
            infoBarDto.setInfoBar(infoBar);
            infoBarDto.setInfoBarDetailList(infoBarDetailList);
            infoBarDtoList.add(infoBarDto);
        }
        return infoBarDtoList;
    }
}
