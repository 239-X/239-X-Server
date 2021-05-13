package com.minimal.common.api.dto.back;

import com.minimal.common.sdk.TablePage;
import com.minimal.entity.model.ArticleComment;
import lombok.Data;

/**
 * @author linzhiqiang
 * @date 2019/3/20
 */
@Data
public class BackArticleCommentDto extends TablePage {
    /**
     * 文章评论
     */
    private ArticleComment articleComment;
}
