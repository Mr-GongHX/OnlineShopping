package com.onlineShopping.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户接口
 * @Author: Gong HaoXin
 * @CreateTime: 2019-02-12 19:25:50
 **/
public interface UserService {

//  根据关键字搜索商品
    List<Map<String, Object>> searchGoods(String searchName);

//  低价好货
    List<Map<String, Object>> showLowPriceGoods();

//  新上架商品
    List<Map<String, Object>> showNewGoods();

//  根据商品id查找商品详情
    List<Map<String, Object>> goodsInfo(HttpServletRequest request);

//  根据商品id查找商品评论详情
    List<Map<String, Object>> goodsCommentDetail(HttpServletRequest request);
}
