package com.minimal.mapper.back;

import com.minimal.common.api.dto.ArticleDto;
import com.minimal.entity.model.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author linzhiqiang
 */
public interface BackArticleMapper extends Mapper<Article> {

}