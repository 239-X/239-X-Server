package com.minimal.service.wechat;
import com.minimal.entity.model.Article;

import java.util.List;
import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/12
 */
public interface ArticleService {
    /**
     * 获取首页文章列表数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    Map<String, Object> selectAll(int pageNo, int pageSize);

    /**
     * 保存文章
     * @param article
     * @return
     */
    Map<String, Object> saveArticle(Article article);

    /**
     * 通过文章id获取文章详情页数据
     * @param articleId
     * @param subjectId
     * @return
     */
    Map<String, Object> selectById(String articleId ,String subjectId);

    /**
     * 通过文章关键字搜索文章列表
     *
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    Map<String, Object> selectByKey(String key,int pageNo, int pageSize);

    /**
     * 获取热门文章
     * @param typeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Map<String,Object> selectHotArticleByTypeId(String typeId, int pageNo, int pageSize);

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    Map<String,Object> deleteArticle(String articleId);
}
