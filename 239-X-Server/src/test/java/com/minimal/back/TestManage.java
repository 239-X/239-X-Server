package com.minimal.back;

import com.minimal.common.api.dto.OperationUserDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.back.BackManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestManage {
    @Autowired
    private BackManageService backManageService;

    @Test
    public void save() {
        OperationUserDto operationUserDto = new OperationUserDto();
        backManageService.save(operationUserDto);
    }

    @Test
    public void delete() {
        backManageService.delete(111);
    }

    @Test
    public void update() {
        OperationUserDto operationUserDto = new OperationUserDto();
        backManageService.update(operationUserDto);
    }

    @Test
    public void detail() {
        System.out.println(JsonUtils.toJson(backManageService.detail(1)));
    }

    @Test
    public void select() {
        OperationUserDto operationUserDto = new OperationUserDto();
        operationUserDto.setPageNo(1);
        operationUserDto.setPageSize(5);
        System.out.println(JsonUtils.toJson(backManageService.select(operationUserDto)));
    }
}
