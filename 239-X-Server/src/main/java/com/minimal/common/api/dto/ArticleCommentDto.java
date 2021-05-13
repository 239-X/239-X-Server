package com.minimal.common.api.dto;

import lombok.Data;

/**
 * @author linzhiqiang
 * @date 2019/3/20
 */
@Data
public class ArticleCommentDto {
    /**
     * 文章id
     */
    private String articleId;
    /**
     * 评论人id
     */
    private String userId;
    /**
     * 评论父级id（如果是一级评论，id就是null的）
     */
    private String parentId;
    /**
     * 评论者名称
     */
    private String authorName;
    /**
     * 评论人头像url地址
     */
    private String authorUrl;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论id
     */
    private String id;


    /**
     * 微信openId
     */
    private String openId;
}
