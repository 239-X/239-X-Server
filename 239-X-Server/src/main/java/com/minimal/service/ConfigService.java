package com.minimal.service;
import com.minimal.common.sdk.Page;
import com.minimal.entity.model.Config;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/7
 */
public interface  ConfigService {
    /**
     * 获取去全部配置信息
     * @return
     */
    Map<String,Object> selectAll();

    /**
     * 通过配置id查询
     * @param id
     * @return
     */
    Config select(String id);

    /**
     * 获取指定key的配置值
     *
     * @param key
     * @return
     */
    Config selectConfigByKey(String key);
}
