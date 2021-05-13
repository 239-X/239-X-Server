package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.entity.model.Config;

/**
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackConfigurationService {
    /**
     * 保持对象
     * @param config
     * @return
     */
    int save(Config config);

    /**
     * 修改对象
     * @param config
     * @return
     */
    int update(Config config);

    /**
     * 删除对象
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取对象详情
     * @param id
     * @return
     */
    Config detail(String id);

    /**
     * 分页查询对象
     * @param configDto
     * @return
     */
    PageInfo<Config> select(ConfigurationDto configDto);
}