package com.minimal.back;

import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.entity.model.Config;
import com.minimal.service.back.BackConfigurationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConfiguration {
    @Autowired
    private BackConfigurationService backConfigurationService;

    @Test
    public void save() {
        Config config = new Config();
        config.setConfigKey("test");
        config.setConfigValue("test");
        config.setIsDelete(0);
        config.setCreateTime(new Date());
        config.setUpdateTime(new Date());
        config.setVersion(1);
        backConfigurationService.save(config);
    }

    @Test
    public void delete() {
        String id = "1084105426338840576";
        backConfigurationService.delete(id);
    }

    @Test
    public void update() {
        Config config = new Config();
        config.setConfigKey("test-test");
        config.setConfigValue("test-test");
        config.setIsDelete(1);
        config.setCreateTime(new Date());
        config.setUpdateTime(new Date());
        config.setVersion(2);
        config.setId("1084105426338840576");
        backConfigurationService.update(config);
    }

    @Test
    public void detail() {
        String id = "1";
        System.out.println(JsonUtils.toJson(backConfigurationService.detail(id)));
    }

    @Test
    public void select() {
        ConfigurationDto configDto = new ConfigurationDto();
        configDto.setPageNo(1);
        configDto.setPageSize(5);
        configDto.setConfigKey("isOpenVideo");
        System.out.println(JsonUtils.toJson(backConfigurationService.select(configDto)));
    }
}
