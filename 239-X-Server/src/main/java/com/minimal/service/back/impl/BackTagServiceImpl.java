package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleTagDto;
import com.minimal.common.api.dto.ConfigurationDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.entity.model.ArticleTag;
import com.minimal.entity.model.Category;
import com.minimal.mapper.back.BackTagMapper;
import com.minimal.service.back.BackTagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
@Service
@Log4j2
public class BackTagServiceImpl implements BackTagService {
    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private BackTagMapper backTagMapper;

    @Override
    public int save(ArticleTag articleTag) {
        articleTag.setId(idGenerator.nextId() + "");
        return backTagMapper.insert(articleTag);
    }

    @Override
    public int update(ArticleTag articleTag) {
        return backTagMapper.updateByPrimaryKey(articleTag);
    }

    @Override
    public int delete(String id) {
        return backTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ArticleTag detail(String id) {
        return backTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ArticleTag> select(ArticleTagDto articleTagDto) {
        ArticleTag articleTag = new ArticleTag();
        // 复制对象
        BeanUtils.copyProps(articleTagDto, articleTag);
        int pageNo = articleTagDto.getPageNo();
        int pageSize = articleTagDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<ArticleTag> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backTagMapper.select(articleTag);
                    }
                });
        return pageInfo;
    }
}