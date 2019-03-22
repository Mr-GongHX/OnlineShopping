package com.onlineShopping.service.impl;

import com.onlineShopping.dao.OrderDao;
import com.onlineShopping.model.Order;
import com.onlineShopping.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 订单业务
 * @Auther: Gong HaoXin
 * @Date: 2019-03-13 15:51
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    /**
     * 创建订单
     * @param request
     */
    @Override
    public int createOrder(HttpServletRequest request, @RequestParam("cartItems[]") List<String> cartItems) {
//      获取小程序端传过来的参数
        String userId = request.getParameter("userId");
        Map<String, Object> param = new HashMap<>();
        param.put("cartItems", cartItems);
//      遍历购买商品列表
        for (String cartItem : cartItems) {
            System.out.println(cartItem);
        }

        String consigneeName = request.getParameter("consigneeName");
        String consigneeAddress = request.getParameter("consigneeAddress");
        String consigneePhone = request.getParameter("consigneePhone");
        Double orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
//      获取当前系统时间
        Date currentTime = new Date();
//      转化为订单创建时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      转化为订单编号(年月日时分秒毫秒(当前时间戳)+4位随机数)
        String dateStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentTime);
        String random = RandomStringUtils.random(4,false,true);
        System.out.println("userId:"+userId + "consigneeName:" + consigneeName + "orderPrice:" + orderPrice  + "orderNo:"+dateStamp + random);
//      创建订单实例
        Order order = new Order();
//        order.setShopId(shopId);
        order.setUserId(userId);
//        order.setGoodsId(goodsId);
//        order.setGoodsQuantity(goodsQuantity);
//        order.setGoodsPrice(goodsPrice);
//        order.setGoodsName(goodsName);
        order.setConsigneeName(consigneeName);
        order.setConsigneeAddress(consigneeAddress);
        order.setConsigneePhone(consigneePhone);
        order.setOrderCode(dateStamp + random);
        order.setOrderPrice(orderPrice);
        order.setOrderCreateTime(sdf.format(currentTime));
//      将数据保存到数据库中
//        orderDao.createOrder(order);
        return 1;
    }

    /**
     * 查询用户订单
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> showMyOrder(String userId) {
        return orderDao.showMyOrder(userId);
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public List<Map<String, Object>> showMyOrderInfo(String orderId) {
        return orderDao.showMyOrderInfo(orderId);
    }

    /**
     * 查询商家待处理订单
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> orderManagement(HttpServletRequest request) {
        String shopId = request.getParameter("shopId");
        return orderDao.orderManagement(shopId);
    }

    /**
     * 商家更新订单状态
     * @param orderId
     * @return
     */
    @Override
    public boolean updateOrderStatus(String orderId) {
        System.out.println("测试"+orderDao.updateOrderStatus(orderId));
        if(orderDao.updateOrderStatus(orderId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询商家收到的订单
     * @param shopId
     * @return
     */
    @Override
    public List<Map<String, Object>> showShopOrder(String shopId) {
        return orderDao.showShopOrder(shopId);
    }
}
