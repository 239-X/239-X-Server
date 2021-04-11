package com.minimal.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minimal.entity.model.CampaignsKeywordInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JvmTest {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            File file = new File("E:/json/ad.json");
            in = new FileInputStream(file);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(in);
//            System.out.println(root.size());
            List<CampaignsKeywordInfo> allKeyword = new ArrayList<>();
//            JsonNode newRoot = root.get("list");
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
                }
            }
//            System.out.println(allKeyword.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}