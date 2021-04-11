package com.minimal.mybatis.mapper;
import com.minimal.common.api.dto.ArticleDto;
import com.minimal.common.api.dto.ArticleSubjectDto;
import com.minimal.entity.model.Article;
import com.minimal.entity.model.ArticleSubject;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * @author linzhiqiang
 */
public interface ArticleSubjectMapper extends Mapper<ArticleSubject> {
    /**
     * 通过专题id获取专题下面的所有数据
     * @param articleSubjecId
     * @return
     */
    List<Article> selectArticleSubjectByArticleSubjecId(String articleSubjecId);

//    /**
//     * 通过所有专题数据
//     * @return
//     */
//    List<ArticleSubjectDto> selectArticleSubjectAll();
}