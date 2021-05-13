package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import com.minimal.entity.model.InfoBar;
import com.minimal.entity.model.InfoBarDetail;
import lombok.Data;

import java.util.List;

@Data
public class InfoBarDto extends TablePage {
    /**
     * 信息栏
     */
    private InfoBar infoBar;

    /**
     * 信息栏详情
     */
    private List<InfoBarDetail> infoBarDetailList;
}