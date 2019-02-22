package com.onlineShopping.model;

/**
 * @Description: 商家实体类
 * @Author Gong Haoxin
 * @Date 2019-01-20 11:34:32
 */

public class Business {

    private long shopId;
    private String shopName;
    private String shopAdminName;
    private String shopAdminPassword;
    private String shopStatus;
    private String shopBusiness;
    private String shopInfoPath;
    private String shopBalance;
    private String shopRegisterTime;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAdminName() {
        return shopAdminName;
    }

    public void setShopAdminName(String shopAdminName) {
        this.shopAdminName = shopAdminName;
    }

    public String getShopAdminPassword() {
        return shopAdminPassword;
    }

    public void setShopAdminPassword(String shopAdminPassword) {
        this.shopAdminPassword = shopAdminPassword;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getShopBusiness() {
        return shopBusiness;
    }

    public void setShopBusiness(String shopBusiness) {
        this.shopBusiness = shopBusiness;
    }

    public String getShopInfoPath() {
        return shopInfoPath;
    }

    public void setShopInfoPath(String shopInfoPath) {
        this.shopInfoPath = shopInfoPath;
    }

    public String getShopBalance() {
        return shopBalance;
    }

    public void setShopBalance(String shopBalance) {
        this.shopBalance = shopBalance;
    }

    public String getShopRegisterTime() {
        return shopRegisterTime;
    }

    public void setShopRegisterTime(String shopRegisterTime) {
        this.shopRegisterTime = shopRegisterTime;
    }

}
