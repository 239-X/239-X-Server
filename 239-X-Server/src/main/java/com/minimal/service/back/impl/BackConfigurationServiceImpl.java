package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.Config;
import com.minimal.mapper.back.BackConfigMapper;
import com.minimal.service.back.BackConfigurationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置服务
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
@Service
@Log4j2
public class BackConfigurationServiceImpl implements BackConfigurationService {
    @Autowired
    private BackConfigMapper backConfigMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(Config config) {
        config.setId(idGenerator.nextId() + "");
        int savaNumber = backConfigMapper.insert(config);
        return savaNumber;
    }

    @Override
    public int update(Config config) {
        int updateNumber = backConfigMapper.updateByPrimaryKey(config);
        return updateNumber;
    }

    @Override
    public int delete(String id) {
        int deleteNumber = backConfigMapper.deleteByPrimaryKey(id);
        return deleteNumber;
    }

    @Override
    public Config detail(String id) {
        return backConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Config> select(ConfigurationDto configDto) {
        Config config = new Config();
        // 复制对象
        BeanUtils.copyProps(configDto, config);
        int pageNo = configDto.getPageNo();
        int pageSize = configDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Config> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backConfigMapper.select(config);
                    }
                });
        return pageInfo;
    }
}