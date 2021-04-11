package com.minimal.common.sdk.enums;

/**
 * @description:
 * @Date : 2019/3/19 19:04
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public enum ResponseEnum {

    SUCCESS("200","000000","请求成功"),
    FAIL("200","100000","请求失败"),
    FAIL_BY_PARAMS("200","200000","请求参数异常"),
    FAIL_IN_SERVER("200","300000","服务器内部异常"),
    RATE_LIMIT("200","400000","限流中");

    public String status;
    public String code;
    public String message;

    ResponseEnum(String s, String s1, String s2) {
        this.status = s;
        this.code = s1;
        this.message = s2;
    }
}
