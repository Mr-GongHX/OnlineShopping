package com.onlineShopping.model;


/**
 * @Description: 商品实体类
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-17 17:04:45
 **/
public class Goods {

    private String goodsId;
    private String shopId;
    private String typeId;
    private String goodsName;
    private String goodsStatus;
    private Double goodsPrice;
    private String goodsAmount;
    private String goodsPicturePath;
    private String goodsPictureInfoPath;
    private String goodsUploadTime;
    private String goodsModifyTime;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getGoodsPicturePath() {
        return goodsPicturePath;
    }

    public void setGoodsPicturePath(String goodsPicturePath) {
        this.goodsPicturePath = goodsPicturePath;
    }

    public String getGoodsPictureInfoPath() {
        return goodsPictureInfoPath;
    }

    public void setGoodsPictureInfoPath(String goodsPictureInfoPath) {
        this.goodsPictureInfoPath = goodsPictureInfoPath;
    }

    public String getGoodsUploadTime() {
        return goodsUploadTime;
    }

    public void setGoodsUploadTime(String goodsUploadTime) {
        this.goodsUploadTime = goodsUploadTime;
    }

    public String getGoodsModifyTime() {
        return goodsModifyTime;
    }

    public void setGoodsModifyTime(String goodsModifyTime) {
        this.goodsModifyTime = goodsModifyTime;
    }
}
