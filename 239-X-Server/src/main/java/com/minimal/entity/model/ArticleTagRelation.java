package com.minimal.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author linzhiqiang
 */
@Table(name = "article_tag_relation")
public class ArticleTagRelation {
    /**
     * ID标识
     */
    @Id
    @Column(name = "id")
    private String id;


    /**
     * 文章id
     */
    @Column(name = "article_id")
    private String articleId;


    /**
     * 标签id
     */
    @Column(name = "tag_id")
    private String tagId;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
}