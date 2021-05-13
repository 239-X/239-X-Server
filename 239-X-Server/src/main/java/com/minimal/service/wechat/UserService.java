package com.minimal.service.wechat;

import com.minimal.common.api.dto.UserLikeDto;
import com.minimal.common.api.dto.WeChatLoginDto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/12
 */
public interface UserService {
    /**
     * 微信授权时候将用户注册到用户表中
     * @param weChatLoginDto
     */
    void loginWeChatUser(WeChatLoginDto weChatLoginDto);

    /**
     * 文章点赞
     * @param userLikeDto
     * @return
     */
    Map<String, Object> articleLike(UserLikeDto userLikeDto);

    /**
     * 每日签到
     * @param weChatLoginDto
     * @return
     */
    Map<String,Object> everydaySign(WeChatLoginDto weChatLoginDto);

    /**
     * 通过openId获取用户信息
     * @param openId
     * @return
     */
    Map<String,Object> selectUserByOpenId(String openId);

    /**
     * 通过openId获取用户的推荐信息
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Map<String,Object> selectRelationshipByOpenId(String openId,int pageNo, int pageSize);

    /**
     * 用户观看视频获取积分奖励
     * @param openId
     * @return
     */
    Map<String,Object> adCoinAward(String openId);
}
