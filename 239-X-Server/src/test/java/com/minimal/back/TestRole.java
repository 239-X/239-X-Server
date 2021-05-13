package com.minimal.back;

import com.minimal.common.api.dto.RoleDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.back.BackRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRole {
    @Autowired
    private BackRoleService backRoleService;

    @Test
    public void save() {
        RoleDto roleDto = new RoleDto();
        backRoleService.save(roleDto);
    }

    @Test
    public void delete() {
        backRoleService.delete(1);
    }

    @Test
    public void update() {
        RoleDto roleDto = new RoleDto();
        backRoleService.update(roleDto);
    }

    @Test
    public void detail() {
        System.out.println(JsonUtils.toJson(backRoleService.detail(1)));
    }

    @Test
    public void select() {
        RoleDto roleDto = new RoleDto();
        roleDto.setPageNo(1);
        roleDto.setPageSize(5);
        System.out.println(JsonUtils.toJson(backRoleService.select(roleDto)));
    }
}
