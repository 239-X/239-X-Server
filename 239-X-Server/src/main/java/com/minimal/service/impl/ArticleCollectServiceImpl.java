package com.minimal.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleCollectDto;
import com.minimal.common.api.dto.UserLikeDto;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.entity.model.ArticleCollect;
import com.minimal.entity.model.ArticleComment;
import com.minimal.entity.model.User;
import com.minimal.mybatis.mapper.ArticleCollectMapper;
import com.minimal.mybatis.mapper.UserMapper;
import com.minimal.service.ArticleCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/22
 */

@Service
public class ArticleCollectServiceImpl implements ArticleCollectService{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ArticleCollectMapper articleCollectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public Map<String, Object> selectCollectByOpenId(String openId, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap();
        int offset = (pageNo - 1) * pageSize;
        if(StringUtils.isBlank(openId)){
            return result;
        }
        User userQuery = new User();
        userQuery.setOpenid(openId);
        userQuery.setIsDelete(0);
        String userId = userMapper.selectOne(userQuery).getId();
        PageInfo<ArticleCollectDto> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("art_coll.create_time DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        articleCollectMapper.selectCollectByUserId(userId);
                    }
                });
        List<ArticleCollectDto> resultList = new ArrayList<>();
        if (pageInfo.getTotal() > offset) {
            for (ArticleCollectDto articleCollectDto : pageInfo.getList()) {
                articleCollectDto.setUpdateTimeStr(sdf.format(articleCollectDto.getUpdateTime()));
                articleCollectDto.setCreateTimeStr(sdf.format(articleCollectDto.getCreateTime()));
                resultList.add(articleCollectDto);
            }
        }
        result.put("data", resultList);
        return result;
    }

    @Override
    public Map<String, Object> userCollect(UserLikeDto userLikeDto) {
        Map<String, Object> response = new HashMap<>();
        User user = new User();
        user.setOpenid(userLikeDto.getOpenId());
        user = userMapper.selectOne(user);
        if (checkIsCollectExist(userLikeDto.getArticleId(), user.getId())) {
            response.put("status", "501");
            return response;
        }
        String id = idGenerator.nextId() + "";
        ArticleCollect articleCollect = new ArticleCollect();
        articleCollect.setArticleId(userLikeDto.getArticleId());
        articleCollect.setId(id);
        articleCollect.setUserId(user.getId());
        articleCollect.setCreateTime(new Date());
        articleCollect.setUpdateTime(new Date());
        articleCollect.setIsDelete(0);
        articleCollect.setVersion(1);
        int resultFlag = articleCollectMapper.insert(articleCollect);
        if (resultFlag > 0) {
            response.put("status", "200");
        }
        return response;
    }

    /**
     * 判断用户是否已经收藏，如果已收藏则取消收藏
     *
     * @param articleId
     * @param userId
     * @return
     */
    private boolean checkIsCollectExist(String articleId, String userId) {
        ArticleCollect articleCollect = new ArticleCollect();
        articleCollect.setArticleId(articleId);
        articleCollect.setUserId(userId);
        articleCollect = articleCollectMapper.selectOne(articleCollect);
        if (articleCollect != null && StringUtils.isNotBlank(articleCollect.getId())) {
            articleCollectMapper.delete(articleCollect);
            return true;
        }
        return false;
    }
}
