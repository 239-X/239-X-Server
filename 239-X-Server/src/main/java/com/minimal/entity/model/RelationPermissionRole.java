package com.minimal.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "relation_permission_role")
public class RelationPermissionRole {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "role_id")
    private Long roleId;

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