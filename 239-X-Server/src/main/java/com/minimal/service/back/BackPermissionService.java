package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.PermissionDto;
import com.minimal.common.api.dto.PermissionDto;
import com.minimal.entity.model.Permission;

/**
 * 后端权限接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackPermissionService {
    /**
     * 保持对象
     *
     * @param permissionDto
     * @return
     */
    int save(PermissionDto permissionDto);

    /**
     * 修改对象
     *
     * @param permissionDto
     * @return
     */
    int update(PermissionDto permissionDto);

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 获取对象详情
     *
     * @param id
     * @return
     */
    Permission detail(long id);

    /**
     * 分页查询对象
     *
     * @param permissionDto
     * @return
     */
    PageInfo<Permission> select(PermissionDto permissionDto);
}
