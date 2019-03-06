package com.onlineShopping.dao;


import java.util.List;
import java.util.Map;

/**
 * @Description: 用户Dao
 * @Author: Gong HaoXin
 * @CreateTime: 2019-02-12 19:25:50
 **/
public interface UserDao {

//  根据关键字搜索商品
    List<Map<String, Object>> searchGoods(String searchName);

//  展示手机专区商品
    List<Map<String, Object>> showPhoneAreaGoods();

//  展示电脑专区商品
    List<Map<String, Object>> showComputerAreaGoods();

//  展示外设专区商品
    List<Map<String, Object>> showAccessoryAreaGoods();

//  低价好货商品
    List<Map<String, Object>> showLowPriceGoods();

//  新上架商品
    List<Map<String, Object>> showNewGoods();

//  根据商品id查找商品详情
    List<Map<String, Object>> goodsInfo(Integer goodsId);

//  根据商品id查找商品评论详情
    List<Map<String, Object>> goodsCommentDetail(Integer goodsId);

}
