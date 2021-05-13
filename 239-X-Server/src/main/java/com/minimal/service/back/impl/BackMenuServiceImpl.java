package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.MenuDto;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.Menu;
import com.minimal.mapper.back.BackMenuMapper;
import com.minimal.service.back.BackMenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linzhiqiang
 */
@Service
@Log4j2
public class BackMenuServiceImpl implements BackMenuService {
    @Autowired
    private BackMenuMapper backMenuMapper;

    @Override
    public int save(Menu menu) {
        int savaNumber = backMenuMapper.insert(menu);
        return savaNumber;
    }

    @Override
    public int update(Menu menu) {
        return backMenuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public int delete(int id) {
        return backMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Menu detail(String id) {
        return backMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Menu> select(MenuDto menuDto) {
        Menu menu = new Menu();
        // 复制对象
        BeanUtils.copyProps(menuDto, menu);
        int pageNo = menuDto.getPageNo();
        int pageSize = menuDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Menu> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backMenuMapper.select(menu);
                    }
                });
        return pageInfo;
    }
}