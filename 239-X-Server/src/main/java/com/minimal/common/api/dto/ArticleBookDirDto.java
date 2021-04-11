package com.minimal.common.api.dto;

import java.util.List;

/**
 * 书籍目录dto
 * @author linzhiqiang
 * @date 2019/5/6
 */
public class ArticleBookDirDto {
    /**
     * 分类id
     */
    private String id;

    /**
     * 分类名称
     */
    private String text;


    /**
     * 二级分类
     */
    private List<ArticleBookDirDto> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ArticleBookDirDto> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleBookDirDto> children) {
        this.children = children;
    }
}
