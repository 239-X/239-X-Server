package com.minimal.back;

import com.minimal.common.api.dto.ArticleTagDto;
import com.minimal.common.api.dto.PermissionDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.ArticleTag;
import com.minimal.service.back.BackPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPermission {
    @Autowired
    private BackPermissionService backPermissionService;

    @Test
    public void save() {
        PermissionDto permissionDto = new PermissionDto();
        backPermissionService.save(permissionDto);
    }

    @Test
    public void delete() {
        backPermissionService.delete(1);
    }

    @Test
    public void update() {
        PermissionDto permissionDto = new PermissionDto();
        backPermissionService.update(permissionDto);
    }

    @Test
    public void detail() {
        System.out.println(JsonUtils.toJson(backPermissionService.detail(1)));
    }

    @Test
    public void select() {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setPageNo(1);
        permissionDto.setPageSize(5);
        System.out.println(JsonUtils.toJson(backPermissionService.select(permissionDto)));
    }
}
