package com.minimal.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleCollectDto;
import com.minimal.common.api.dto.UserDto;
import com.minimal.common.api.dto.UserLikeDto;
import com.minimal.common.api.dto.WeChatLoginDto;
import com.minimal.common.constant.UserConstants;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.RedisUtil;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.entity.model.ArticleLike;
import com.minimal.entity.model.Config;
import com.minimal.entity.model.User;
import com.minimal.entity.model.UserRelationship;
import com.minimal.mybatis.mapper.ArticleLikeMapper;
import com.minimal.mybatis.mapper.ConfigMapper;
import com.minimal.mybatis.mapper.UserMapper;
import com.minimal.mybatis.mapper.UserRelationshipMapper;
import com.minimal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linzhiqiang
 * @date 2019/3/12
 */
@Service
public class UserServiceImpl implements UserService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void loginWeChatUser(WeChatLoginDto weChatLoginDto) {
        System.out.println("getActOpenId:" + weChatLoginDto.getActOpenId());
        if (StringUtils.isBlank(weChatLoginDto.getOpenId())) {
            return;
        }
        User userQuery = new User();
        userQuery.setOpenid(weChatLoginDto.getOpenId());
        userQuery = userMapper.selectOne(userQuery);
        if (userQuery != null && StringUtils.isNotBlank(userQuery.getId())) {
            return;
        }
        // 新增用户注册
        User user = new User();
        String id = idGenerator.nextId() + "";
        user.setId(id);
        user.setOpenid(weChatLoginDto.getOpenId());
        String nickname = weChatLoginDto.getNickname();
        try {
            nickname = new String(nickname.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setNickname(weChatLoginDto.getNickname());
        user.setHeadPortrait(weChatLoginDto.getAvatarUrl());
        user.setAccount(id);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCoin(new BigDecimal(0));
        user.setIsDelete(0);
        user.setVersion(1);
        userMapper.insert(user);
        if (StringUtils.isNotBlank(weChatLoginDto.getActOpenId()) && !weChatLoginDto.getOpenId().equals(weChatLoginDto.getActOpenId())) {
            System.out.println("openId和ActOpenId不同");
            User actUserQuery = new User();
            actUserQuery.setOpenid(weChatLoginDto.getActOpenId());
            actUserQuery = userMapper.selectOne(actUserQuery);
            if (actUserQuery != null && StringUtils.isNotBlank(actUserQuery.getId())) {
                System.out.println("actUserQuery用户存在");
                // 记录推荐关系
                UserRelationship userRelationship = new UserRelationship();
                userRelationship.setId(idGenerator.nextId() + "");
                Config queryConfig = new Config();
                queryConfig.setIsDelete(0);
                // 获取推荐用户奖励积分
                queryConfig.setConfigKey("shareCoin");
                int signCoin = Integer.valueOf(configMapper.selectOne(queryConfig).getConfigValue());
                userRelationship.setActUserId(actUserQuery.getId());
                userRelationship.setBeUserId(id);
                userRelationship.setCoin(signCoin);
                userRelationship.setIsDelete(0);
                userRelationship.setVersion(1);
                userRelationship.setCreateTime(new Date());
                userRelationship.setUpdateTime(new Date());
                userRelationshipMapper.insert(userRelationship);
                System.out.println("userRelationship保存成功");
                // 用户增加积分
                BigDecimal userAddCoin = new BigDecimal(signCoin);
                BigDecimal saveCoin = actUserQuery.getCoin().add(userAddCoin);
                actUserQuery.setCoin(saveCoin);
                userMapper.updateByPrimaryKey(actUserQuery);
                System.out.println("推荐人用户更新成功,saveCoin值为：" + saveCoin);
            }
        }
    }

    @Override
    public Map<String, Object> articleLike(UserLikeDto userLikeDto) {
        Map<String, Object> response = new HashMap<>();
        User user = new User();
        user.setOpenid(userLikeDto.getOpenId());
        user = userMapper.selectOne(user);
        if (checkIsLikeExist(userLikeDto.getArticleId(), user.getId())) {
            response.put("status", "501");
            return response;
        }
        String id = idGenerator.nextId() + "";
        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticleId(userLikeDto.getArticleId());
        articleLike.setId(id);
        articleLike.setAuthorUrl(user.getHeadPortrait());
        articleLike.setUserId(user.getId());
        articleLike.setCreateTime(new Date());
        articleLike.setUpdateTime(new Date());
        articleLike.setIsDelete(0);
        articleLike.setVersion(1);
        int resultFlag = articleLikeMapper.insert(articleLike);
        if (resultFlag > 0) {
            response.put("status", "200");
        }
        return response;
    }

    @Override
    public Map<String, Object> everydaySign(WeChatLoginDto weChatLoginDto) {
        String openId = weChatLoginDto.getOpenId();
        Map<String, Object> response = new HashMap<>();
        if (StringUtils.isBlank(openId)) {
            // openId不能为null
            response.put("status", 0);
            response.put("msg", "openId不能为null");
            return response;
        }
        User queryUser = new User();
        queryUser.setIsDelete(0);
        queryUser.setOpenid(openId);
        queryUser = userMapper.selectOne(queryUser);
        if (queryUser == null) {
            // 用户不存在
            response.put("status", 3);
            response.put("msg", "用户不存在！");
            return response;
        }
        String currentDate = dateFormat.format(new Date());
        String signRedisKey = UserConstants.EVERYDAYSIGN + "_" + queryUser.getId() + "_" + currentDate;
        if (redisUtil.get(signRedisKey) != null) {
            // 今日已经点赞过了
            response.put("status", 1);
            response.put("msg", "亲，今日已签到了哦~");
            return response;
        }
        Config queryConfig = new Config();
        queryConfig.setIsDelete(0);
        queryConfig.setConfigKey("signCoin");
        int signCoin = Integer.valueOf(configMapper.selectOne(queryConfig).getConfigValue());
        if (redisUtil.get(signRedisKey) == null) {
            BigDecimal beforeCoin = queryUser.getCoin();
            int ratio =1;
            // 三倍签到
            if(weChatLoginDto.getRatio()!=0){
                ratio = weChatLoginDto.getRatio();
            }
            signCoin = signCoin * ratio;
            BigDecimal increaseCoin = new BigDecimal(signCoin);
            BigDecimal currentCoin = beforeCoin.add(increaseCoin);
            queryUser.setCoin(currentCoin);
            userMapper.updateByPrimaryKey(queryUser);
            // 签到时间保存两天
            redisUtil.set(signRedisKey, signCoin, 60 * 60 * 48);
            response.put("status", 2);
            response.put("msg", "签到成功！");
        }
        return response;
    }

    @Override
    public Map<String, Object> selectUserByOpenId(String openId) {
        Map<String, Object> response = new HashMap<>();
        User queryUser = new User();
        queryUser.setIsDelete(0);
        queryUser.setOpenid(openId);
        queryUser = userMapper.selectOne(queryUser);
        response.put("data", queryUser);

        // 检查今日是否签到
        String currentDate = dateFormat.format(new Date());
        String signRedisKey = UserConstants.EVERYDAYSIGN + "_" + queryUser.getId() + "_" + currentDate;
        if (redisUtil.get(signRedisKey) != null) {
            // 今日已经点赞过了
            response.put("signStatus","1");
        }else {
            // 今日还未点赞
            response.put("signStatus","0");
        }
        return response;
    }

    @Override
    public Map<String, Object> selectRelationshipByOpenId(String openId, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap();
        int offset = (pageNo - 1) * pageSize;
        if (StringUtils.isBlank(openId)) {
            return result;
        }
        String userId = userMapper.selectUserByOpenId(openId).getId();
        PageInfo<UserDto> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .setOrderBy("create_time DESC")
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        userMapper.selectRelationshipUserByActUserId(userId);
                    }
                });
        if (pageInfo.getTotal() > offset) {
            for (UserDto userDto : pageInfo.getList()) {
                userDto.setCreateTimeStr(dateFormat.format(userDto.getCreateTime()));
            }
        }
        result.put("data", pageInfo.getList());
        return result;
    }

    @Override
    public Map<String, Object> adCoinAward(String openId) {
        Map<String, Object> response = new HashMap<>();
        // 获取广告视频奖励数值
        Config queryConfig = new Config();
        queryConfig.setIsDelete(0);
        queryConfig.setConfigKey("adCoinAward");
        int adCoinAward = Integer.valueOf(configMapper.selectOne(queryConfig).getConfigValue());

        // 获取用户信息
        User queryUser = new User();
        queryUser.setIsDelete(0);
        queryUser.setOpenid(openId);
        queryUser = userMapper.selectOne(queryUser);

        if(queryUser!=null){
            // 如果分值为0，则修改为30
            if(adCoinAward==0){
                adCoinAward = 30;
            }
            //增加用户积分
            BigDecimal beforeCoin = queryUser.getCoin();
            BigDecimal increaseCoin = new BigDecimal(adCoinAward);
            BigDecimal currentCoin = beforeCoin.add(increaseCoin);
            queryUser.setCoin(currentCoin);
            userMapper.updateByPrimaryKey(queryUser);
            response.put("status","1");
        }else {
            // 用户不存在
            response.put("status","0");
        }
        return response;
    }

    /**
     * 判断用户是否已经点赞过
     *
     * @param articleId
     * @param userId
     * @return
     */
    private boolean checkIsLikeExist(String articleId, String userId) {
        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticleId(articleId);
        articleLike.setUserId(userId);
        articleLike = articleLikeMapper.selectOne(articleLike);
        if (articleLike != null && StringUtils.isNotBlank(articleLike.getId())) {
            return true;
        }
        return false;
    }
}
