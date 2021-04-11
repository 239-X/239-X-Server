package com.minimal.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class CampaignsKeywordInfo {
    private Integer id;

    private Integer userid;

    private Integer accountid;

    private Long campaignid;

    private String matchtype;

    private String state;

    private Date createtime;

    private Date updatetime;

    private Long adgroupid;

    private Long keywordid;

    private String keywordtext;

    private BigDecimal bid;

    private BigDecimal recoverybid;

    private String site;

    private String accountname;

    private Integer performance;

    private Integer settingid;

    private Date creationdate;

    private Date lastupdateddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Long getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Long campaignid) {
        this.campaignid = campaignid;
    }

    public String getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype == null ? null : matchtype.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getAdgroupid() {
        return adgroupid;
    }

    public void setAdgroupid(Long adgroupid) {
        this.adgroupid = adgroupid;
    }

    public Long getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(Long keywordid) {
        this.keywordid = keywordid;
    }

    public String getKeywordtext() {
        return keywordtext;
    }

    public void setKeywordtext(String keywordtext) {
        this.keywordtext = keywordtext == null ? null : keywordtext.trim();
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getRecoverybid() {
        return recoverybid;
    }

    public void setRecoverybid(BigDecimal recoverybid) {
        this.recoverybid = recoverybid;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getSettingid() {
        return settingid;
    }

    public void setSettingid(Integer settingid) {
        this.settingid = settingid;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getLastupdateddate() {
        return lastupdateddate;
    }

    public void setLastupdateddate(Date lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }
}