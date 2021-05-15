package com.minimal.service.back.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.back.BackArticleDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.ArticleComment;
import com.minimal.entity.model.ArticleTag;
import com.minimal.entity.model.Category;
import com.minimal.mapper.back.BackArticleCommentMapper;
import com.minimal.mapper.back.BackArticleMapper;
import com.minimal.mapper.back.BackCategoryMapper;
import com.minimal.mapper.back.BackTagMapper;
import com.minimal.service.back.BackArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linzhiqiang
 * @date 2019/3/12
 */
@Service
public class BackArticleServiceImpl implements BackArticleService {
    @Autowired
    private BackArticleMapper backArticleMapper;

    @Autowired
    private BackArticleCommentMapper backArticleCommentMapper;

    @Autowired
    private BackCategoryMapper backCategoryMapper;

    @Autowired
    private BackTagMapper backTagMapper;

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
        BackArticleDto backArticleDto = new BackArticleDto();
        // 文章详情、评论详情
        Article article = backArticleMapper.selectByPrimaryKey(id);
        backArticleDto.setArticle(article);
        // 获取文章相关评论
        tk.mybatis.mapper.entity.Example articleCommentExample = new tk.mybatis.mapper.entity.Example(ArticleComment.class);
        articleCommentExample.createCriteria().andEqualTo("article_id", id);
        List<ArticleComment> articleCommentList = backArticleCommentMapper.selectByExample(articleCommentExample);
        backArticleDto.setArticleComment(articleCommentList);
        // 查询文章分类
        tk.mybatis.mapper.entity.Example articleCategoryExample = new tk.mybatis.mapper.entity.Example(Category.class);
        articleCategoryExample.createCriteria().andEqualTo("article_id", id);
        List<Category> categoryList = backCategoryMapper.selectByExample(articleCategoryExample);
        backArticleDto.setCategoryList(categoryList);
        // 查询文章标签
        tk.mybatis.mapper.entity.Example articleTagExample = new tk.mybatis.mapper.entity.Example(ArticleTag.class);
        articleTagExample.createCriteria().andEqualTo("article_id", id);
        List<ArticleTag> articleTagList = backTagMapper.selectByExample(articleTagExample);
        backArticleDto.setArticleTagList(articleTagList);
        return backArticleDto;
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
