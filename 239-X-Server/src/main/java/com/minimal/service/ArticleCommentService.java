package com.minimal.service;

import com.minimal.common.api.dto.ArticleCommentDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/20
 */

public interface ArticleCommentService {
    /**
     * 通过articleId获取该文章所有评论数据
     * @param articleId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> selectArticleCommentsByArticleId(String  articleId,int pageNo, int pageSize);

    /**
     * 插入文章的评论数据
     * @param articleCommentDto
     * @return
     */
    public Map<String, Object> insertArticleComment(ArticleCommentDto articleCommentDto);
}
