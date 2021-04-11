package com.minimal.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.sdk.Page;
import com.minimal.common.sdk.annotation.RedisCacheResult;
import com.minimal.common.sdk.utils.PageCopier;
import com.minimal.entity.model.Config;
import com.minimal.mybatis.mapper.ConfigMapper;
import com.minimal.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linzhiqiang
 * @date 2019/3/7
 */

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @RedisCacheResult
    @Override
    public Map<String, Object> selectAll() {
        Config configQuery = new Config();
        configQuery.setIsDelete(0);
        List<Config> configList = configMapper.select(configQuery);
        Map<String, Object> result = new HashMap<>();
        for (Config config : configList) {
            if (config != null && config.getConfigKey() != null) {
                result.put(config.getConfigKey(), config.getConfigValue());
            }
        }
        return result;
    }

    @Override
    public Config select(String id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public Config selectConfigByKey(String key) {
        Config queryConfig = new Config();
        queryConfig.setIsDelete(0);
        queryConfig.setConfigKey("signCoin");
        return configMapper.selectOne(queryConfig);
    }
}
