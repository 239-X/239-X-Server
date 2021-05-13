package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.OperationUserDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.OperationUser;
import com.minimal.entity.model.RelationOperationUserRole;
import com.minimal.entity.model.Role;
import com.minimal.mapper.back.BackOperationUserMapper;
import com.minimal.mapper.back.BackRelationOperationUserRoleMapper;
import com.minimal.mapper.back.BackRoleMapper;
import com.minimal.service.back.BackManageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后端管理员实现类
 *
 * @author linzhiqinag
 * @date 2021-05-09
 */
@Service
@Log4j2
public class BackManageServiceImpl implements BackManageService {
    @Autowired
    private BackOperationUserMapper backOperationUserMapper;

    @Autowired
    private BackRelationOperationUserRoleMapper backRelationOperationUserRoleMapper;

    @Autowired
    private BackRoleMapper backRoleMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(OperationUserDto operationUserDto) {
        OperationUser operationUser = operationUserDto.getOperationUser();
        operationUser.setId(idGenerator.nextId());
        int num = backOperationUserMapper.insert(operationUser);
        List<RelationOperationUserRole> relationOperationUserRoleList = operationUserDto.getRelationOperationUserRoleList();
        relationOperationUserRoleList.forEach(n -> {
            n.setOperationUserId(operationUser.getId());
            backRelationOperationUserRoleMapper.insert(n);
        });
        return num;
    }

    @Override
    public int update(OperationUserDto operationUserDto) {
        OperationUser operationUser = operationUserDto.getOperationUser();
        int num = backOperationUserMapper.updateByPrimaryKey(operationUser);
        List<RelationOperationUserRole> relationOperationUserRoleList = operationUserDto.getRelationOperationUserRoleList();
        relationOperationUserRoleList.forEach(n -> {
            backRelationOperationUserRoleMapper.updateByPrimaryKey(n);
        });
        return num;
    }

    @Override
    public int delete(long id) {
        int num = backOperationUserMapper.deleteByPrimaryKey(id);
        tk.mybatis.mapper.entity.Example example = new tk.mybatis.mapper.entity.Example(RelationOperationUserRole.class);
        example.createCriteria().andEqualTo("operationUserId", id);
        backRelationOperationUserRoleMapper.deleteByExample(example);
        return num;
    }

    @Override
    public OperationUserDto detail(long id) {
        OperationUserDto operationUserDto = new OperationUserDto();
        OperationUser operationUser = backOperationUserMapper.selectByPrimaryKey(id);
        operationUserDto.setOperationUser(operationUser);
        // 查询管理原对应角色信息
        List<Role> roleList = backRoleMapper.selectByOperationUserId(id);
        operationUserDto.setRoleList(roleList);
        return operationUserDto;
    }

    @Override
    public PageInfo<OperationUser> select(OperationUserDto operationUserDto) {
        OperationUser operationUser = new OperationUser();
        OperationUser operationUserQuery = operationUserDto.getOperationUser();
        // 复制对象
        BeanUtils.copyProps(operationUserQuery, operationUser);
        int pageNo = operationUserDto.getPageNo();
        int pageSize = operationUserDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<OperationUser> operationUserPageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backOperationUserMapper.select(operationUser);
                    }
                });
        return operationUserPageInfo;
    }
}