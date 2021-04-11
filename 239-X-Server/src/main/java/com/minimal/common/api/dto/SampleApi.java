package com.minimal.common.api.dto;
import com.minimal.common.sdk.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 服务接口示例
 * @author wubin
 *
 */

@RequestMapping("samples")
public interface SampleApi {

	/**
	 * 回声测试
	 * @param dto {@link SampleDto}
	 * @return {@link SampleDto}
	 */
	@RequestMapping(value = "echo", method = RequestMethod.GET)
	SampleDto echo(SampleDto dto);
	
	/**
	 * 根据ID查找
	 * @param id ID
	 * @return {@link SampleDto}，查找不到返回{@code null}
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	SampleDto getById(@PathVariable("id") Long id);
	
	/**
	 * 创建
	 * @param dto {@link SampleDto}
	 * @return ID
	 */
	@RequestMapping(method = RequestMethod.POST)
	Long create(@RequestBody SampleDto dto);
	
	/**
	 * 更新
	 * @param id ID
	 * @param dto {@link SampleDto}
	 * @return 是否已更新
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	boolean update(@PathVariable("id") Long id, @RequestBody SampleDto dto);
	
	/**
	 * 根据ID删除
	 * @param id ID
	 * @return 是否已删除
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	boolean delete(@PathVariable("id") Long id);
	
	/**
	 * 分页查找
	 * @param index 页码下标
	 * @param size 每页大小，默认为20
	 * @return 分页对象
	 */
	@RequestMapping(value = "page", method = RequestMethod.GET)
	Page<SampleDto> page(@RequestParam("index") int index, @RequestParam(value = "size", defaultValue = "20") int size);
}
