package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.InfoBarDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.InfoBar;
import com.minimal.entity.model.InfoBarDetail;
import com.minimal.mapper.back.BackInfoBarDetailMapper;
import com.minimal.mapper.back.BackInfoBarMapper;
import com.minimal.service.back.BackMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class BackMessageServiceImpl implements BackMessageService {
    @Autowired
    private BackInfoBarMapper backInfoBarMapper;

    @Autowired
    private BackInfoBarDetailMapper backInfoBarDetailMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(InfoBarDto infoBarDto) {
        InfoBar infoBarD = infoBarDto.getInfoBar();
        infoBarD.setId(idGenerator.nextId());
        List<InfoBarDetail> infoBarDetailList = infoBarDto.getInfoBarDetailList();
        int num = backInfoBarMapper.insert(infoBarD);
        for (InfoBarDetail infoBarDetail : infoBarDetailList) {
            infoBarDetail.setInfoBarId(infoBarD.getId());;
            backInfoBarDetailMapper.insert(infoBarDetail);
        }
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(InfoBarDto infoBarDto) {
        InfoBar infoBarD = infoBarDto.getInfoBar();
        List<InfoBarDetail> infoBarDetailList = infoBarDto.getInfoBarDetailList();
        int num = backInfoBarMapper.updateByPrimaryKey(infoBarD);
        for (InfoBarDetail infoBarDetail : infoBarDetailList) {
            backInfoBarDetailMapper.updateByPrimaryKey(infoBarDetail);
        }
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(long id) {
        // 删除信息栏表
        int num = backInfoBarMapper.deleteByPrimaryKey(id);
        // 删除信息栏目详情表
        backInfoBarDetailMapper.deleteByInfoBarId(id);
        return num;
    }

    @Override
    public InfoBarDto detail(long id) {
        InfoBarDto infoBarDto = new InfoBarDto();
        InfoBar infoBar = backInfoBarMapper.selectByPrimaryKey(id);
        List<InfoBarDetail> infoBarDetailList = backInfoBarDetailMapper.selectByInfoBarId(id);
        infoBarDto.setInfoBar(infoBar);
        infoBarDto.setInfoBarDetailList(infoBarDetailList);
        return infoBarDto;
    }

    @Override
    public PageInfo<InfoBar> select(InfoBarDto infoBarDto) {
        InfoBar infoBar = new InfoBar();
        // 复制对象
        BeanUtils.copyProps(infoBarDto.getInfoBar(), infoBar);
        int pageNo = infoBarDto.getPageNo();
        int pageSize = infoBarDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<InfoBar> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backInfoBarMapper.select(infoBar);
                    }
                });
        return pageInfo;
    }
}