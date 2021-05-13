package com.minimal.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "infobar")
public class InfoBar {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "is_launch")
    private Boolean isLaunch;

    @Column(name = "content")
    private String content;

    @Column(name = "url")
    private String url;

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