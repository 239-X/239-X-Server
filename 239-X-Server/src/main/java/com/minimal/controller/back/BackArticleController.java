package com.minimal.controller.back;

import com.minimal.common.api.dto.back.BackArticleDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.back.BackArticleService;
import com.minimal.util.QiniuUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 平台文章控制类
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "back/backArticleDto", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BackArticleController {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.imgPath}")
    private String imgPath;

    @Autowired
    private BackArticleService backArticleService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody BackArticleDto backArticleDto) {
        String result = null;
        try {
            result = JsonUtils.toJson(backArticleService.save(backArticleDto));
        } catch (Exception e) {
            log.error("保存文章失败,backArticleDto={}", JsonUtils.toJson(backArticleDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(@RequestBody BackArticleDto backArticleDto) {
        String result = null;
        try {
            result = JsonUtils.toJson(backArticleService.update(backArticleDto));
        } catch (Exception e) {
            log.error("保存文章失败,backArticleDto={}", JsonUtils.toJson(backArticleDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(@RequestBody String articleId) {
        String result = null;
        try {
            result = JsonUtils.toJson(backArticleService.delete(articleId));
        } catch (Exception e) {
            log.error("删除文章失败,backArticleDto={}", JsonUtils.toJson(articleId), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody BackArticleDto backArticleDto) {
        String result = null;
        try {
            return JsonUtils.toJson(backArticleService.select(backArticleDto));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(backArticleDto), e.getMessage());
        }
        return result;
    }


    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(String id) {
        String result = null;
        try {
            result = JsonUtils.toJson(backArticleService.detail(id));
        } catch (Exception e) {
            log.error("获取文章列表信息失败,id={},error={}", id, e);
        }
        return result;
    }

    @RequestMapping(value = "uploadPic", method = RequestMethod.POST)
    public String uploadPic(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        String result = null;
        String fileName = null;
        try {
            if (file.isEmpty()) {
                return "上传失败，请选择文件";
            }
            fileName = file.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + ".pic";
            String newQiNiuYunKey = QiniuUtil.upload(QiniuUtil.DEFAULT_BUCKET, newFileName, file.getBytes());
            resultMap.put("data", newQiNiuYunKey);
            result = JsonUtils.toJson(resultMap);
        } catch (Exception e) {
            log.error("获取文章列表信息失败,fileName:{},error:{}", fileName, e);
        }
        return result;
    }
}

