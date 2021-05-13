package com.minimal.controller.back;

import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.api.dto.MenuDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Category;
import com.minimal.entity.model.Menu;
import com.minimal.service.back.BackMenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台菜单控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "back/menu", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BackMenuController {
    @Autowired
    private BackMenuService backMenuService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody Menu menu) {
        String result = null;
        try {
            backMenuService.save(menu);
        } catch (Exception e) {
            log.error("保存失败,parameter={}", JsonUtils.toJson(menu), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(@RequestBody Menu menu) {
        String result = null;
        try {
            backMenuService.update(menu);
        } catch (Exception e) {
            log.error("修改失败,parameter={}", JsonUtils.toJson(menu), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(int id) {
        String result = null;
        try {
            backMenuService.delete(id);
        } catch (Exception e) {
            log.error("删除失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(String id) {
        String result = null;
        try {
            return JsonUtils.toJson(backMenuService.detail(id));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody MenuDto menuDto) {
        String result = null;
        try {
            return JsonUtils.toJson(backMenuService.select(menuDto));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(menuDto), e.getMessage());
        }
        return result;
    }
}

