package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.back.BackArticleDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.entity.model.Article;
import com.minimal.mapper.back.BackArticleMapper;
import com.minimal.service.back.BackArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linzhiqiang
 * @date 2019/3/12
 */
@Service
public class BackArticleServiceImpl implements BackArticleService {
    @Autowired
    private BackArticleMapper backArticleMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(BackArticleDto backArticleDto) {
        Article article = backArticleDto.getArticle();
        article.setId(idGenerator.nextId() + "");
        return backArticleMapper.insert(article);
    }

    @Override
    public int update(BackArticleDto backArticleDto) {
        Article article = backArticleDto.getArticle();
        return backArticleMapper.updateByPrimaryKey(article);
    }

    @Override
    public int delete(String id) {
        return backArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BackArticleDto detail(String id) {
        // 文章详情、评论详情

        return null;
    }

    @Override
    public PageInfo<Article> select(BackArticleDto backArticleDto) {
        Article article = backArticleDto.getArticle();
        int pageNo = backArticleDto.getPageNo();
        int pageSize = backArticleDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Article> articlePageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backArticleMapper.select(article);
                    }
                });
        return articlePageInfo;
    }
}
