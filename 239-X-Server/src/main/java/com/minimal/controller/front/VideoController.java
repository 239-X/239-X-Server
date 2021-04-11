package com.minimal.controller.front;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.common.sdk.web.ResponseUtils;
import com.minimal.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频相关接口控制类
 * @author linzhiqiang
 * @date 2019/4/25
 */
@RestController
@RequestMapping(value = "", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class VideoController {
    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    private static final String LOG_TITLE = "【视频专题查询】";
    /**
     * 获取视频专题列表
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectVideos", method = RequestMethod.GET)
    public String selectVideos(String openId) {
        Map<String, Object> result = new HashMap();
        try {
            result = videoService.selectAllVideo(openId);
        } catch (Exception e) {
            logger.error("{}获取视频列表信息失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }

    /**
     * 查询指定id视频信息
     *
     * @param videoId
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectVideoById", method = RequestMethod.GET)
    public String selectVideoById(String videoId,String openId) {
        Map<String, Object> result = new HashMap();
        try {
            result = videoService.selectVideoById(videoId,openId);
        } catch (Exception e) {
            logger.error("{}获取视频信息失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }
}
