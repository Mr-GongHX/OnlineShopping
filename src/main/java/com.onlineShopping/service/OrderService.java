package com.onlineShopping.service;


import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 订单Service
 * @Auther: Gong HaoXin
 * @Date: 2019-03-13 15:52
 */
public interface OrderService {

//  创建订单
    boolean createOrder(HttpServletRequest request);

//  查询用户订单
    List<Map<String, Object>> showMyOrder(String userId);

//  查询订单详情
    List<Map<String, Object>> showMyOrderInfo(String orderId);

//  查询商家待处理订单
    List<Map<String, Object>> orderManagement(HttpServletRequest request);

//  商家更新订单状态
    boolean updateOrderStatus(String orderId);

//  查询商家收到的订单
    List<Map<String, Object>> showShopOrder(String shopId);
}
