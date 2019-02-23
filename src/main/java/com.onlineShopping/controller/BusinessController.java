package com.onlineShopping.controller;

import com.onlineShopping.service.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description: 商家Controller
 * @Author: Gong Haoxin
 * @CreateTime: 2019-02-22 22:03:32
 **/
@Controller
@RequestMapping("/shop")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * 检测商家管理员用户名是否重复
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkShopAdminNameDuplicate")
    public boolean checkShopAdminNameDuplicate(HttpServletRequest request) {
        String shopAdminName = request.getParameter("username");
        return businessService.checkShopAdminNameDuplicate(shopAdminName);
    }

    /**
     * 商家注册
     * @param request
     */
    @ResponseBody
    @RequestMapping("/shopRegister")
    public boolean shopRegister(HttpServletRequest request) {
//      返回1条结果代表注册成功
        if(businessService.shopRegister(request) == 1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 商家管理员登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/shopAdminLogin")
    public Map<String, Object> shopAdminLogin(HttpServletRequest request) {
        return businessService.shopAdminLogin(request);
    }

    /**
     * 商家上传头像
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadShopProfile-{shopId}")
    public boolean uploadShopProfile(HttpServletRequest request, Model model, @PathVariable String shopId){
        model.addAttribute("shopId", shopId);
        System.out.println("进入商家上传头像");
        return businessService.uploadShopProfile(request, shopId);
    }

    /**
     * 根据商家id返回商家头像
     * @param response
     * @param model
     * @param shopId
     * @return
     */
    @ResponseBody
    @RequestMapping("/shopAdminProfile-{shopId}")
    public String showShopAdminProfile(HttpServletResponse response, Model model, @PathVariable String shopId) {
        model.addAttribute("shopId", shopId);
        return businessService.showShopAdminProfile(response, Integer.parseInt(shopId));
    }
}
