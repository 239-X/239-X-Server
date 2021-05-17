package com.minimal.controller.front;

import com.minimal.common.api.dto.front.FrontArticleDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.front.FrontArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台文章控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "front/article", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class FrontArticleController {
    @Autowired
    private FrontArticleService frontArticleService;

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(String key) {
        String result = null;
        try {
            return JsonUtils.toJson(frontArticleService.search(key));
        } catch (Exception e) {
            log.error("文章搜索失败,key：{}，错误信息：{}", key, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody FrontArticleDto frontArticleDto) {
        String result = null;
        try {
            return JsonUtils.toJson(frontArticleService.select(frontArticleDto));
        } catch (Exception e) {
            log.error("文章列表查询失败,parameter={}", JsonUtils.toJson(frontArticleDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(String id) {
        String result = null;
        try {
            result = JsonUtils.toJson(frontArticleService.detail(id));
        } catch (Exception e) {
            log.error("获取文章详细信息失败,id={},error={}", id, e.getMessage());
        }
        return result;
    }
}

