package com.minimal.controller.test;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minimal.common.sdk.utils.RedisUtil;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.CampaignsKeywordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author linzhiqiang
 * @date 2019/3/4
 */

@RestController
public class RedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Object jvm() throws IOException {
        List<CampaignsKeywordInfo> allKeyword = new ArrayList<>();
        InputStream in = null;
        try {
            File file = new File("E:/json/ad.json");
            in = new FileInputStream(file);
//            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(in);
//            System.out.println(root.size());
            if (root.isArray()) {
                for (JsonNode keywordItem : root) {
                    CampaignsKeywordInfo campaignsKeywordInfo = new CampaignsKeywordInfo();
                    campaignsKeywordInfo.setCampaignid(Long.parseLong(keywordItem.path("campaignId").asText()));
                    campaignsKeywordInfo.setMatchtype(keywordItem.path("matchType").asText());
                    campaignsKeywordInfo.setCreatetime(new Date());
                    campaignsKeywordInfo.setUpdatetime(new Date());
                    campaignsKeywordInfo.setKeywordid(Long.parseLong(keywordItem.path("keywordId").asText()));
                    campaignsKeywordInfo.setKeywordtext(keywordItem.path("keywordText").asText());
                    allKeyword.add(campaignsKeywordInfo);
                    keywordItem = null;
                    campaignsKeywordInfo = null;
                }
            }
            allKeyword = null;
//            objectMapper = null;
            root = null;
//            System.out.println(allKeyword.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return allKeyword;
    }

    @RequestMapping(value = "jvm", method = RequestMethod.GET)
    public Object jvms(int num) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0;i<num;i++){
            jvm();
            Thread.sleep(10);
        }
        long end = System.currentTimeMillis();
        long time = end-start;
        return "time:"+time+"  num:"+num;
    }

    @RequestMapping(value = "setKey", method = RequestMethod.GET)
    public String setKey(String key, String value) {
        redisUtil.set(key, value);
        return "setKey:key:" + key + " value" + value;
    }

    @RequestMapping(value = "getValue", method = RequestMethod.GET)
    public String getValue(String key) {
        String value = redisUtil.get(key) + "";
        return "getValue:key:" + key + " value" + value;
    }
}
