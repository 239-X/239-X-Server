package com.minimal.controller.wechat;

import com.minimal.common.sdk.utils.JsonUtils;
import com.minimal.common.sdk.web.ResponseUtils;
import com.minimal.service.wechat.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 书籍相关接口控制类
 *
 * @author linzhiqiang
 * @date 2019/4/25
 */
@RestController
@RequestMapping(value = "", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class BookController {
    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    private static final String LOG_TITLE = "【书籍专题查询】";

    /**
     * 获取书籍专题列表
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectBooks", method = RequestMethod.GET)
    public String selectBooks(String openId) {
        Map<String, Object> result = new HashMap();
        try {
            result = bookService.selectAllBook(openId);
        } catch (Exception e) {
            logger.error("{}获取书籍列表信息失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }

    /**
     * 查询指定id书籍信息
     *
     * @param bookId
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectBookById", method = RequestMethod.GET)
    public String selectBookById(String bookId, String openId) {
        Map<String, Object> result = new HashMap();
        try {
            result = bookService.selectBookById(bookId, openId);
        } catch (Exception e) {
            logger.error("{}获取书籍信息失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }

    /**
     * 查询指定id书籍的文章信息
     *
     * @param bookId
     * @param articleId
     * @param openId
     * @return
     */
    @RequestMapping(value = "selectBookArticleById", method = RequestMethod.GET)
    public String selectBookArticleById(String bookId, String articleId, String openId) {
        Map<String, Object> result = new HashMap();
        try {
            result = bookService.selectBookArticleById(bookId, articleId, openId);
        } catch (Exception e) {
            logger.error("{}获取书籍信息失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }

    /**
     * 查询指定书籍目录列表
     *
     * @param bookId
     * @return
     */
    @RequestMapping(value = "selectBookDirById", method = RequestMethod.GET)
    public String selectBookDirById(String bookId) {
        Map<String, Object> result = new HashMap();
        try {
            result = bookService.selectBookDirById(bookId);
        } catch (Exception e) {
            logger.error("{}获取书籍信息失败", LOG_TITLE, e);
            JsonUtils.toJson(ResponseUtils.failInServer(result));
        }
        return JsonUtils.toJson(ResponseUtils.success(result));
    }
}
