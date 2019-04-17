package com.onlineShopping.dao;

import com.onlineShopping.model.Order;

import java.util.List;
import java.util.Map;

/**
 * @Description: 订单Dao
 * @Auther: Gong HaoXin
 * @Date: 2019-03-13 15:53
 */
public interface OrderDao {

//  创建订单
    int createOrder(Order order);

//  创建订单明细表
    int createOrderInfo(Order order);

//  查询用户订单
    List<Map<String, Object>> showMyOrder(String userId);

//  查询订单详情
    List<Map<String, Object>> showMyOrderInfo(String orderId);

//  查询商家待处理订单
    List<Map<String, Object>> orderManagement(String shopId);

//  商家更新订单状态
    int updateOrderStatus(String orderId);

//  查询商家收到的订单
    List<Map<String, Object>> showShopOrder(String shopId);

}
