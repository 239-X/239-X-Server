package com.minimal.mapper.back;

import com.minimal.entity.model.InfoBarDetail;
import com.minimal.entity.model.Role;
import feign.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author linzhiqiang
 */
public interface BackRoleMapper extends Mapper<Role> {
    /**
     * 查询管理员用户所有角色信息
     * @param operationUserId
     * @return
     */
    List<Role> selectByOperationUserId(@Param("operationUserId") long operationUserId);
}