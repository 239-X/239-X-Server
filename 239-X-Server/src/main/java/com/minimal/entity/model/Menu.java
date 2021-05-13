package com.minimal.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "layer")
    private Integer layer;

    @Column(name = "is_launch")
    private Boolean isLaunch;

    @Column(name = "skip_type")
    private Integer skipType;

    @Column(name = "skip_location")
    private String skipLocation;

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