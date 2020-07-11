package com.cloud.consumer.pojo;

import java.util.Date;

public class Users {
    private Integer userid;

    private String userName;

    private String userPassword;

    private String isexpired;

    private String islocked;

    private Date creatTime;

    private Date updataTime;

    private String remark;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getIsexpired() {
        return isexpired;
    }

    public void setIsexpired(String isexpired) {
        this.isexpired = isexpired == null ? null : isexpired.trim();
    }

    public String getIslocked() {
        return islocked;
    }

    public void setIslocked(String islocked) {
        this.islocked = islocked == null ? null : islocked.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isexpired='" + isexpired + '\'' +
                ", islocked='" + islocked + '\'' +
                ", creatTime=" + creatTime +
                ", updataTime=" + updataTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}