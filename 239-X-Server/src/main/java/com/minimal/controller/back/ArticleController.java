package com.minimal.controller.back;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.common.sdk.utils.RedisUtil;
import com.minimal.entity.model.Article;
import com.minimal.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 文章控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class ArticleController {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.imgPath}")
    private String imgPath;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 保存文章信息
     *
     * @param article
     * @return
     */
    @RequestMapping(value = "saveArticle", method = RequestMethod.POST)
    public String homeArticles(@RequestBody Article article) {
        String result = null;
        try {
            Map<String, Object> map = articleService.saveArticle(article);
            result = JsonUtils.toJson(map);
        } catch (Exception e) {
            log.error("保存文章失败,article={}", JsonUtils.toJson(article), e.getMessage());
        }
        return result;
    }
}

