package com.minimal.common.sdk.log;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体类
 * @author linzhiqiang
 */

@Data
@Document(collection = "sys_operate_log")
public class OperateLogPO implements Serializable {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 用户标识
     */
    @Field("user_id")
    private String userId;

    /**
     * 用户名称
     */
    @Field("user_name")
    private String userName;

    /**
     * 访问IP
     */
    @Field("ip")
    private String ip;

    /**
     * 操作模块
     */
    @Field("module")
    private String module;

    /**
     * 操作时间
     */
    @Field("create_time")
    private Date createTime;

    /**
     * 操作结果(1--成功，2--失败)
     */
    @Field("status")
    private String status;

    /**
     * 操作详情
     */
    @Field("operation_desc")
    private String operationDesc;

    /**
     * 浏览器
     */
    @Field("browner_no")
    private String brownerNo;

    /**
     * 操作系统
     */
    @Field("os_no")
    private String osNo;

    /**
     * 接口路径
     */
    @Field("method")
    private String method;

    /**
     * 接口请求参数
     */
    @Field("params")
    private String params;

    /**
     * 接口返回值
     */
    @Field("result")
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc == null ? null : operationDesc.trim();
    }

    public String getBrownerNo() {
        return brownerNo;
    }

    public void setBrownerNo(String brownerNo) {
        this.brownerNo = brownerNo == null ? null : brownerNo.trim();
    }

    public String getOsNo() {
        return osNo;
    }

    public void setOsNo(String osNo) {
        this.osNo = osNo == null ? null : osNo.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OperLogPO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", module='" + module + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", operationDesc='" + operationDesc + '\'' +
                ", brownerNo='" + brownerNo + '\'' +
                ", osNo='" + osNo + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}