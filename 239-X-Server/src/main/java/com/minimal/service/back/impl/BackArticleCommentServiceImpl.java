package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.back.BackArticleCommentDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.ArticleComment;
import com.minimal.mapper.back.BackArticleCommentMapper;
import com.minimal.service.back.BackArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
@Service
public class BackArticleCommentServiceImpl implements BackArticleCommentService {
    @Autowired
    private BackArticleCommentMapper backArticleCommentMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int save(BackArticleCommentDto backArticleCommentDto) {
        ArticleComment articleComment = backArticleCommentDto.getArticleComment();
        articleComment.setId(idGenerator.nextId() + "");
        return backArticleCommentMapper.insert(articleComment);
    }

    @Override
    public int update(BackArticleCommentDto backArticleCommentDto) {
        ArticleComment articleComment = backArticleCommentDto.getArticleComment();
        articleComment.setId(idGenerator.nextId() + "");
        return backArticleCommentMapper.updateByPrimaryKey(articleComment);
    }

    @Override
    public int delete(String id) {
        return backArticleCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BackArticleCommentDto detail(String id) {
        BackArticleCommentDto backArticleCommentDto = new BackArticleCommentDto();
        ArticleComment articleComment = backArticleCommentMapper.selectByPrimaryKey(id);
        backArticleCommentDto.setArticleComment(articleComment);
        return backArticleCommentDto;
    }

    @Override
    public PageInfo<ArticleComment> select(BackArticleCommentDto backArticleCommentDto) {
        ArticleComment articleComment = backArticleCommentDto.getArticleComment();
        int pageNo = backArticleCommentDto.getPageNo();
        int pageSize = backArticleCommentDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<ArticleComment> articleCommentPageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        backArticleCommentMapper.select(articleComment);
                    }
                });
        return articleCommentPageInfo;
    }
}
