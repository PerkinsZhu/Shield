package com.perkinszhu.www.shield.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 8:56
 */
public class Account {
    private String loginName;
    private String passWord;
    private String userName;
    private Date addTime;
    private String desc;

    public Account() {
    }

    public Account(String loginName, String passWord, String desc) {
        this.loginName = loginName;
        this.passWord = passWord;
        this.addTime = new Date();
        this.desc = desc;
    }

    public Account(String loginName, String passWord, String userName, String desc) {
        this.loginName = loginName;
        this.passWord = passWord;
        this.userName = userName;
        this.addTime = new Date();
        this.desc = desc;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
