package com.minimal.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

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