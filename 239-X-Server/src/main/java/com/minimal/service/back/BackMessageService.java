package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.entity.model.InfoBar;
import com.minimal.common.api.dto.InfoBarDto;

/**
 * 后端分类接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackMessageService {
    /**
     * 保持对象
     *
     * @param infoBarDto
     * @return
     */
    int save(InfoBarDto infoBarDto);

    /**
     * 修改对象
     *
     * @param infoBarDto
     * @return
     */
    int update(InfoBarDto infoBarDto);

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 获取对象详情
     *
     * @param id
     * @return
     */
    InfoBarDto detail(long id);

    /**
     * 分页查询对象
     *
     * @param infoBarDto
     * @return
     */
    PageInfo<InfoBar> select(InfoBarDto infoBarDto);
}
