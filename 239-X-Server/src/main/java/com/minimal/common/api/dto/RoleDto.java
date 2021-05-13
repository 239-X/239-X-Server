package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import com.minimal.entity.model.Permission;
import com.minimal.entity.model.RelationPermissionRole;
import com.minimal.entity.model.Role;
import lombok.Data;

import java.util.List;

/**
 * 角色dto
 *
 * @author linzhiqiang
 */
@Data
public class RoleDto extends TablePage {
    /**
     * 角色信息
     */
    private Role role;

    /**
     * 权限列表
     */
    private List<Permission> permissionList;

    /**
     * 角色权限列表
     */
    private List<RelationPermissionRole> relationPermissionRoleList;
}