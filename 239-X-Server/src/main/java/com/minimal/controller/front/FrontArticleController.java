package com.minimal.controller.front;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Article;
import com.minimal.service.front.FrontArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 前台文章控制类
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "front/article", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class FrontArticleController {
    @Autowired
    private FrontArticleService frontArticleService;

    @RequestMapping(value = "saveArticle", method = RequestMethod.POST)
    public String saveArticle(@RequestBody Article article) {
        String result = null;
        try {
        } catch (Exception e) {
            log.error("失败,article={}", JsonUtils.toJson(article), e.getMessage());
        }
        return result;
    }
}

