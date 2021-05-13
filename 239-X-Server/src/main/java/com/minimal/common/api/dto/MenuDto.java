package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class MenuDto extends TablePage {
    private Integer id;

    @Column(name = "name")
    private String name;

    private Integer priority;

    private Integer layer;

    private Boolean isLaunch;

    private Integer skipType;

    private String skipLocation;

    private Date createTime;

    private Date updateTime;
}