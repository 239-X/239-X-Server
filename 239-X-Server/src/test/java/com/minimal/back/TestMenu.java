package com.minimal.back;

import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.api.dto.MenuDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Category;
import com.minimal.entity.model.Menu;
import com.minimal.service.back.BackMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMenu {
    @Autowired
    private BackMenuService backMenuService;

    @Test
    public void save() {
        Menu menu = new Menu();
        menu.setPriority(1);
        menu.setName("test");
        menu.setIsLaunch(true);
        menu.setPriority(1);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setSkipType(1);
        menu.setLayer(1);
        menu.setSkipLocation("test");
        backMenuService.save(menu);
    }

    @Test
    public void delete() {
        backMenuService.delete(1);
    }

    @Test
    public void update() {
        Menu menu = new Menu();
        menu.setPriority(2);
        menu.setName("test------test");
        menu.setIsLaunch(true);
        menu.setPriority(2);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setSkipType(2);
        menu.setLayer(2);
        menu.setSkipLocation("test---test");
        menu.setId(1);
        backMenuService.update(menu);
    }

    @Test
    public void detail() {
        String id = "1";
        System.out.println(JsonUtils.toJson(backMenuService.detail(id)));
    }

    @Test
    public void select() {
        MenuDto menuDto = new MenuDto();
        menuDto.setPageNo(1);
        menuDto.setPageSize(5);
        System.out.println(JsonUtils.toJson(backMenuService.select(menuDto)));
    }
}
