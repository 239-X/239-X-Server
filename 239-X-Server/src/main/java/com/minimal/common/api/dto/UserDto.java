package com.minimal.common.api.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/22
 */
public class UserDto {
    /**
     * ID标识
     */
    private String id;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 名称
     */
    private String name;

    /**
     * 微信openId
     */
    private String openid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private String createTimeStr;

    /**
     * 推荐获得的积分
     */
    private BigDecimal coin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }
}
