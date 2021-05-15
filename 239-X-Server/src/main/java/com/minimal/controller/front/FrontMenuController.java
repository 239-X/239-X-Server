package com.minimal.controller.front;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.front.FrontMenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台菜单控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "front/menu", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class FrontMenuController {
    @Autowired
    private FrontMenuService frontMenuService;

    @RequestMapping(value = "homeMenu", method = RequestMethod.POST)
    public String homeMenu() {
        String result = null;
        try {
            return JsonUtils.toJson(frontMenuService.detail());
        } catch (Exception e) {
            log.error("获取首页菜单失败,error={}", e.getMessage());
        }
        return result;
    }
}

