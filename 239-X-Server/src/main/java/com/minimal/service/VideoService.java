package com.minimal.service;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/25
 */
public interface VideoService {

    /**
     * 查询所有的视频列表
     *
     * @param openId
     * @return
     */
    Map<String,Object> selectAllVideo(String openId);

    /**
     * 查询指定id视频信息
     * @param videoId
     * @param openId
     * @return
     */
    Map<String,Object> selectVideoById(String videoId,String openId);
}
