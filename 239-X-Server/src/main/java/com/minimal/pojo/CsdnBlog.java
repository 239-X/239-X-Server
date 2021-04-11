package com.minimal.pojo;

/**
 * csdn博客pojo
 * @author linzhiqiang
 * @date 2019/12/8
 */
public class CsdnBlog {

    /**
     * 编号
     */
    private int id;

    /**
     * 标题
     */
    private String title;

    /**
     * 日期
     */
    private String date;

    /**
     * 分类
     */
    private String category;

    /**
     * 阅读人数
     */
    private int view;

    /**
     * 评论人数
     */
    private int comments;

    /**
     * 是否原创
     */
    private String copyright;

    public CsdnBlog id(int id){
        this.id = id;
        return this;
    }
    public CsdnBlog date(String date){
        this.date = date;
        return this;
    }
    public CsdnBlog title(String title){
        this.title = title;
        return this;
    }
    public CsdnBlog category(String category){
        this.category = category;
        return this;
    }
    public CsdnBlog view(int view){
        this.view = view;
        return this;
    }
    public CsdnBlog comments(int comments){
        this.comments = comments;
        return this;
    }
    public CsdnBlog copyright(String copyright){
        this.copyright = copyright;
        return this;
    }

    @Override
    public String toString() {
        return "CsdnBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", view=" + view +
                ", comments=" + comments +
                ", copyright='" + copyright + '\'' +
                '}';
    }
}