package com.minimal.controller.wechat;

import com.minimal.common.api.dto.WeChatLoginDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.common.sdk.web.ResponseUtils;
import com.minimal.service.wechat.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关接口控制类
 *
 * @author linzhiqiang
 * @date 2019/4/25
 */
@RestController
@RequestMapping(value = "", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private static final String LOG_TITLE = "【用户查询】";

    /**
     * 用户观看视频获取积分奖励
     *
     * @param weChatLoginDto
     * @return
     */
    @RequestMapping(value = "adCoinAward", method = RequestMethod.POST)
    public String adCoinAward(@RequestBody WeChatLoginDto weChatLoginDto) {
        Map<String, Object> result = new HashMap();
        try {
            result = userService.adCoinAward(weChatLoginDto.getOpenId());
        } catch (Exception e) {
            logger.error("{}用户观看视频获取积分奖励失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }
}
