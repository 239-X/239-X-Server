package com.minimal.common.sdk;

import lombok.Data;

/**
 * 列表分页请求
 * @author linzhiqiang
 * @date 2021-05-03
 */
@Data
public class TablePage {
    /**
     * 页码
     */
    private int pageNo;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int currentPageNo;
}