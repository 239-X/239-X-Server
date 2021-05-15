package com.minimal.service.front;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.back.BackArticleCommentDto;
import com.minimal.entity.model.ArticleComment;

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
    void detail(String id);

    /**
     * 保持文章评论
     * @param articleComment
     * @return
     */
    int saveArticleReview(ArticleComment articleComment);
}
