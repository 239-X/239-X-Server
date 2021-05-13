package com.minimal.service.back;

import com.github.pagehelper.PageInfo;
import com.minimal.common.api.dto.OperationUserDto;
import com.minimal.entity.model.OperationUser;

/**
 * 后端管理员接口
 *
 * @author linzhiqiang
 * @date 2021-05-05
 */
public interface BackManageService {
    /**
     * 保持对象
     *
     * @param operationUserDto
     * @return
     */
    int save(OperationUserDto operationUserDto);

    /**
     * 修改对象
     *
     * @param operationUserDto
     * @return
     */
    int update(OperationUserDto operationUserDto);

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
    OperationUserDto detail(long id);

    /**
     * 分页查询对象
     *
     * @param operationUserDto
     * @return
     */
    PageInfo<OperationUser> select(OperationUserDto operationUserDto);
}
