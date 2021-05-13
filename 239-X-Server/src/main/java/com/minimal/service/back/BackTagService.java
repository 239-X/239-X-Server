package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleTagDto;
import com.minimal.entity.model.ArticleTag;

/**
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackTagService {
    /**
     * 保持对象
     *
     * @param articleTag
     * @return
     */
    int save(ArticleTag articleTag);

    /**
     * 修改对象
     *
     * @param articleTag
     * @return
     */
    int update(ArticleTag articleTag);

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
    ArticleTag detail(String id);

    /**
     * 分页查询对象
     *
     * @param articleTagDto
     * @return
     */
    PageInfo<ArticleTag> select(ArticleTagDto articleTagDto);
}