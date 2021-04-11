package com.minimal.common.sdk.log;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author linzhiqiang
 * @date 2019/4/26
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLog {

    /**
     * 请求模块名称
     * @return
     */
    public String module() default "";

    /**
     * 接口详情描述
     * @return
     */
    public String operationDesc() default "";
}

