package com.minimal.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author linzhiqiang
 */
@Table(name = "article")
public class Article {
    /**
     * ID标识
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 文章简要描述
     */
    @Column(name = "sketch")
    private String sketch;

    /**
     * 文章背景图
     */
    @Column(name = "back_img")
    private String backImg;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 分类id
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * 点赞数目
     */
    @Column(name = "like_num")
    private Integer likeNum;

    /**
     * 收藏数目
     */
    @Column(name = "collect_num")
    private Integer collectNum;

    /**
     * 分享数目
     */
    @Column(name = "share_num")
    private Integer shareNum;

    /**
     * 阅读数目
     */
    @Column(name = "read_num")
    private Integer readNum;

    /**
     * 评论数目
     */
    @Column(name = "comment_num")
    private Integer commentNum;

    /**
     * 排序值
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 是否为系统推荐文章
     */
    @Column(name = "is_sys_recommend")
    private Integer isSysRecommend;

    /**
     * 是否为专题文章
     */
    @Column(name = "is_subject")
    private Integer isSubject;

    /**
     * 是否原创
     */
    @Column(name = "is_original")
    private Integer isOriginal;

    /**
     * 作者用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 作者名名称
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Timestamp updateTime;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 原文地址
     */
    @Column(name = "link")
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsSysRecommend() {
        return isSysRecommend;
    }

    public void setIsSysRecommend(Integer isSysRecommend) {
        this.isSysRecommend = isSysRecommend;
    }

    public Integer getIsSubject() {
        return isSubject;
    }

    public void setIsSubject(Integer isSubject) {
        this.isSubject = isSubject;
    }

    public Integer getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Integer isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}