package com.minimal.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "infobar_detail")
public class InfoBarDetail {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "infobar_id")
    private Long infoBarId;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "classify_id")
    private Integer classifyId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "url_pic")
    private String urlPic;

    @Column(name = "url_address")
    private String urlAddress;

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
}