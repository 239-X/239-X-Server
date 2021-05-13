package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import lombok.Data;

import java.util.Date;

/**
 * @author linzhiqiang
 * @date 2021-05-05
 */
@Data
public class ArticleTagDto extends TablePage {
    /**
     * ID标识
     */
    private String id;

    /**
     * 标签名字
     */
    private String tagName;

    /**
     * 标签层级
     */
    private Integer tagLevel;

    /**
     * 文章分类id
     */
    private String articleTypeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 版本号
     */
    private Integer version;
}