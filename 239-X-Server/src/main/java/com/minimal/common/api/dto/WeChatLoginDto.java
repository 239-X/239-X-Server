package com.minimal.common.api.dto;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/20
 */
public class WeChatLoginDto {
    /**
     * 微信的jsCode
     */
    private String jsCode;
    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 微信头像
     */
    private String avatarUrl;


    /**
     * 微信openId
     */
    private String openId;

    /**
     * 推荐人微信act_openId
     */
    private String actOpenId;

    /**
     * 签到倍率
     */
    private int ratio;

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getActOpenId() {
        return actOpenId;
    }

    public void setActOpenId(String actOpenId) {
        this.actOpenId = actOpenId;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}
