package com.minimal.common.api.dto.back;

import com.minimal.common.sdk.TablePage;
import com.minimal.entity.model.Article;
import lombok.Data;

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
}
