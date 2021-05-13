package com.minimal.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "relation_operation_user_role")
public class RelationOperationUserRole {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "operation_user_id")
    private Long operationUserId;

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