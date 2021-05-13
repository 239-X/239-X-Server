package com.minimal.service.wechat;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/27
 */
public interface HomeModuleService {

    /**
     * 查询所有的功能列表数据
     * @return
     */
    Map<String, Object> selectAllHomeModule();
}
