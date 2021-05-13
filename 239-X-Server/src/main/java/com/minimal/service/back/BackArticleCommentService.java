package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.back.BackArticleCommentDto;
import com.minimal.entity.model.ArticleComment;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackArticleCommentService {
    /**
     * 保持对象
     *
     * @param backArticleCommentDto
     * @return
     */
    int save(BackArticleCommentDto backArticleCommentDto);

    /**
     * 修改对象
     *
     * @param backArticleCommentDto
     * @return
     */
    int update(BackArticleCommentDto backArticleCommentDto);

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取对象详情
     *
     * @param id
     * @return
     */
    BackArticleCommentDto detail(String id);

    /**
     * 分页查询对象
     *
     * @param backArticleCommentDto
     * @return
     */
    PageInfo<ArticleComment> select(BackArticleCommentDto backArticleCommentDto);
}
