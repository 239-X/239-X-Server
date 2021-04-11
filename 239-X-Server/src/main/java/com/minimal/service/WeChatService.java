package com.minimal.service;

import com.minimal.common.api.dto.WXACodeDto;
import com.minimal.common.api.dto.WeChatLoginDto;
import org.apache.catalina.connector.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author linzhiqiang
 * @date 2019/3/19
 */
@FeignClient(url = "${wechat.url}", name = "weChatTest")
public interface WeChatService {
    /**
     * 获取微信登录的授权
     *
     * @param appId
     * @param secret
     * @param jsCode
     * @param authorizationCode
     * @return
     */
    @RequestMapping(value = "/sns/jscode2session", method = RequestMethod.GET)
    String getJscode2session(@RequestParam(value = "appid", required = true) String appId,
                             @RequestParam(value = "secret", required = true) String secret,
                             @RequestParam(value = "js_code", required = true) String jsCode,
                             @RequestParam(value = "authorization_code", required = true) String authorizationCode);


    /**
     * 获取小程序二维码，适用于需要的码数量较少的业务场景.
     * @param accessToken
     * @param wXACodeDto
     * @return
     */
    @RequestMapping(value = "/wxa/getwxacode", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    String getwxacode(@RequestParam("access_token") String accessToken, @RequestBody WXACodeDto wXACodeDto);

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     * @param grantType client_credential
     * @param appid
     * @param secret
     * @return
     */
    @RequestMapping(value = "/cgi-bin/token", method = RequestMethod.GET)
    String token(@RequestParam(value = "grant_type", required = true) String grantType,
                           @RequestParam(value = "appid", required = true) String appid,
                           @RequestParam(value = "secret", required = true) String secret);
}
