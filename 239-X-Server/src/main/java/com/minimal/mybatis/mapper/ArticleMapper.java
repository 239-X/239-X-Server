package com.minimal.mybatis.mapper;

import com.minimal.common.api.dto.ArticleDto;
import com.minimal.entity.model.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author linzhiqiang
 */
public interface ArticleMapper extends Mapper<Article> {
    /**
     * 获取按照评论排序的文章（倒叙）
     * @return
     */
    List<Article> selectHotArticleOrderByCommentNum();

    /**
     * 获取按照点赞排序的文章（倒叙）
     * @return
     */
    List<Article> selectHotArticleOrderByLikeNum();

    /**
     * 获取热门文章排行数据
     * @return
     */
    List<ArticleDto> selectHotArticleOrderByTypeId();
    /**
     * 获取按照点赞排序的文章（倒叙）
     * @return
     */
    List<Article> selectHomeArticles();

    /**
     * 查询createTime之前的第一篇文章
     * @param createTime
     * @return
     */
    Map selectPreviousArticleByCreateTime(@Param(value="createTime") Timestamp createTime);


    /**
     * 查询createTime之后的第一篇文章
     * @param createTime
     * @return
     */
    Map selectNextArticleByCreateTime(@Param(value="createTime") Timestamp createTime);

    /**
     * 通过用户id查询文章详情
     * @param articleId
     * @return
     */
    Article selectByArticleId(@Param(value="articleId") String articleId);

    /**
     * 获取按照点赞排序的文章（倒叙）
     * @param key
     * @return
     */
    List<Article> selectSearchBykey(@Param(value="key") String key);
}