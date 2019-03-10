package com.onlineShopping.model;

/**
 * 管理员实体类
 * @Author GongHaoxin
 * @Date ${Date} ${Time}
 */

public class Administrator {

    private long adminId;
    private String adminName;
    private String adminPassword;

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
