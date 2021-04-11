package com.minimal.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linzhiqiang
 */
@Table(name = "user")
public class User {
    /**
     * ID标识
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 头像
     */
    @Column(name = "head_portrait")
    private String headPortrait;

    /**
     * 微信昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 微信openId
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 手机
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "mailbox")
    private String mailbox;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * 金币
     */
    @Column(name = "coin")
    private BigDecimal coin;

    /**
     * 账号
     */
    @Column(name = "account")
    private String account;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;


    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;


    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}