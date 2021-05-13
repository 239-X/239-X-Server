package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import com.minimal.entity.model.OperationUser;
import com.minimal.entity.model.Permission;
import com.minimal.entity.model.RelationOperationUserRole;
import com.minimal.entity.model.Role;
import lombok.Data;

import java.util.List;

/**
 * 平台管理员dto
 *
 * @author linzhiqiang
 */
@Data
public class OperationUserDto extends TablePage {
    private OperationUser operationUser;
    /**
     * 管理员对应角色关系
     */
    private List<RelationOperationUserRole> relationOperationUserRoleList;

    /**
     * 管理员对应角色列表
     */
    private List<Role> roleList;

    /**
     * 管理员对应权限列表
     */
    private List<Permission> permissionList;
}