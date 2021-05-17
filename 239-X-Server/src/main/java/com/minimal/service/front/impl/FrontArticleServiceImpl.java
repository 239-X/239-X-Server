package com.minimal.service.front.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.front.FrontArticleDto;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.ArticleComment;
import com.minimal.entity.model.ArticleTag;
import com.minimal.entity.model.Category;
import com.minimal.mapper.front.FrontArticleCommentMapper;
import com.minimal.mapper.front.FrontArticleMapper;
import com.minimal.mapper.front.FrontCategoryMapper;
import com.minimal.mapper.front.FrontTagMapper;
import com.minimal.service.front.FrontArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前端文章服务
 *
 * @author linzhiqiang
 * @date 2021-05-15
 */
@Service
@Log4j2
public class FrontArticleServiceImpl implements FrontArticleService {
    @Autowired
    private FrontArticleMapper frontArticleMapper;

    @Autowired
    private FrontArticleCommentMapper frontArticleCommentMapper;

    @Autowired
    private FrontCategoryMapper frontCategoryMapper;

    @Autowired
    private FrontTagMapper frontTagMapper;

    @Override
    public Object select(FrontArticleDto frontArticleDto) {
        Article article = frontArticleDto.getArticle();
        int pageNo = frontArticleDto.getPageNo();
        int pageSize = frontArticleDto.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        // 分页查询
        PageInfo<Article> articlePageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        frontArticleMapper.select(article);
                    }
                });
        return articlePageInfo;
    }

    @Override
    public Object detail(String id) {
        FrontArticleDto frontArticleDto = new FrontArticleDto();
        // 文章详情、评论详情
        Article article = frontArticleMapper.selectByPrimaryKey(id);
        frontArticleDto.setArticle(article);
        // 获取文章相关评论
        tk.mybatis.mapper.entity.Example articleCommentExample = new tk.mybatis.mapper.entity.Example(ArticleComment.class);
        articleCommentExample.createCriteria().andEqualTo("article_id", id);
        List<ArticleComment> articleCommentList = frontArticleCommentMapper.selectByExample(articleCommentExample);
        frontArticleDto.setArticleComment(articleCommentList);
        // 查询文章分类
        tk.mybatis.mapper.entity.Example articleCategoryExample = new tk.mybatis.mapper.entity.Example(Category.class);
        articleCategoryExample.createCriteria().andEqualTo("article_id", id);
        List<Category> categoryList = frontCategoryMapper.selectByExample(articleCategoryExample);
        frontArticleDto.setCategoryList(categoryList);
        // 查询文章标签
        tk.mybatis.mapper.entity.Example articleTagExample = new tk.mybatis.mapper.entity.Example(ArticleTag.class);
        articleTagExample.createCriteria().andEqualTo("article_id", id);
        List<ArticleTag> articleTagList = frontTagMapper.selectByExample(articleTagExample);
        frontArticleDto.setArticleTagList(articleTagList);
        return frontArticleDto;
    }

    @Override
    public Object search(String key) {
        // 分页查询
        PageInfo<Article> articlePageInfo = PageHelper.offsetPage(0, 10)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        tk.mybatis.mapper.entity.Example example = new tk.mybatis.mapper.entity.Example(Article.class);
                        example.createCriteria().andLike("title", key);
                        frontArticleMapper.selectByExample(example);
                    }
                });
        return articlePageInfo;
    }
}