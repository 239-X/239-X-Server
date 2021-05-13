package com.minimal.back;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.InfoBar;
import com.minimal.entity.model.InfoBarDetail;
import com.minimal.common.api.dto.InfoBarDto;
import com.minimal.service.back.BackMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMessage {
    @Autowired
    private BackMessageService backMessageService;

    @Test
    public void save() {
        InfoBarDto infoBarDto = new InfoBarDto();
        InfoBar infoBar= new InfoBar();
        infoBar.setName("test");
        infoBar.setIsLaunch(true);
        infoBar.setPriority(1);
        infoBar.setCreateTime(new Date());
        infoBar.setUpdateTime(new Date());
        infoBar.setType(1);
        infoBar.setUrl("test");
        infoBar.setContent("test");
        List<InfoBarDetail> infoBarDetailList = new ArrayList<>();
        InfoBarDetail infoBarDetail = new InfoBarDetail();
        infoBarDetail.setPriority(1);
        infoBarDetail.setName("test");
        infoBarDetail.setType(1);
        infoBarDetail.setPriority(1);
        infoBarDetail.setCreateTime(new Date());
        infoBarDetail.setUpdateTime(new Date());
        infoBarDetail.setUrlAddress("test");
        infoBarDetail.setUrlPic("test");
        infoBarDetail.setArticleId(1);
        infoBarDetail.setClassifyId(1);
        infoBarDetailList.add(infoBarDetail);
        infoBarDto.setInfoBarDetailList(infoBarDetailList);
        infoBarDto.setInfoBar(infoBar);
        backMessageService.save(infoBarDto);
    }

    @Test
    public void delete() {
        backMessageService.delete(1084473791674843136L);
    }

    @Test
    public void update() {
        InfoBarDto infoBarDto = new InfoBarDto();
        InfoBar infoBar= new InfoBar();
        infoBar.setId(1084473791674843136L);
        infoBar.setName("test222");
        infoBar.setIsLaunch(true);
        infoBar.setPriority(1);
        infoBar.setCreateTime(new Date());
        infoBar.setUpdateTime(new Date());
        infoBar.setType(1);
        infoBar.setUrl("test222");
        infoBar.setContent("test222");
        List<InfoBarDetail> infoBarDetailList = new ArrayList<>();
        InfoBarDetail infoBarDetail = new InfoBarDetail();
        infoBarDetail.setPriority(1);
        infoBarDetail.setId(1L);
        infoBarDetail.setName("test");
        infoBarDetail.setType(1);
        infoBarDetail.setPriority(1);
        infoBarDetail.setCreateTime(new Date());
        infoBarDetail.setUpdateTime(new Date());
        infoBarDetail.setUrlAddress("test222");
        infoBarDetail.setUrlPic("test222");
        infoBarDetail.setArticleId(1);
        infoBarDetail.setInfoBarId(1084473791674843136L);
        infoBarDetail.setClassifyId(1);
        infoBarDetailList.add(infoBarDetail);
        infoBarDto.setInfoBarDetailList(infoBarDetailList);
        infoBarDto.setInfoBar(infoBar);
        backMessageService.update(infoBarDto);
    }

    @Test
    public void detail() {
        System.out.println(JsonUtils.toJson(backMessageService.detail(1084473791674843136L)));
    }

    @Test
    public void select() {
        InfoBarDto infoBarDto = new InfoBarDto();
        infoBarDto.setPageNo(1);
        infoBarDto.setPageSize(5);
        System.out.println(JsonUtils.toJson(backMessageService.select(infoBarDto)));
    }
}
