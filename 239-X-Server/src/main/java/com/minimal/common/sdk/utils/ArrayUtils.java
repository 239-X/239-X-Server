package com.minimal.common.sdk.utils;

/**
 * 数组工具类
 * @author WuBin
 *
 */
public abstract class ArrayUtils {

	private ArrayUtils() {}
	
	/**
	 * 判断对象数组是否为null或长度等于0
	 * @param array 对象数组
	 * @return 如果对象数组是否为null或长度等于0则返回true
	 */
	public static boolean isEmpty(final Object[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}
	
	/**
	 * 判断对象数组是否存在元素
	 * @param array 对象数组
	 * @return 如果对象数组存在元素则返回true
	 */
	public static boolean isNotEmpty(final Object[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}
	
	/**
	 * 判断对象数组是否存在指定元素
	 * @param array 对象数组
	 * @param objectToFind 待查找元素
	 * @return 如果存在返回true
	 */
	public static boolean contains(final Object[] array, final Object objectToFind) {
        return org.apache.commons.lang3.ArrayUtils.contains(array, objectToFind);
    }
}
