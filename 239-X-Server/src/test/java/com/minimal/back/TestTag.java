package com.minimal.back;

import com.minimal.common.api.dto.ArticleTagDto;
import com.minimal.common.api.dto.CategoryDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.ArticleTag;
import com.minimal.entity.model.Category;
import com.minimal.service.back.BackClassifyService;
import com.minimal.service.back.BackTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTag {
    @Autowired
    private BackTagService backTagService;

    @Test
    public void save() {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleTypeId("11111");
        articleTag.setTagLevel(1);
        articleTag.setTagName("test");
        articleTag.setIsDelete(0);
        articleTag.setCreateTime(new Date());
        articleTag.setUpdateTime(new Date());
        articleTag.setVersion(1);
        backTagService.save(articleTag);
    }

    @Test
    public void delete() {
        String id = "1084112867017359360";
        backTagService.delete(id);
    }

    @Test
    public void update() {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleTypeId("222222");
        articleTag.setTagLevel(1);
        articleTag.setTagName("test-1111111");
        articleTag.setIsDelete(0);
        articleTag.setCreateTime(new Date());
        articleTag.setUpdateTime(new Date());
        articleTag.setVersion(1);
        articleTag.setId("1084112867017359360");
        backTagService.update(articleTag);
    }

    @Test
    public void detail() {
        String id = "1";
        System.out.println(JsonUtils.toJson(backTagService.detail(id)));
    }

    @Test
    public void select() {
        ArticleTagDto articleTagDto = new ArticleTagDto();
        articleTagDto.setPageNo(1);
        articleTagDto.setPageSize(5);
        System.out.println(JsonUtils.toJson(backTagService.select(articleTagDto)));
    }
}
