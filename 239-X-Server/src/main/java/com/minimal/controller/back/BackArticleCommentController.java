package com.minimal.controller.back;

import com.minimal.common.api.dto.back.BackArticleCommentDto;
import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.service.back.BackArticleCommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章评论控制器
 *
 * @author linzhiqiang
 * @date 2019/3/8
 */
@RestController
@Log4j2
@RequestMapping(value = "back/comment", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BackArticleCommentController {
    @Autowired
    private BackArticleCommentService backArticleCommentService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody BackArticleCommentDto backArticleCommentDto) {
        String result = null;
        try {
            backArticleCommentService.save(backArticleCommentDto);
        } catch (Exception e) {
            log.error("保存失败,parameter={}", JsonUtils.toJson(backArticleCommentDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(@RequestBody BackArticleCommentDto backArticleCommentDto) {
        String result = null;
        try {
            backArticleCommentService.update(backArticleCommentDto);
        } catch (Exception e) {
            log.error("修改失败,parameter={}", JsonUtils.toJson(backArticleCommentDto), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(String id) {
        String result = null;
        try {
            backArticleCommentService.delete(id);
        } catch (Exception e) {
            log.error("删除失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(String id) {
        String result = null;
        try {
            return JsonUtils.toJson(backArticleCommentService.detail(id));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(id), e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(@RequestBody BackArticleCommentDto backArticleCommentDto) {
        String result = null;
        try {
            return JsonUtils.toJson(backArticleCommentService.select(backArticleCommentDto));
        } catch (Exception e) {
            log.error("查询失败,parameter={}", JsonUtils.toJson(backArticleCommentDto), e.getMessage());
        }
        return result;
    }
}

