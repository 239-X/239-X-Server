package com.minimal.common.api.dto.back;

import com.minimal.common.sdk.TablePage;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.ArticleComment;
import com.minimal.entity.model.ArticleTag;
import com.minimal.entity.model.Category;
import lombok.Data;

import java.util.List;

/**
 * 后台文章dto
 *
 * @author linzhiqiang
 * @date 2019/3/27
 */
@Data
public class BackArticleDto extends TablePage {
    /**
     * 文章详情
     */
    private Article article;

    /**
     * 评论信息列表
     */
    List<ArticleComment> articleComment;

    /**
     * 分类信息列表
     */
    List<Category> categoryList;

    /**
     * 文章标签信息列表
     */
    List<ArticleTag> articleTagList;
}
