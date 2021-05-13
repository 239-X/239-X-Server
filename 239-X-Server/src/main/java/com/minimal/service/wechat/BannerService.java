package com.minimal.service.wechat;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/22
 */
public interface BannerService {

    /**
     * 查询banner所有数据
     * @return
     */
    Map<String,Object> selectBanners();
}
