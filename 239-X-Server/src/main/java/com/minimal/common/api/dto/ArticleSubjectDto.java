package com.minimal.common.api.dto;

import java.util.Date;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/27
 */
public class ArticleSubjectDto {
    /**
     * id
     */
    private String id;

    /**
     * 用户userId
     */
    private String userId;

    /**
     * 用户openId
     */
    private String openId;

    /**
     * 专题是否免费
     */
    private Integer isFree;

    /**
     * 专题金币
     */
    private Integer coin;

    /**
     * 专题名字
     */
    private String name;
    /**
     * 专题描述
     */
    private String description;

    /**
     * 专题背景
     */
    private String backImg;

    /**
     * 用户是否已经订阅
     */
    private Integer isSubscription;

    /**
     * 开始时间
     */
    private String createTimeStr;

    /**
     * 结束时间
     */
    private String updateTimeStr;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getIsSubscription() {
        return isSubscription;
    }

    public void setIsSubscription(Integer isSubscription) {
        this.isSubscription = isSubscription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
