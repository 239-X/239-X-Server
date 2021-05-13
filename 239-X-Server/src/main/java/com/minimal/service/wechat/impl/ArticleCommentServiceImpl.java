package com.minimal.service.wechat.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleCommentDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.entity.model.ArticleComment;
import com.minimal.entity.model.User;
import com.minimal.mapper.wechat.ArticleCommentMapper;
import com.minimal.mapper.wechat.UserMapper;
import com.minimal.service.wechat.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linzhiqiang
 * @date 2019/3/20
 */

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;


    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> selectArticleCommentsByArticleId(String articleId, int pageNo, int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        PageInfo<ArticleComment> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("update_time DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        ArticleComment articleComment = new ArticleComment();
                        articleComment.setArticleId(articleId);
                        articleComment.setParentId("0");
                        articleCommentMapper.select(articleComment);
                    }
                });
        Map<String, Object> result = new HashMap<>();
        List<Object> articleCommentList = new ArrayList<>();
        if (pageInfo.getTotal() > offset) {
            articleCommentList = packagingArticleComment(pageInfo.getList());
        }
        result.put("data", articleCommentList);
        return result;
    }

    @Override
    public Map<String, Object> insertArticleComment(ArticleCommentDto articleCommentDto) {
        Map<String, Object> result = new HashMap<>();
        ArticleComment articleComment = new ArticleComment();
        BeanUtils.copyProps(articleCommentDto, articleComment);
        long commentId = idGenerator.nextId();
        articleComment.setId(commentId + "");
        if (StringUtils.isBlank(articleCommentDto.getParentId())) {
            articleComment.setParentId("0");
        } else if (!"0".equals(articleCommentDto.getParentId())) {
            // 二级评论时，更新评论时间
            String parentId = articleCommentDto.getParentId();
            ArticleComment parentArticleComment = articleCommentMapper.selectByPrimaryKey(parentId);
            parentArticleComment.setUpdateTime(new Date());
            articleCommentMapper.updateByPrimaryKey(parentArticleComment);
        }
        User user = new User();
        user.setOpenid(articleCommentDto.getOpenId());
        articleComment.setUserId(userMapper.selectOne(user).getId());
        articleComment.setCreateTime(new Date());
        articleComment.setUpdateTime(new Date());
        articleComment.setIsDelete(0);
        articleComment.setVersion(1);
        articleComment.setLikeNum(0);
        String content = articleCommentDto.getContent();
        if (content.length() > 300) {
            result.put("status", "501");
        }
        int resultFlag = 0;
        try {
            resultFlag = articleCommentMapper.insert(articleComment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultFlag > 0) {
            result.put("status", "200");
        } else {
            result.put("status", "500");
        }
        return result;
    }

    /**
     * 封装文章评论数据
     *
     * @return
     */
    private List<Object> packagingArticleComment(List<ArticleComment> articleComments) {
        List<Object> articleCommentList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ArticleComment ac : articleComments) {
            // 属于一级评论
            if (StringUtils.isBlank(ac.getParentId()) || "0".equals(ac.getParentId())) {
                Map<String, Object> articleComment = new HashMap<>();
                articleComment.put("id", ac.getId());
                articleComment.put("author_name", ac.getAuthorName());
                articleComment.put("userid", ac.getUserId());
                articleComment.put("formId", ac.getId());
                articleComment.put("date", sdf.format(ac.getUpdateTime()));
                articleComment.put("content", ac.getContent());
                articleComment.put("author_url", ac.getAuthorUrl());
                List<Map> ArticleCommentChildList = new ArrayList<>();
                ArticleComment articleCommentQuery = new ArticleComment();
                articleCommentQuery.setParentId(ac.getId());
                PageInfo<ArticleComment> pageInfo = PageHelper.offsetPage(0, 50)
                        .setOrderBy("create_time DESC")
                        .doSelectPageInfo(new ISelect() {
                            @Override
                            public void doSelect() {
                                articleCommentMapper.select(articleCommentQuery);
                            }
                        });
                List<ArticleComment> articleCommentResult = pageInfo.getList();
//                // 属于二级评论
                for (ArticleComment acChild : articleCommentResult) {
                    Map<String, Object> articleCommentChild = new HashMap<>();
                    articleCommentChild.put("id", acChild.getId());
                    articleCommentChild.put("author_name", acChild.getAuthorName());
                    articleCommentChild.put("userid", acChild.getUserId());
                    articleCommentChild.put("formId", acChild.getId());
                    articleCommentChild.put("date", sdf.format(acChild.getCreateTime()));
                    articleCommentChild.put("content", acChild.getContent());
                    articleCommentChild.put("author_url", acChild.getAuthorUrl());
                    ArticleCommentChildList.add(articleCommentChild);
                }
                articleComment.put("child", ArticleCommentChildList);
                articleCommentList.add(articleComment);
            }
        }
        return articleCommentList;
    }
}
