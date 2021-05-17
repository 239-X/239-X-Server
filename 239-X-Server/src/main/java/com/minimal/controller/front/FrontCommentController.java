package com.minimal.controller.front;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.ArticleComment;
import com.minimal.service.front.FrontArticleReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台评论控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "front/comment", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class FrontCommentController {
    @Autowired
    private FrontArticleReviewService frontArticleReviewService;

    @RequestMapping(value = "articleReview", method = RequestMethod.POST)
    public String articleReview(String id) {
        String result = null;
        try {
            return JsonUtils.toJson(frontArticleReviewService.detail(id));
        } catch (Exception e) {
            log.error("获取文章评论失败,article={}", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "saveArticleReview", method = RequestMethod.POST)
    public String saveArticleReview(@RequestBody ArticleComment articleComment) {
        String result = null;
        try {
            return JsonUtils.toJson(frontArticleReviewService.saveArticleReview(articleComment));
        } catch (Exception e) {
            log.error("保存文章评论失败,article={}", e.getMessage());
        }
        return result;
    }
}

