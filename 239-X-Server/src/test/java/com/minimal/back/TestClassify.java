package com.minimal.back;

import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Category;
import com.minimal.entity.model.Config;
import com.minimal.service.back.BackClassifyService;
import com.minimal.service.back.BackConfigurationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClassify {
    @Autowired
    private BackClassifyService backClassifyService;

    @Test
    public void save() {
        Category category = new Category();
        category.setIsStart(1);
        category.setName("test");
        category.setParentId("test");
        category.setSketch("test");
        category.setPriority(1);
        category.setIsDelete(0);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        category.setVersion(1);
        backClassifyService.save(category);
    }

    @Test
    public void delete() {
        String id = "1084109368061853696";
        backClassifyService.delete(id);
    }

    @Test
    public void update() {
        Category category = new Category();
        category.setIsStart(1);
        category.setName("test-222222");
        category.setParentId("test-2222222");
        category.setSketch("test");
        category.setPriority(1);
        category.setIsDelete(0);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        category.setVersion(1);
        category.setId("1084109368061853696");
        backClassifyService.update(category);
    }

    @Test
    public void detail() {
        String id = "1";
        System.out.println(JsonUtils.toJson(backClassifyService.detail(id)));
    }

    @Test
    public void select() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setPageNo(1);
        categoryDto.setPageSize(5);
        categoryDto.setName("计算机");
        System.out.println(JsonUtils.toJson(backClassifyService.select(categoryDto)));
    }
}
