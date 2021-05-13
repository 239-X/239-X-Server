package com.minimal.mapper.back;

import com.minimal.entity.model.Permission;
import feign.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author linzhiqiang
 */
public interface BackPermissionMapper extends Mapper<Permission> {
    /**
     * 查询管理员用户所有权限信息
     *
     * @param operationUserId
     * @return
     */
    List<Permission> selectByOperationUserId(@Param("operationUserId") long operationUserId);

    /**
     * 查询角色所有权限信息
     *
     * @param ruleId
     * @return
     */
    List<Permission> selectByRuleId(@Param("ruleId") long ruleId);
}