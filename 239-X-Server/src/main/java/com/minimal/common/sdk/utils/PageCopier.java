package com.minimal.common.sdk.utils;
import com.github.pagehelper.PageInfo;
import com.minimal.common.sdk.Page;

/**
 * 分页复制器
 * 
 * @author WuBin
 */
public class PageCopier {

    private PageCopier() {}
    
    /**
     * 将DAO层获取到的分页对象相应内容，复制到服务层的分页对象上
     * @param pageInfo 通过分页组件得到的{@link PageInfo}对象
     * @param type 分页元素类型
     * @return 分页对象
     * @throws IllegalStateException 分页元素类型无法实例化
     */
    public static <T> Page<T> copy(PageInfo<?> pageInfo, Class<T> type) throws IllegalStateException {
        Page<T> page = new Page<>();
        page.setTotal(pageInfo.getTotal());
        page.setContent(BeanUtils.copyList(pageInfo.getList(), type));
        return page;
    }
}
