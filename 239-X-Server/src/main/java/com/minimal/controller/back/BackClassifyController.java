package com.minimal.controller.back;

import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Category;
import com.minimal.service.back.BackClassifyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台类目控制器
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "back/classify", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BackClassifyController {
    @Autowired
    private BackClassifyService backClassifyService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody Category category) {
        String result = null;
        try {
            backClassifyService.save(category);
        } catch (Exception e) {
            log.error("保存失败,parameter={}", JsonUtils.toJson(category), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(@RequestBody Category category) {
        String result = null;
        try {
            backClassifyService.update(category);
        } catch (Exception e) {
            log.error("修改失败,parameter={}", JsonUtils.toJson(category), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(String id) {
        String result = null;
        try {
            backClassifyService.delete(id);
        } catch (Exception e) {
            log.error("删除失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(String id) {
        String result = null;
        try {
            return JsonUtils.toJson(backClassifyService.detail(id));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody CategoryDto categoryDto) {
        String result = null;
        try {
            return JsonUtils.toJson(backClassifyService.select(categoryDto));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(categoryDto), e.getMessage());
        }
        return result;
    }
}

