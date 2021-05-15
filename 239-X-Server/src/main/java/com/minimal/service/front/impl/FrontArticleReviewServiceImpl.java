package com.minimal.service.front.impl;

import com.minimal.entity.model.ArticleComment;
import com.minimal.mapper.front.FrontArticleReviewMapper;
import com.minimal.service.front.FrontArticleReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前端文章评论模块
 *
 * @author linzhiqiang
 * @date 2021-05-15
 */
@Service
@Log4j2
public class FrontArticleReviewServiceImpl implements FrontArticleReviewService {
    @Autowired
    private FrontArticleReviewMapper frontArticleReviewMapper;

    @Override
    public void detail(String id) {

    }

    @Override
    public int saveArticleReview(ArticleComment articleComment) {
        return frontArticleReviewMapper.insert(articleComment);
    }
}