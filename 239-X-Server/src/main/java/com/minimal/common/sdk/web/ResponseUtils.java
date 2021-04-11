package com.minimal.common.sdk.web;

import com.minimal.common.sdk.enums.ResponseEnum;
import com.minimal.common.sdk.utils.DateUtils;

/**
 * Web响应工具类
 * <p>用于快速构建返回统一的响应对象
 * 
 * @author WuBin
 */
public class ResponseUtils {
    
    /**
     * 成功数据（无返回）
     * @return 成功数据（无返回）
     */
    public static Response<Void> success() {
        Response<Void> response = setCommonResponseSuccessProperties(ResponseEnum.SUCCESS,null);
        return response;
    }

    /**
     * 成功数据（非列表）
     * @param data 数据
     * @return 成功数据（非列表）
     */
    public static <T> Response<T> success(T data) {
        Response<T> response = setCommonResponseSuccessProperties(ResponseEnum.SUCCESS,data);
        return response;
    }

    /**
     * 参数异常是数据
     * @param data 数据
     * @return 失败数据
     */
    public static <T> Response<T> failByParams(T data) {
        return setCommonResponseSuccessProperties(ResponseEnum.FAIL_BY_PARAMS,data);
    }

    /**
     * 失败
     * @param data 数据
     * @return 成功数据（非列表）
     */
    public static <T> Response<T> fail(T data) {
        return setCommonResponseSuccessProperties(ResponseEnum.FAIL,data);
    }

    /**
     * 服务器内部异常
     * @param data 数据
     * @return 成功数据（非列表）
     */
    public static <T> Response<T> failInServer(T data) {
        return setCommonResponseSuccessProperties(ResponseEnum.FAIL_IN_SERVER,data);
    }

    private static <T> Response<T> setCommonResponseSuccessProperties(ResponseEnum responseEnums,T data) {
        Response<T> response = new Response<T>();
        response.setMessage(responseEnums.message);
        response.setCode(responseEnums.code);
        response.setStatus(responseEnums.status);
        response.setData(data);
        response.setResponseTime(DateUtils.getCurrentDate());
        return response;
    }
}
