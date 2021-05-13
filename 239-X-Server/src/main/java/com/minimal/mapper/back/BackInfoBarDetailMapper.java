package com.minimal.mapper.back;

import com.minimal.entity.model.InfoBar;
import com.minimal.entity.model.InfoBarDetail;
import com.minimal.entity.model.InfoBarDetail;
import feign.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BackInfoBarDetailMapper extends Mapper<InfoBarDetail> {
    int deleteByInfoBarId(@Param("infoBarId") long infoBarId);

    List<InfoBarDetail> selectByInfoBarId(@Param("infoBarId") long infoBarId);
}