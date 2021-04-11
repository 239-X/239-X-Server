package com.minimal.common.sdk.utils;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * 集合工具
 * @author wubin
 *
 */
public abstract class CollectionUtils {

	public static boolean isEmpty(Collection<?> collection) {
		return org.springframework.util.CollectionUtils.isEmpty(collection);
	}
	
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	public static boolean isEmpty(Map<?, ?> map) {
		return org.springframework.util.CollectionUtils.isEmpty(map);
	}
	
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
	
	public static boolean containsAny(Collection<?> source, Collection<?> candidates) {
		return org.springframework.util.CollectionUtils.containsAny(source, candidates);
	}

	/**
	 * 返回对象集合的指定参数值集合
	 * <p>忽略空值
	 * @param collection 对象集合
	 * @param propertyName 参数名
	 * @return 参数值的集合
	 * @throws ClassCastException 属性类型错误
	 * @throws RuntimeException 反射取值失败
	 */
    public static <T> Set<T> toPropertySet(Collection<?> collection, String propertyName, Class<T> targetType) {
		if (isEmpty(collection) || propertyName == null || targetType == null) {
			return Collections.emptySet();
		}
		
		Set<T> result = new HashSet<>();
		toCollection(collection,propertyName,targetType,result);
		return result;
	}
    
	/**
	 * 返回对象集合的指定参数值列表
	 * <p>忽略空值
	 * @param collection 对象集合
	 * @param propertyName 参数名
	 * @param targetType
	 * @return 参数值列表
	 * @throws ClassCastException 属性类型错误
	 * @throws RuntimeException 反射取值失败
	 */
	public static <T> List<T> toPropertyList(Collection<?> collection, String propertyName, Class<T> targetType) {
		if (isEmpty(collection) || propertyName == null || targetType == null) {
			return Collections.emptyList();
		}

		List<T> result=new ArrayList<>();
		toCollection(collection, propertyName, targetType, result);
		return result;
	}

	private static <T> void toCollection(Collection<?> collection, String propertyName, Class<T> targetType, Collection<T> result) {
		BeanUtilsBean bub = BeanUtilsBean.getInstance();

    		for (Object bean : collection) {
                Object value;
				try {
					value = bub.getPropertyUtils().getSimpleProperty(bean, propertyName);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new RuntimeException(e);
				}
                if (value != null) {
                	result.add(targetType.cast(value));
                }
    		}
		
	}

	/**
	 * 将对象集合转换成以该对象某个值作为key，对象作为value的map
	 * <p><strong>注意：作为key的属性值不能重复</strong>
	 * @param collection 集合
	 * @param propertyName 用作map key的属性名
	 * @param targetType key类型
	 * @return 值作为key，对象作为value的map
	 * @throws IllegalStateException 存在重复的属性值
	 * @throws ClassCastException 属性类型错误
	 * @throws RuntimeException 反射取值失败
	 */
	public static <K, V> Map<K, V> toBeanMap(Collection<V> collection, String propertyName, Class<K> targetType) {
	    if (isEmpty(collection) || propertyName == null || targetType == null) {
            return Collections.emptyMap();
        }
	    
	    Map<K, V> result = new HashMap<>();
	    BeanUtilsBean bub = BeanUtilsBean.getInstance();
	    try {
	        Set<K> keySet = new HashSet<>();
            for (V bean : collection) {
                    Object value = bub.getPropertyUtils().getSimpleProperty(bean, propertyName);
                    if (targetType.isInstance(value)) {
                    	
                        @SuppressWarnings("unchecked")
						K typedValue = (K) value;
                        
                        if (!keySet.add(typedValue)) {//jdk8直接用map.putIfAbsent
                            throw new IllegalStateException("存在重复的属性值: " + typedValue);
                        }
                        result.put(typedValue, bean);
                    } else {
                        throw new ClassCastException();
                    }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
	}
}
