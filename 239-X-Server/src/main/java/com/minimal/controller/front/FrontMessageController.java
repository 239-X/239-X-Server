package com.minimal.controller.front;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.front.FrontMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台信息栏控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "front/message", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class FrontMessageController {
    @Autowired
    private FrontMessageService frontMessageService;

    @RequestMapping(value = "homeMessage", method = RequestMethod.POST)
    public String homeMessage() {
        String result = null;
        try {
            return JsonUtils.toJson(frontMessageService.detail());
        } catch (Exception e) {
            log.error("获取信息栏目失败,error={}", e.getMessage());
        }
        return result;
    }
}

