package com.minimal.service.front;

import com.minimal.entity.model.ArticleComment;

import java.util.List;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface FrontArticleReviewService {
    /**
     * 获取对象详情
     *
     * @param id
     * @return
     */
    List<ArticleComment> detail(String id);

    /**
     * 保持文章评论
     *
     * @param articleComment
     * @return
     */
    int saveArticleReview(ArticleComment articleComment);
}
