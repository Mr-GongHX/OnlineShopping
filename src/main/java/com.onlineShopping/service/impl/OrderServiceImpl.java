package com.onlineShopping.service.impl;

import com.onlineShopping.dao.BusinessDao;
import com.onlineShopping.dao.GoodsDao;
import com.onlineShopping.dao.OrderDao;
import com.onlineShopping.dao.UserDao;
import com.onlineShopping.model.Order;
import com.onlineShopping.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 订单业务
 * @Auther: Gong HaoXin
 * @Date: 2019-03-13 15:51
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private UserDao userDao;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private BusinessDao businessDao;

    /**
     * 创建订单
     * @param request
     */
    @Override
    public boolean createOrder(HttpServletRequest request) {
        boolean flag = false;
//      获取小程序端传过来的参数
        String userId = request.getParameter("userId");
        String list = request.getParameter("cartItems");
        Double orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
//      获取收货人信息
        String consigneeName = request.getParameter("consigneeName");
        String consigneeAddress = request.getParameter("consigneeAddress");
        String consigneePhone = request.getParameter("consigneePhone");
//      先将对象转成json数组
        JSONArray json = JSONArray.fromObject(list);
//      遍历购买商品列表
        for (int i = 0; i < json.size(); i++) {
            JSONObject goodsInfo = json.getJSONObject(i);
            System.out.println(goodsInfo);
            String goodsId = goodsInfo.get("id").toString();
            String shopId = goodsInfo.get("shopId").toString();
            String goodsQuantity = goodsInfo.get("goodsQuantity").toString();
            Double goodsPrice = Double.parseDouble(goodsInfo.get("goodsPrice").toString());
            String goodsName = goodsInfo.get("goodsName").toString();
            String goodsImg = goodsInfo.get("goodsPicture").toString();
//          获取当前系统时间
            Date currentTime = new Date();
//          转化为订单创建时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          转化为订单编号(年月日时分秒毫秒(当前时间戳)+4位随机数)
            String dateStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentTime);
            String random = RandomStringUtils.random(4,false,true);
//          创建订单实例
            Order order = new Order();
            order.setUserId(userId);
            order.setConsigneeName(consigneeName);
            order.setConsigneeAddress(consigneeAddress);
            order.setConsigneePhone(consigneePhone);
            order.setOrderCode(dateStamp + random);
            order.setOrderPrice(goodsPrice * Integer.parseInt(goodsQuantity));
            order.setOrderCreateTime(sdf.format(currentTime));
//          把数据保存到订单表中
            int resultCreateOrder = orderDao.createOrder(order);

            order.setOrderId(String.valueOf(order.getOrderId()));
            order.setShopId(shopId);
            order.setGoodsId(goodsId);
            order.setGoodsQuantity(goodsQuantity);
            order.setGoodsPrice(goodsPrice);
            order.setGoodsName(goodsName);
            order.setGoodsImg(goodsImg);
//          把数据保存到订单明细表中
            int resultCreateOrderInfo =  orderDao.createOrderInfo(order);

//          修改对应商品库存
            int resultUpdateGoodsAmount = goodsDao.updateGoodsAmount(goodsId, Integer.parseInt(goodsQuantity));

//          修改商家余额
            int resultUpdateShopBalance =
                    businessDao.updateShopBalance(Integer.parseInt(shopId),goodsPrice * Integer.parseInt(goodsQuantity));
            if(resultCreateOrder == 1 && resultCreateOrderInfo == 1
                    && resultUpdateGoodsAmount == 1 && resultUpdateShopBalance == 1) {
                flag = true;
            } else {
                flag = false;
            }
        }
//      修改用户余额
        userDao.updateUserBalance(userId, orderPrice);
        return flag;
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
