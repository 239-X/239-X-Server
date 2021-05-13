package com.minimal.service.wechat.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.ArticleDto;
import com.minimal.common.api.dto.ArticleSubjectDto;
import com.minimal.common.constant.UserConstants;
import com.minimal.common.sdk.sequence.IdGenerator;
import com.minimal.common.sdk.utils.BeanUtils;
import com.minimal.common.sdk.utils.RedisUtil;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.entity.model.*;
import com.minimal.mapper.wechat.ArticleCommentMapper;
import com.minimal.mapper.wechat.ArticleSubjectMapper;
import com.minimal.mapper.wechat.ArticleSubjectUserMapper;
import com.minimal.mapper.wechat.UserMapper;
import com.minimal.service.wechat.ArticleSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linzhiqiang
 * @date 2019/3/27
 */

@Service
public class ArticleSubjectServiceImpl implements ArticleSubjectService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ArticleSubjectMapper articleSubjectMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleSubjectUserMapper articleSubjectUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> selectAllArticleSubject(String openId) {
        Map<String, Object> result = new HashMap();
        User userQuery =null;
        if(StringUtils.isNotBlank(openId)){
            userQuery = new User();
            userQuery.setIsDelete(0);
            userQuery.setOpenid(openId);
            userQuery = userMapper.selectOne(userQuery);
        }
        Example example = new Example(ArticleSubject.class);
        example.setOrderByClause("sort DESC , update_time DESC");
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("isEnable", 1);
        List<ArticleSubjectDto> articleSubjectListResult = new ArrayList<ArticleSubjectDto>();
        List<ArticleSubject> articleSubjectList = articleSubjectMapper.selectByExample(example);
        for (ArticleSubject articleSubject: articleSubjectList){
            ArticleSubjectDto articleSubjectDto = new ArticleSubjectDto();
            BeanUtils.copyProps(articleSubject,articleSubjectDto);
            if(userQuery!=null && StringUtils.isNotBlank(userQuery.getId())){
                ArticleSubjectUser articleSubjectUserQuery = new ArticleSubjectUser();
                articleSubjectUserQuery.setIsDelete(0);
                articleSubjectUserQuery.setSubjectId(articleSubjectDto.getId());
                articleSubjectUserQuery.setUserId(userQuery.getId());
                int isSubscribe = articleSubjectUserMapper.selectCount(articleSubjectUserQuery);
                if(isSubscribe>0){
                    articleSubjectDto.setIsSubscription(1);
                }else{
                    articleSubjectDto.setIsSubscription(0);
                }
            }else {
                articleSubjectDto.setIsSubscription(0);
            }
            articleSubjectListResult.add(articleSubjectDto);
        }
        result.put("data", articleSubjectListResult);
        return result;
    }

    @Override
    public Map<String, Object> selectArticleSubjectById(String id,String openId) {
        Map<String, Object> result = new HashMap();
        // 专题信息
        ArticleSubject articleSubject = articleSubjectMapper.selectByPrimaryKey(id);
        ArticleSubjectDto articleSubjectDto =new ArticleSubjectDto();
        BeanUtils.copyProps(articleSubject,articleSubjectDto);
        articleSubjectDto.setUpdateTimeStr(sdf.format(articleSubjectDto.getUpdateTime()));
        articleSubjectDto.setCreateTimeStr(sdf.format(articleSubjectDto.getCreateTime()));

        // 专题的用户信息
        User userQuery =null;
        if(StringUtils.isNotBlank(openId)){
            userQuery = new User();
            userQuery.setIsDelete(0);
            userQuery.setOpenid(openId);
            userQuery = userMapper.selectOne(userQuery);
        }
        if(userQuery!=null && StringUtils.isNotBlank(userQuery.getId())){
            // 查询用户订阅信息
            ArticleSubjectUser articleSubjectUserQuery = new ArticleSubjectUser();
            articleSubjectUserQuery.setIsDelete(0);
            articleSubjectUserQuery.setSubjectId(articleSubjectDto.getId());
            articleSubjectUserQuery.setUserId(userQuery.getId());
            int isSubscribe = articleSubjectUserMapper.selectCount(articleSubjectUserQuery);
            if(isSubscribe>0){
                articleSubjectDto.setIsSubscription(1);
            }else{
                articleSubjectDto.setIsSubscription(0);
            }
        }else {
            articleSubjectDto.setIsSubscription(0);
        }
        result.put("data", articleSubjectDto);
        return result;
    }

    @Override
    public Map<String, Object> selectArticleBySubjectId(String articleSubjectId, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap();
        int offset = (pageNo - 1) * pageSize;
        PageInfo<ArticleDto> pageInfo = PageHelper.offsetPage(offset, pageSize)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        articleSubjectMapper.selectArticleSubjectByArticleSubjecId(articleSubjectId);
                    }
                });
        // 修改专题阅读次数
        String subjectReadNumRedisKey = UserConstants.SUBJECTREADNUM + "_" + articleSubjectId;
        if (redisUtil.get(subjectReadNumRedisKey) != null) {
            redisUtil.incr(subjectReadNumRedisKey,1);
        }else {
            // 时间永久保留
            redisUtil.set(subjectReadNumRedisKey, 1, 0);
        }
        List<ArticleDto> resultList = new ArrayList<>();
        if (pageInfo.getTotal() > offset) {
            for (ArticleDto articleDto : pageInfo.getList()) {
                if(articleDto.getAuthorName().length()>=15){
                    articleDto.setAuthorName(articleDto.getAuthorName().substring(0,15)+"*");
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
    public Map<String, Object> subjectSubscribeById(ArticleSubjectDto articleSubjectDto) {
        Map<String, Object> result = new HashMap();
        User userQuery = new User();
        userQuery.setIsDelete(0);
        userQuery.setOpenid(articleSubjectDto.getOpenId());
        userQuery = userMapper.selectOne(userQuery);
        if(userQuery==null || StringUtils.isBlank(userQuery.getId())){
            result.put("data","0");
            result.put("msg",articleSubjectDto.getOpenId()+"用户为null");
            return result;
        }

        ArticleSubject articleSubjectQuery = new ArticleSubject();
        articleSubjectQuery.setId(articleSubjectDto.getId());
        articleSubjectQuery.setIsDelete(0);
        articleSubjectQuery = articleSubjectMapper.selectOne(articleSubjectQuery);
        if(articleSubjectQuery==null || StringUtils.isBlank(articleSubjectQuery.getId())){
            result.put("data","1");
            result.put("msg","对应主题不存在！");
            return result;
        }

        BigDecimal subtractCoin = new BigDecimal(articleSubjectQuery.getCoin());
        if(subtractCoin.compareTo(userQuery.getCoin())== 1){
            result.put("data","2");
            result.put("msg","积分不足");
            return result;
        }
        ArticleSubjectUser articleSubjectUser = new ArticleSubjectUser();
        articleSubjectUser.setSubjectId(articleSubjectQuery.getId());
        articleSubjectUser.setUserId(userQuery.getId());
        articleSubjectUser.setIsDelete(0);
        if(articleSubjectUserMapper.selectCount(articleSubjectUser)>0){
            result.put("data","4");
            result.put("msg","专题已订阅");
            return result;
        }
        articleSubjectUser.setIsFree(articleSubjectQuery.getIsFree());
        articleSubjectUser.setCreateTime(new Date());
        articleSubjectUser.setUpdateTime(new Date());
        articleSubjectUser.setCoin(articleSubjectQuery.getCoin());
        articleSubjectUser.setVersion(1);
        articleSubjectUser.setId(idGenerator.nextId()+"");
        articleSubjectUserMapper.insert(articleSubjectUser);
        BigDecimal coin = userQuery.getCoin().subtract(subtractCoin);
        userQuery.setCoin(coin);
        userMapper.updateByPrimaryKey(userQuery);
        result.put("data","3");
        result.put("msg","订阅成功");
        return result;
    }

    @Override
    public Map<String, Object> selectSubjectByOpenId(String openId) {
        Map<String, Object> result = new HashMap();
        String userId =null;
        if(StringUtils.isNotBlank(openId)){
            userId = userMapper.selectUserByOpenId(openId).getId();
        }
        List<ArticleSubjectDto> articleSubjectList = articleSubjectUserMapper.selectSubjectByUserId(userId);
        for (int i=0;i<articleSubjectList.size();i++){
            ArticleSubjectDto articleSubjectDto = articleSubjectList.get(i);
            if(articleSubjectDto!=null){
                articleSubjectDto.setIsSubscription(1);
            }else {
                articleSubjectList.remove(i);
            }
        }
        result.put("data", articleSubjectList);
        return result;
    }

    @Override
    public Map<String, Object> cancelSubject(ArticleSubjectDto articleSubjectDto) {
        Map<String, Object> result = new HashMap();
        if(StringUtils.isBlank(articleSubjectDto.getId()) ||
                StringUtils.isBlank(articleSubjectDto.getOpenId())){
            result.put("data","500");
            result.put("msg","id或者openId为null");
            return result;
        }
        String userId = userMapper.selectUserByOpenId(articleSubjectDto.getOpenId()).getId();
        ArticleSubjectUser articleSubjectUserQuery = new ArticleSubjectUser();
        articleSubjectUserQuery.setIsDelete(0);
        articleSubjectUserQuery.setUserId(userId);
        articleSubjectUserQuery.setSubjectId(articleSubjectDto.getId());
        articleSubjectUserQuery = articleSubjectUserMapper.selectOne(articleSubjectUserQuery);
        if(articleSubjectUserQuery!=null && StringUtils.isNotBlank(articleSubjectUserQuery.getId())){
            articleSubjectUserMapper.delete(articleSubjectUserQuery);
            result.put("data","200");
            result.put("msg","取消成功");
            return result;
        }
        result.put("data","501");
        result.put("msg","取消失败");
        return result;
    }
}
