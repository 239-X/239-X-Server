package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author linzhiqiang
 */
@Data
public class CategoryDto extends TablePage {
    private String id;

    private String name;

    private String sketch;

    private String parentId;

    private Integer isStart;

    private Integer priority;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private Integer version;
}