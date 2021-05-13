package com.minimal.controller.back;

import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.Config;
import com.minimal.service.back.BackConfigurationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 平台标签控制类
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "back/tag", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BackTagController {
    @Autowired
    private BackConfigurationService backConfigurationService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody Config config) {
        String result = null;
        try {
            backConfigurationService.save(config);
        } catch (Exception e) {
            log.error("保存失败,parameter={}", JsonUtils.toJson(config), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(@RequestBody Config config) {
        String result = null;
        try {
            backConfigurationService.update(config);
        } catch (Exception e) {
            log.error("修改失败,parameter={}", JsonUtils.toJson(config), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(String id) {
        String result = null;
        try {
            backConfigurationService.delete(id);
        } catch (Exception e) {
            log.error("删除失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(String id) {
        String result = null;
        try {
            return JsonUtils.toJson(backConfigurationService.detail(id));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody ConfigurationDto configDto) {
        String result = null;
        try {
            return JsonUtils.toJson(backConfigurationService.select(configDto));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(configDto), e.getMessage());
        }
        return result;
    }
}

