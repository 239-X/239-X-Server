package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.back.BackArticleDto;
import com.minimal.entity.model.Article;

/**
 * @author linzhiqiang
 * @date 2019/3/12
 */
public interface BackArticleService {
    /**
     * 保持对象
     *
     * @param backArticleDto
     * @return
     */
    int save(BackArticleDto backArticleDto);

    /**
     * 修改对象
     *
     * @param backArticleDto
     * @return
     */
    int update(BackArticleDto backArticleDto);

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
    BackArticleDto detail(String id);

    /**
     * 分页查询对象
     *
     * @param backArticleDto
     * @return
     */
    PageInfo<Article> select(BackArticleDto backArticleDto);
}
