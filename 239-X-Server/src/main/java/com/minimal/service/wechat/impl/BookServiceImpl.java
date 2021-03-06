package com.minimal.service.wechat.impl;

import com.minimal.common.api.dto.ArticleBookDirDto;
import com.minimal.common.constant.UserConstants;
import com.minimal.common.sdk.utils.RedisUtil;
import com.minimal.common.sdk.utils.StringUtils;
import com.minimal.entity.model.ArticleBook;
import com.minimal.entity.model.Book;
import com.minimal.entity.model.User;
import com.minimal.mapper.wechat.ArticleBookMapper;
import com.minimal.mapper.wechat.BookMapper;
import com.minimal.mapper.wechat.UserMapper;
import com.minimal.service.wechat.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/25
 */

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ArticleBookMapper articleBookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> selectAllBook(String openId) {
        Map<String, Object> result = new HashMap();
        Example example = new Example(Book.class);
        example.excludeProperties("description");
        example.setOrderByClause("sort DESC , update_time DESC");
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("isEnable", 1);
        List<Book> bookList = bookMapper.selectByExample(example);
        result.put("books",bookList);
        return result;
    }

    @Override
    public Map<String, Object> selectBookById(String bookId,String openId) {
        Map<String, Object> result = new HashMap();
        Example example = new Example(Book.class);
        example.createCriteria().andEqualTo("isDelete", 0)
                .andEqualTo("isEnable", 1).andEqualTo("id",bookId);
        Book book = bookMapper.selectOneByExample(example);
        // ????????????????????????
        String bookReadNumRedisKey = UserConstants.BOOKREADNUM + "_" + book.getId();
        if (redisUtil.get(bookReadNumRedisKey) != null) {
            redisUtil.incr(bookReadNumRedisKey,1);
        }else {
            // ??????????????????
            redisUtil.set(bookReadNumRedisKey, 1, 0);
        }
        if(book!=null){
            result.put("book",book);
            return result;
        }
        return result;
    }

    @Override
    public Map<String, Object> selectBookArticleById(String bookId, String articleId, String openId) {
        Map<String, Object> result = new HashMap();
        ArticleBook articleBook = null;
        if(StringUtils.isBlank(articleId)){
            Example example = new Example(ArticleBook.class);
            example.createCriteria().andEqualTo("isDelete", 0)
                    .andEqualTo("leftId", "0").andEqualTo("bookId",bookId);
            articleBook = articleBookMapper.selectOneByExample(example);
        }else {
            Example example = new Example(ArticleBook.class);
            example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("id",articleId);
            articleBook = articleBookMapper.selectOneByExample(example);
        }
        if(articleBook!=null){
            // ???????????????????????????
            int coin = articleBook.getCoin();
            User user = userMapper.selectUserByOpenId(openId);
            BigDecimal subtractCoin = new BigDecimal(coin);
            if(subtractCoin.compareTo(user.getCoin())== 1){
                result.put("status","0");
                result.put("msg","????????????");
                return result;
            }
            // ??????????????????
            BigDecimal lastCoin = user.getCoin().subtract(subtractCoin);
            user.setCoin(lastCoin);
            userMapper.updateByPrimaryKey(user);
            if(coin!=0){
                result.put("status","1");
                result.put("msg","??????"+coin+"??????");
            }else {
                result.put("status","2");
            }
            result.put("articleBook",articleBook);
            return result;
        }
        return result;
    }

    @Override
    public Map<String, Object> selectBookDirById(String bookId) {
        Map<String, Object> result = new HashMap();
        List<ArticleBook> articleBookList = null;
        if(StringUtils.isNotBlank(bookId)) {
            Example example = new Example(ArticleBook.class);
            //?????????????????????????????????
            example.setOrderByClause("sort ASC");
            example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("bookId", bookId);
            articleBookList = articleBookMapper.selectByExample(example);
            // ??????????????????????????????
            result.put("dir",packagingDir(articleBookList));
        }
        return result;
    }

    /**
     * ??????????????????????????????
     * @param articleBookList
     * @return
     */
    public List<ArticleBookDirDto> packagingDir(List<ArticleBook> articleBookList){
        List<ArticleBookDirDto> articleBookDirDtoList = new ArrayList<>();
        for (ArticleBook articleBook : articleBookList){
            // parentId???0??????????????????
            if("0".equals(articleBook.getParentId())){
                ArticleBookDirDto articleBookDirDto =new ArticleBookDirDto();
                articleBookDirDto.setId(articleBook.getId());
                articleBookDirDto.setText(articleBook.getTitle());
                articleBookDirDtoList.add(articleBookDirDto);
            }
        }

        for (ArticleBookDirDto articleBookDirDto : articleBookDirDtoList){
            List<ArticleBookDirDto> articleBookDirChildrenDtoList = new ArrayList<>();
            // ?????????????????????????????????
            ArticleBookDirDto articleBookDirChildrenDtoOne =new ArticleBookDirDto();
            articleBookDirChildrenDtoOne.setId(articleBookDirDto.getId());
            articleBookDirChildrenDtoOne.setText(articleBookDirDto.getText());
            articleBookDirChildrenDtoList.add(articleBookDirChildrenDtoOne);
            // ????????????????????????
            for (ArticleBook articleBook : articleBookList){
                // parentId???0??????????????????
                if(articleBookDirDto.getId().equals(articleBook.getParentId()) &&
                        !"0".equals(articleBook.getParentId())){
                    ArticleBookDirDto articleBookDirChildrenDto =new ArticleBookDirDto();
                    articleBookDirChildrenDto.setId(articleBook.getId());
                    articleBookDirChildrenDto.setText(articleBook.getTitle());
                    articleBookDirChildrenDtoList.add(articleBookDirChildrenDto);
                }
            }
            // ??????????????????
            if(articleBookDirDto.getChildren()==null){
                articleBookDirDto.setChildren(articleBookDirChildrenDtoList);
            }
        }
        return articleBookDirDtoList;
    }

}
