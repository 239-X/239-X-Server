package com.minimal.service.wechat;

import com.minimal.common.api.dto.ArticleSubjectDto;

import java.util.Map;

/**
 * @author linzhiqiang
 * @date 2019/3/27
 */
public interface ArticleSubjectService {
    /**
     * 获取所有专题列表数据
     *
     * @param openId
     * @return
     */
    Map<String, Object> selectAllArticleSubject(String openId);

    /**
     * 获取所有指定专题数据
     *
     * @param id
     * @param openId
     * @return
     */
    Map<String, Object> selectArticleSubjectById(String id,String openId);

    /**
     * 获取所有专题中文章数据
     *
     * @param articleSubjectId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Map<String, Object> selectArticleBySubjectId(String articleSubjectId, int pageNo, int pageSize);

    /**
     * 专题订阅
     * @param articleSubjectDto
     * @return
     */
    Map<String,Object> subjectSubscribeById(ArticleSubjectDto articleSubjectDto);

    /**
     * 获取指定用户下面的所有专题列表数据
     * @param openId
     * @return
     */
    Map<String,Object> selectSubjectByOpenId(String openId);

    /**
     * 取消专题订阅
     * @param articleSubjectDto
     * @return
     */
    Map<String,Object> cancelSubject(ArticleSubjectDto articleSubjectDto);
}
