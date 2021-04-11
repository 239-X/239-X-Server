package com.minimal.common.sdk.sequence;

/**
 * ID生成器
 * @author wubin
 *
 */
public interface IdGenerator {

	/**
	 * 获取新的ID
	 * @return ID
	 */
	long nextId();
}
