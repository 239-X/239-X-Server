package com.minimal.service.impl;

import com.minimal.entity.model.User;
import com.minimal.entity.model.Video;
import com.minimal.mybatis.mapper.UserMapper;
import com.minimal.mybatis.mapper.VideoMapper;
import com.minimal.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/25
 */

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> selectAllVideo(String openId) {
        Map<String, Object> result = new HashMap();
        Example example = new Example(Video.class);
        example.excludeProperties("description");
        example.setOrderByClause("sort DESC , update_time DESC");
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("isEnable", 1);
        List<Video> videoList = videoMapper.selectByExample(example);
        result.put("videos",videoList);
        return result;
    }

    @Override
    public Map<String, Object> selectVideoById(String videoId,String openId) {
        Map<String, Object> result = new HashMap();
        Example example = new Example(Video.class);
        example.createCriteria().andEqualTo("isDelete", 0)
                .andEqualTo("isEnable", 1).andEqualTo("id",videoId);
        Video video = videoMapper.selectOneByExample(example);
        if(video!=null){
            // 检查积分是否够扣除
            int coin = video.getCoin();
            User user = userMapper.selectUserByOpenId(openId);
            BigDecimal subtractCoin = new BigDecimal(coin);
            if(subtractCoin.compareTo(user.getCoin())== 1){
                result.put("status","0");
                result.put("msg","积分不足");
                return result;
            }
            // 扣除用户积分
            BigDecimal lastCoin = user.getCoin().subtract(subtractCoin);
            user.setCoin(lastCoin);
            userMapper.updateByPrimaryKey(user);

            // 阅读数量加1
            int readNum = video.getReadNum();
            readNum++;
            video.setReadNum(readNum);
            // 更新阅读记录
            videoMapper.updateByPrimaryKey(video);
            result.put("status","1");
            result.put("msg","消耗"+coin+"积分");
            result.put("video",video);
            return result;
        }
        return result;
    }
}
