package com.minimal.service.front.impl;

import com.minimal.entity.model.Menu;
import com.minimal.mapper.front.FrontMenuMapper;
import com.minimal.service.front.FrontMenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linzhiqiang
 */
@Service
@Log4j2
public class FrontMenuServiceImpl implements FrontMenuService {
    @Autowired
    private FrontMenuMapper frontMenuMapper;

    @Override
    public List<Menu> detail() {
        tk.mybatis.mapper.entity.Example menuExample = new tk.mybatis.mapper.entity.Example(Menu.class);
        menuExample.createCriteria().andEqualTo("is_launch", true);
        menuExample.setOrderByClause("priority DESC,create_time DESC");
        List<Menu> menuList = frontMenuMapper.selectByExample(menuExample);
        return menuList;
    }
}