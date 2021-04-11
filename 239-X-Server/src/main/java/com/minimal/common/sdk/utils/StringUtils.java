package com.minimal.common.sdk.utils;

/**
 * <p>字符串工具类，统一接口</p>
 * 区分字符串的形式：
 * <ol>
 * 	<li>普通字符串abc</li>
 *  <li>空串""</li>
 *  <li>空白字符"  "</li>
 *  <li>null</li>
 * </ol>
 * <p>对{@link org.apache.commons.lang3.StringUtils}的简单封装</p>
 * 
 * @author WuBin
 *
 */
public final class StringUtils {

	private StringUtils() {}
	
	/**
	 * <p>判断字符序列是否为空格、空字符或null</p>
	 * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
	 * 
	 * @param cs 待检查的字符序列，可以为null
	 * @return 字符序列为空格、空字符或null，则返回true
	 */
	public static boolean isBlank(final CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isBlank(cs);
	}
	
	/**
	 * <p>{@link #isBlank(CharSequence)}取反</p>
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
	 * @param cs 待检查的字符序列，可以为null
	 * @return  字符序列不为空格、空字符和null，则返回true
	 */
	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}
	
	/**
	 * <p>去掉前后空格，如果去掉后为空字符串，则返回null</p>
	 * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     * </pre>
	 * @param str 待修剪的字符串，可以为null
	 * @return 修剪结果
	 */
	public static String trimToNull(final String str) {
		return org.apache.commons.lang3.StringUtils.trimToNull(str);
	}
	
	/**
	 * <p>去掉前后空格，如果为null，则返回空字符串</p>
	 * <pre>
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     * </pre>
	 * @param str 待修剪的字符串，可以为null
	 * @return 修剪结果
	 */
	public static String trimToEmpty(final String str) {
		return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
	}
	
}
