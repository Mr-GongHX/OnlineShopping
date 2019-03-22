package com.onlineShopping.controller;

import com.onlineShopping.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 订单Controller
 * @Auther: Gong HaoXin
 * @Date: 2019-03-13 15:49
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/createOrder")
    public boolean createOrder(HttpServletRequest request, @RequestParam List<String> cartItems) {
        if(orderService.createOrder(request, cartItems) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询用户订单
     * @return
     */
    @ResponseBody
    @RequestMapping("showMyOrder-{userId}")
    public List<Map<String, Object>> showMyOrder(Model model, @PathVariable String userId) {
        model.addAttribute("userId", userId);
        return orderService.showMyOrder(userId);
    }

    /**
     * 查询用户订单详情
     * @param model
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("showMyOrderInfo-{orderId}")
    public List<Map<String, Object>> showMyOrderInfo(Model model, @PathVariable String orderId) {
        model.addAttribute("orderId", orderId);
        return orderService.showMyOrderInfo(orderId);
    }

    /**
     * 查询商家待处理订单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderManagement")
    public List<Map<String, Object>> orderManagement(HttpServletRequest request) {
        return orderService.orderManagement(request);
    }

    /**
     * 商家更新订单状态
     * @param model
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("updateOrderStatus-{orderId}")
    public boolean updateOrderStatus(Model model, @PathVariable String orderId) {
        model.addAttribute("orderId", orderId);
        return orderService.updateOrderStatus(orderId);
    }

    /**
     * 查询商家收到的订单
     * @param model
     * @param shopId
     * @return
     */
    @ResponseBody
    @RequestMapping("/showShopOrder-{shopId}")
    public List<Map<String, Object>> showShopOrder(Model model, @PathVariable String shopId) {
        model.addAttribute("shopId", shopId);
        return orderService.showShopOrder(shopId);
    }

}
