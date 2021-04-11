package com.minimal.mybatis.mapper;

import com.minimal.common.api.dto.ArticleSubjectDto;
import com.minimal.entity.model.ArticleSubjectUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author linzhiqiang
 */
public interface ArticleSubjectUserMapper extends Mapper<ArticleSubjectUser> {

    /**
     * 获取指定用户的所有订阅信息
     * @param userId
     * @return
     */
    List<ArticleSubjectDto> selectSubjectByUserId(@Param(value="userId") String userId);
}