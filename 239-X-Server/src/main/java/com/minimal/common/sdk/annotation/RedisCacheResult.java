package com.minimal.common.sdk.annotation;

import java.lang.annotation.*;

/**
 * @description:  redis切面缓存
 * @Date : 2019/3/19 19:28
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCacheResult {

    long expire() default 3600;
}
