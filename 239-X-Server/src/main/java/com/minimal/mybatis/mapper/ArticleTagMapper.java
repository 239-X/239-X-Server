package com.minimal.mybatis.mapper;

import com.minimal.entity.model.ArticleTag;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author linzhiqiang
 */
public interface ArticleTagMapper extends Mapper<ArticleTag> {
    /**
     * 通过articleId获取文章的所有对应标签
     * @param articleId
     * @return
     */
    List<ArticleTag> selectTagsByArticleId(String articleId);
}