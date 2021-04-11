package com.minimal.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleDto;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.ArticleComment;
import com.minimal.entity.model.ArticleLike;
import com.minimal.entity.model.ArticleTag;
import com.minimal.mybatis.mapper.*;
import com.minimal.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linzhiqiang
 * @date 2019/3/12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public Map<String, Object> selectAll(int pageNo, int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        PageInfo<ArticleDto> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC , id DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        articleMapper.selectHomeArticles();
                    }
                });
        Map<String, Object> result = new HashMap<>();
        List<Object> productList = new ArrayList<>();
        if (pageInfo.getTotal() > offset) {
            for (ArticleDto articleDto : pageInfo.getList()) {
                Map<String, Object> product = new HashMap<>();
                articleDto.setUpdateTimeStr(sdf.format(articleDto.getUpdateTime()));
                articleDto.setCreateTimeStr(sdf.format(articleDto.getCreateTime()));
                product.put("article", articleDto);
                productList.add(product);
            }
        }
        result.put("list", productList);
        result.put("count", pageInfo.getTotal());
        result.put("pageNo", pageNo);
        result.put("pageSize", pageSize);
        return result;
    }

    @Override
    public Map<String, Object> saveArticle(Article article) {
        Map<String, Object> result = new HashMap<>();
        article.setCreateTime((Timestamp) new Date());
        article.setUpdateTime((Timestamp) new Date());
        if (StringUtils.isNotBlank(article.getId())) {
            articleMapper.updateByPrimaryKey(article);
        } else {
            articleMapper.insert(article);
        }
        result.put("data", 1);
        return result;
    }

    @Override
    public Map<String, Object> selectById(String articleId, String subjectId) {
        Map<String, Object> result = new HashMap<>();
        Article article = articleMapper.selectByArticleId(articleId);
        if (article == null) {
            return result;
        }
        updateArticleReadNum(article);
        List<ArticleTag> articleTagList = articleTagMapper.selectTagsByArticleId(article.getId());
        ArticleLike articleLikeQuery = new ArticleLike();
        articleLikeQuery.setArticleId(article.getId());
        articleLikeQuery.setIsDelete(0);
        List<ArticleLike> articleLikes = articleLikeMapper.select(articleLikeQuery);
        result.put("article", article);
        result.put("tags", articleTagList);
        List<String> likeList = new ArrayList<>();
        int likeCount = 0;
        for (ArticleLike alike : articleLikes) {
            likeList.add(alike.getAuthorUrl());
            likeCount++;
        }
        result.put("likeList", likeList);
        result.put("likeCount", likeCount);
        ArticleComment articleCommentQuery = new ArticleComment();
        articleCommentQuery.setArticleId(articleId);
        int commentNum = articleCommentMapper.selectCount(articleCommentQuery);
        result.put("commentNum", commentNum);
//        // 上一页、下一页
//        Map previousMap = articleMapper.selectPreviousArticleByCreateTime(article.getCreateTime());
//        Map nextMap = articleMapper.selectNextArticleByCreateTime(article.getCreateTime());
//        result.put("previous", previousMap);
//        result.put("next", nextMap);

        // 带猜你喜欢
        PageInfo<ArticleDto> pageInfo = PageHelper.offsetPage(0, 4)
                .setOrderBy("update_time DESC , read_num DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        articleMapper.selectHomeArticles();
                    }
                });
        result.put("guessLikeList", pageInfo.getList());
        return result;
    }

    @Override
    public Map<String, Object> selectByKey(String key, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap();
        int offset = (pageNo - 1) * pageSize;
        PageInfo<ArticleDto> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("update_time DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        articleMapper.selectSearchBykey("%" + key + "%");
                    }
                });
        List<ArticleDto> resultList = new ArrayList<>();
        if (pageInfo.getTotal() > offset) {
            for (ArticleDto articleDto : pageInfo.getList()) {
                if (articleDto.getAuthorName().length() >= 15) {
                    articleDto.setAuthorName(articleDto.getAuthorName().substring(0, 15) + "*");
                }
                articleDto.setUpdateTimeStr(sdf.format(articleDto.getUpdateTime()));
                articleDto.setCreateTimeStr(sdf.format(articleDto.getCreateTime()));
                resultList.add(articleDto);
            }
        }
        result.put("data", resultList);
        return result;
    }

    @Override
    public Map<String, Object> selectHotArticleByTypeId(String typeId, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap();
        int offset = (pageNo - 1) * pageSize;
        StringBuilder stringBuilder = new StringBuilder();
        //评论数目
        if ("1".equals(typeId)) {
            stringBuilder.append("comment_num DESC , ");
        } else if ("2".equals(typeId)) {
            //浏览数目
            stringBuilder.append("read_num DESC , ");
        } else if ("3".equals(typeId)) {
            //点赞数目
            stringBuilder.append("like_num DESC , ");
        } else if ("4".equals(typeId)) {
            //收藏数目
            stringBuilder.append("collect_num DESC , ");
        }
        stringBuilder.append("update_time DESC");
        PageInfo<ArticleDto> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy(stringBuilder.toString())
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        articleMapper.selectHotArticleOrderByTypeId();
                    }
                });
        List<ArticleDto> resultList = new ArrayList<>();
        if (pageInfo.getTotal() > offset) {
            for (ArticleDto articleDto : pageInfo.getList()) {
                ArticleComment articleCommentQuery = new ArticleComment();
                if (articleDto.getAuthorName().length() >= 15) {
                    articleDto.setAuthorName(articleDto.getAuthorName().substring(0, 15) + "*");
                }
                articleDto.setUpdateTimeStr(sdf.format(articleDto.getUpdateTime()));
                articleDto.setCreateTimeStr(sdf.format(articleDto.getCreateTime()));
                resultList.add(articleDto);
            }
        }
        result.put("data", resultList);
        return result;
    }

    /**
     * 修改文章阅读数
     *
     * @param article
     */
    private void updateArticleReadNum(Article article) {
        if (article == null) {
            return;
        }
        article.setReadNum(article.getReadNum() + 1);
        articleMapper.updateByPrimaryKey(article);
    }
}
