package com.minimal.common.api.dto;

/**
 * @author linzhiqiang
 * @date 2019/3/20
 */
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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
