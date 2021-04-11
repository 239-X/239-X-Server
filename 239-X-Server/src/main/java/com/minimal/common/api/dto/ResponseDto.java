package com.minimal.common.api.dto;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/17
 */
public class ResponseDto {
    public String status;
    public String code;
    public String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
