package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.RoleDto;
import com.minimal.entity.model.Role;

/**
 * 后端角色接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackRoleService {
    /**
     * 保持对象
     *
     * @param roleDto
     * @return
     */
    int save(RoleDto roleDto);

    /**
     * 修改对象
     *
     * @param roleDto
     * @return
     */
    int update(RoleDto roleDto);

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
    RoleDto detail(long id);

    /**
     * 分页查询对象
     *
     * @param roleDto
     * @return
     */
    PageInfo<Role> select(RoleDto roleDto);
}
