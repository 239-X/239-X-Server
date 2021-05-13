package com.minimal.mapper.wechat;

import com.minimal.common.api.dto.ArticleCollectDto;
import com.minimal.entity.model.ArticleCollect;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author linzhiqiang
 */
public interface ArticleCollectMapper extends Mapper<ArticleCollect> {

    /**
     * 获取指定userId下面的所有数据
     * @param userId
     * @return
     */
    List<ArticleCollectDto> selectCollectByUserId(@Param(value="userId") String userId);
}