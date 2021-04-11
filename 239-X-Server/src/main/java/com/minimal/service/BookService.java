package com.minimal.service;

import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/25
 */
public interface BookService {

    /**
     * 查询所有的书籍列表
     *
     * @param openId
     * @return
     */
    Map<String,Object> selectAllBook(String openId);

    /**
     * 查询指定id书籍信息
     * @param bookId
     * @param openId
     * @return
     */
    Map<String,Object> selectBookById(String bookId, String openId);

    /**
     * 查询指定id书籍的文章信息
     * @param bookId
     * @param articleId
     * @param openId
     * @return
     */
    Map<String,Object> selectBookArticleById(String bookId, String articleId, String openId);

    /**
     * 查询指定书籍目录列表
     * @param bookId
     * @return
     */
    Map<String,Object> selectBookDirById(String bookId);
}
