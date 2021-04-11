package com.minimal.common.sdk.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 网关响应结果
 * @author WuBin
 * @param <T> 业务对象
 */
@ApiModel("响应结果")
@Data
public class Response<T>{

	@ApiModelProperty("响应时间")
    private Date responseTime;
	
	@ApiModelProperty("响应码")
    private String code;
	
	@ApiModelProperty("信息")
    private String message;
	
	@ApiModelProperty("状态")
    private String status;
    private T data;

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
