package com.minimal.service.wechat.impl;

import com.minimal.entity.model.HomeModule;
import com.minimal.mapper.wechat.HomeModuleMapper;
import com.minimal.service.wechat.HomeModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/27
 */

@Service
public class HomeModuleServiceImpl implements HomeModuleService {
    @Autowired
    private HomeModuleMapper homeModuleMapper;

    @Override
    public Map<String, Object> selectAllHomeModule() {
        Map<String, Object> result = new HashMap();
        Example example = new Example(HomeModule.class);
        example.setOrderByClause("sort DESC , update_time DESC");
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("isEnable", 1);
        List<HomeModule> homeModuleList = homeModuleMapper.selectByExample(example);
        result.put("data",homeModuleList);
        return result;
    }
}
