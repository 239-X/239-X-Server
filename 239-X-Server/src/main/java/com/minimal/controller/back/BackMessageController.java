package com.minimal.controller.back;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.common.api.dto.InfoBarDto;
import com.minimal.service.back.BackMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 平台信息栏控制类
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "back/message", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BackMessageController {
    @Autowired
    private BackMessageService backMessageService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody InfoBarDto infoBarDto) {
        String result = null;
        try {
            backMessageService.save(infoBarDto);
        } catch (Exception e) {
            log.error("保存失败,parameter={}", JsonUtils.toJson(infoBarDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(@RequestBody InfoBarDto infoBarDto) {
        String result = null;
        try {
            backMessageService.update(infoBarDto);
        } catch (Exception e) {
            log.error("修改失败,parameter={}", JsonUtils.toJson(infoBarDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(int id) {
        String result = null;
        try {
            backMessageService.delete(id);
        } catch (Exception e) {
            log.error("删除失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(int id) {
        String result = null;
        try {
            return JsonUtils.toJson(backMessageService.detail(id));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody InfoBarDto infoBarDto) {
        String result = null;
        try {
            return JsonUtils.toJson(backMessageService.select(infoBarDto));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(infoBarDto), e.getMessage());
        }
        return result;
    }
}

