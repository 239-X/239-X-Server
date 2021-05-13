package com.minimal.service.wechat.impl;

import com.minimal.entity.model.Banner;
import com.minimal.mapper.wechat.BannerMapper;
import com.minimal.service.wechat.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/22
 */

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Map<String, Object> selectBanners() {
        Map<String, Object> result = new HashMap();
        Example example = new Example(Banner.class);
        example.setOrderByClause("sort DESC");
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("isEnable", 1);
        List<Banner> bannerList = bannerMapper.selectByExample(example);
        result.put("banner",bannerList);
        return result;
    }
}
