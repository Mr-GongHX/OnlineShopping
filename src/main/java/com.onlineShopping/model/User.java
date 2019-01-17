package com.onlineShopping.model;

import java.util.Date;

/**
 * 用户实体类
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 * @Version 1.0
 */


public class User {

    private long userId;
    private String userPassword;
    private String username;
    private Date userRegisterTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date useRegisterTime) {
        this.userRegisterTime = useRegisterTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", username='" + username + '\'' +
                ", useRegisterTime=" + userRegisterTime +
                '}';
    }
}
