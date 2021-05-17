package com.minimal.service.front;

import com.minimal.common.api.dto.front.FrontArticleDto;
import com.minimal.entity.model.ArticleComment;

/**
 * 前端文章服务
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface FrontArticleService {
    /**
     * 查询文章列表信息
     * @param frontArticleDto
     * @return
     */
    Object select(FrontArticleDto frontArticleDto);

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    Object detail(String id);

    /**
     * 搜索文章标题
     * @param key
     * @return
     */
    Object search(String key);
}
