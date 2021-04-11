package com.minimal.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author linzhiqiang
 */
@Table(name = "article_subject_relation")
public class ArticleSubjectRelation {
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
     * 专题id
     */
    @Column(name = "article_subject_id")
    private String articleSubjectId;

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

    public String getArticleSubjectId() {
        return articleSubjectId;
    }

    public void setArticleSubjectId(String articleSubjectId) {
        this.articleSubjectId = articleSubjectId;
    }
}