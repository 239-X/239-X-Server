package com.minimal.service;

import com.minimal.common.api.dto.UserLikeDto;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/22
 */
public interface ArticleCollectService {

    /**
     * 查询我的收藏
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Map<String,Object> selectCollectByOpenId(String openId, int pageNo, int pageSize);

    /**
     * 用户收藏
     * @param userLikeDto
     * @return
     */
    Map<String, Object> userCollect(UserLikeDto userLikeDto);
}
