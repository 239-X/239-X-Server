package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import lombok.Data;

import java.util.Date;

/**
 * @author linzhiqiang
 */
@Data
public class PermissionDto extends TablePage {
    private Long id;

    private String code;

    private String menuUrl;

    private Integer layer;

    private Date createTime;

    private Date updateTime;
}