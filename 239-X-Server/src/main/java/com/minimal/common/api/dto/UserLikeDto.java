package com.minimal.common.api.dto;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/20
 */
public class UserLikeDto {
    /**
     * 文章id
     */
    private String articleId;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 收藏类型 1代表取消收藏、2代表收藏
     */
    private String typeCode;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
