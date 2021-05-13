package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.RoleDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.Permission;
import com.minimal.entity.model.RelationPermissionRole;
import com.minimal.entity.model.Role;
import com.minimal.mapper.back.BackPermissionMapper;
import com.minimal.mapper.back.BackRelationPermissionRoleMapper;
import com.minimal.mapper.back.BackRoleMapper;
import com.minimal.service.back.BackRoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后端角色服务
 *
 * @author linzhiqinag
 * @date 2021-05-09
 */
@Service
@Log4j2
public class BackRoleServiceImpl implements BackRoleService {
    @Autowired
    private BackRoleMapper backRoleMapper;

    @Autowired
    private BackPermissionMapper backPermissionMapper;

    @Autowired
    private BackRelationPermissionRoleMapper backRelationPermissionRoleMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(RoleDto roleDto) {
        List<RelationPermissionRole> relationPermissionRoleList = roleDto.getRelationPermissionRoleList();
        Role role = roleDto.getRole();
        role.setId(idGenerator.nextId());
        int num = backRoleMapper.insert(role);
        relationPermissionRoleList.forEach(n -> {
            n.setRoleId(role.getId());
            backRelationPermissionRoleMapper.updateByPrimaryKey(n);
        });
        return num;
    }

    @Override
    public int update(RoleDto roleDto) {
        List<RelationPermissionRole> relationPermissionRoleList = roleDto.getRelationPermissionRoleList();
        Role role = roleDto.getRole();
        int num = backRoleMapper.updateByPrimaryKey(role);
        relationPermissionRoleList.forEach(n -> {
            backRelationPermissionRoleMapper.updateByPrimaryKey(n);
        });
        return num;
    }

    @Override
    public int delete(long id) {
        int num = backRoleMapper.deleteByExample(id);
        tk.mybatis.mapper.entity.Example example = new tk.mybatis.mapper.entity.Example(RelationPermissionRole.class);
        example.createCriteria().andEqualTo("role_id", id);
        backRelationPermissionRoleMapper.deleteByExample(example);
        return num;
    }

    @Override
    public RoleDto detail(long id) {
        RoleDto roleDto = new RoleDto();
        Role role = backRoleMapper.selectByPrimaryKey(id);
        roleDto.setRole(role);
        List<Permission> permissionList = backPermissionMapper.selectByRuleId(id);
        roleDto.setPermissionList(permissionList);
        return roleDto;
    }

    @Override
    public PageInfo<Role> select(RoleDto roleDto) {
        Role role = roleDto.getRole();
        // 复制对象
        BeanUtils.copyProps(roleDto, role);
        int pageNo = roleDto.getPageNo();
        int pageSize = roleDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Role> rolePageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backRoleMapper.select(role);
                    }
                });
        return rolePageInfo;
    }
}