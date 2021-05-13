package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.PermissionDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.Permission;
import com.minimal.mapper.back.BackPermissionMapper;
import com.minimal.service.back.BackPermissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后端权限服务
 *
 * @author linzhiqinag
 * @date 2021-05-09
 */
@Service
@Log4j2
public class BackPermissionServiceImpl implements BackPermissionService {
    @Autowired
    private BackPermissionMapper backPermissionMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(PermissionDto permissionDto) {
        Permission permission = new Permission();
        BeanUtils.copyProps(permissionDto, permission);
        permission.setId(idGenerator.nextId());
        return backPermissionMapper.insert(permission);
    }

    @Override
    public int update(PermissionDto permissionDto) {
        Permission permission = new Permission();
        BeanUtils.copyProps(permissionDto, permission);
        return backPermissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public int delete(long id) {
        return backPermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Permission detail(long id) {
        return backPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Permission> select(PermissionDto permissionDto) {
        Permission permission = new Permission();
        // 复制对象
        BeanUtils.copyProps(permissionDto, permission);
        int pageNo = permissionDto.getPageNo();
        int pageSize = permissionDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Permission> permissionPageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backPermissionMapper.select(permission);
                    }
                });
        return permissionPageInfo;
    }
}